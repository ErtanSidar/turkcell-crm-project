package com.turkcell.customerservice.services.concretes;

import com.turkcell.customerservice.entities.City;
import com.turkcell.customerservice.repositories.CityRepository;
import com.turkcell.customerservice.services.abstracts.CityService;
import com.turkcell.customerservice.services.dtos.requests.cityRequests.CreateCityRequest;
import com.turkcell.customerservice.services.dtos.requests.cityRequests.UpdateCityRequest;
import com.turkcell.customerservice.services.dtos.responses.cityResponses.CreatedCityResponse;
import com.turkcell.customerservice.services.dtos.responses.cityResponses.GetAllCityResponse;
import com.turkcell.customerservice.services.dtos.responses.cityResponses.GetCityResponse;
import com.turkcell.customerservice.services.dtos.responses.cityResponses.UpdatedCityResponse;
import com.turkcell.customerservice.services.mappers.CityMapper;
import com.turkcell.customerservice.services.rules.CityBusinessRules;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import io.github.ertansidar.response.ListResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {

    private CityRepository cityRepository;
    private CityBusinessRules cityBusinessRules;

    @Override
    public GetListResponse<GetAllCityResponse> getAll(PageInfo pageInfo) {
        return ListResponse.get(pageInfo, cityRepository, CityMapper.INSTANCE::getAllCityResponseFromCity);
    }

    @Override
    public GetCityResponse getById(UUID id) {
        cityBusinessRules.checkCityIdExists(id);
        City foundedCity = cityRepository.findById(id).get();
        return CityMapper.INSTANCE.getCityResponseFromCity(foundedCity);
    }

    @Override
    public CreatedCityResponse add(CreateCityRequest request) {
        cityBusinessRules.checkCityNameIsUnique(request.getName());
        City city = CityMapper.INSTANCE.cityFromCreateCityRequest(request);
        City createdCity = cityRepository.save(city);
        return CityMapper.INSTANCE.createdCityResponseFromCity(createdCity);
    }

    @Override
    public UpdatedCityResponse update(UpdateCityRequest request, UUID id) {
        cityBusinessRules.checkCityIdExists(id);
        cityBusinessRules.checkCityNameIsUnique(request.getName());
        City foundedCity = cityRepository.findById(id).get();
        City city = CityMapper.INSTANCE.cityFromUpdateCityRequest(request);
        city.setId(foundedCity.getId());
        City updatedCity = cityRepository.save(city);
        return CityMapper.INSTANCE.updatedCityResponseFromCity(updatedCity);
    }

    @Override
    public void delete(UUID id) {
        cityBusinessRules.checkCityIdExists(id);
        cityRepository.softDelete(id, LocalDateTime.now(), AuditAwareImpl.USER);
    }

}
