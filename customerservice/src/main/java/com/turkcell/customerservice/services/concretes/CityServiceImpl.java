package com.turkcell.customerservice.services.concretes;

import com.turkcell.customerservice.entities.City;
import com.turkcell.customerservice.repositories.CityRepository;
import com.turkcell.customerservice.services.abstracts.CityService;
import com.turkcell.customerservice.services.dtos.requests.cityRequests.CreateCityRequest;
import com.turkcell.customerservice.services.dtos.requests.cityRequests.UpdateCityRequest;
import com.turkcell.customerservice.services.dtos.responses.cityResponses.*;
import com.turkcell.customerservice.services.mappers.CityMapper;
import com.turkcell.customerservice.services.rules.CityBusinessRules;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        GetListResponse<GetAllCityResponse> response = new GetListResponse<>();
        Pageable pageable = PageRequest.of(pageInfo.getPage(), pageInfo.getSize());
        Page<City> cities = cityRepository.findAllIfDeletedDateIsNull(pageable);
        response.setItems(cities.stream().map(CityMapper.INSTANCE::getAllCityResponseFromCity).collect(Collectors.toList()));
        response.setTotalElements(cities.getTotalElements());
        response.setTotalPage(cities.getTotalPages());
        response.setSize(cities.getSize());
        response.setHasNext(cities.hasNext());
        response.setHasPrevious(cities.hasPrevious());
        return response;
    }

    @Override
    public GetCityResponse getById(UUID id) {
        cityBusinessRules.cityNotFound(id);
        cityBusinessRules.cityIsDeleted(id);

        City foundCity = cityRepository.findById(id).get();
        GetCityResponse getCityResponse = CityMapper.INSTANCE.getCityResponseFromCity(foundCity);
        return getCityResponse;
    }

    @Override
    public CreatedCityResponse add(CreateCityRequest createCityRequest) {
        cityBusinessRules.cityNameCanNotBeDuplicated(createCityRequest.getName());

        City city = CityMapper.INSTANCE.cityFromCreateCityRequest(createCityRequest);
        city.setId(UUID.randomUUID());
        city.setCreatedDate(LocalDateTime.now());
        City createdCity = cityRepository.save(city);

        CreatedCityResponse createdCityResponse = CityMapper.INSTANCE.createdCityResponseFromCity(createdCity);
        return createdCityResponse;
    }

    public UpdatedCityResponse update(UpdateCityRequest updateCityRequest, UUID id) {
        cityBusinessRules.cityNotFound(id);
        cityBusinessRules.cityIsDeleted(id);
        cityBusinessRules.cityNameCanNotBeDuplicated(updateCityRequest.getName());

        City foundCity = cityRepository.findById(id).get();
        foundCity.setUpdatedDate(LocalDateTime.now());

        City city = CityMapper.INSTANCE.cityFromUpdateCityRequest(updateCityRequest);
        city.setId(foundCity.getId());
        city.setCreatedDate(foundCity.getCreatedDate());
        city.setUpdatedDate(foundCity.getUpdatedDate());
        City updatedCity = cityRepository.save(city);

        UpdatedCityResponse updatedCityResponse = CityMapper.INSTANCE.updatedCityResponseFromCity(updatedCity);
        return updatedCityResponse;
    }

    public DeletedCityResponse delete(UUID id) {
        cityBusinessRules.cityNotFound(id);
        cityBusinessRules.cityIsDeleted(id);

        City foundCity = cityRepository.findById(id).get();
        foundCity.setDeletedDate(LocalDateTime.now());
        City deletedCity = cityRepository.save(foundCity);

        DeletedCityResponse deletedCityResponse = CityMapper.INSTANCE.deletedCityResponseFromCity(deletedCity);
        return deletedCityResponse;
    }

    @Override
    public List<GetCityByCountryIdResponse> getByCountryId(UUID countryId) {
        List<City> cities = this.cityRepository.findByCountryId(countryId);
        List<GetCityByCountryIdResponse> getCityByCountryIdResponse = cities
                .stream().map(CityMapper.INSTANCE::getCityByCountryIdResponseFromCity).collect(Collectors.toList());
        return getCityByCountryIdResponse;
    }
}
