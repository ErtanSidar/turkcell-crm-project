package com.turkcell.customerservice.services.concretes;

import com.turkcell.customerservice.entities.City;
import com.turkcell.customerservice.repositories.CityRepository;
import com.turkcell.customerservice.services.abstracts.CityService;
import com.turkcell.customerservice.services.dtos.requests.cityRequests.CreateCityRequest;
import com.turkcell.customerservice.services.dtos.requests.cityRequests.UpdateCityRequest;
import com.turkcell.customerservice.services.dtos.responses.cityResponses.*;
import com.turkcell.customerservice.services.mappers.CityMapper;
import com.turkcell.customerservice.services.rules.CityBusinessRules;
import io.github.ertansidar.exception.type.BusinessException;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import io.github.ertansidar.response.ListResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
        cityBusinessRules.cityNotFound(id);
        cityBusinessRules.cityIsDeleted(id);

        City foundCity = cityRepository.findById(id)
                .orElseThrow(() -> new BusinessException("City not found"));
        return CityMapper.INSTANCE.getCityResponseFromCity(foundCity);
    }

    @Override
    public CreatedCityResponse add(CreateCityRequest request) {
        cityBusinessRules.cityNameCanNotBeDuplicated(request.getName());

        City city = CityMapper.INSTANCE.cityFromCreateCityRequest(request);
        city.setId(UUID.randomUUID());
        City createdCity = cityRepository.save(city);

        return CityMapper.INSTANCE.createdCityResponseFromCity(createdCity);
    }

    @Override
    public UpdatedCityResponse update(UpdateCityRequest request, UUID id) {
//        cityBusinessRules.cityNotFound(id);
//        cityBusinessRules.cityNameCanNotBeDuplicated(request.getName());

        City foundCity = cityRepository.findById(id)
                .orElseThrow(() -> new BusinessException("City not found"));

        City city = CityMapper.INSTANCE.cityFromUpdateCityRequest(request);
        city.setId(foundCity.getId());
        City updatedCity = cityRepository.save(city);

        return CityMapper.INSTANCE.updatedCityResponseFromCity(updatedCity);
    }

    @Override
    public void delete(UUID id) {
        cityBusinessRules.cityNotFound(id);
        cityBusinessRules.cityIsDeleted(id);
        cityRepository.softDelete(id, LocalDateTime.now(), AuditAwareImpl.USER);
    }

    @Override
    public List<GetCityByCountryIdResponse> getByCountryId(UUID countryId) {
        List<City> cities = this.cityRepository.findByCountryId(countryId);
        return cities.stream().map(CityMapper.INSTANCE::getCityByCountryIdResponseFromCity).toList();
    }

}
