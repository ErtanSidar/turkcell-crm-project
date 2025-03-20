package com.turkcell.customerservice.services.mappers;

import com.turkcell.customerservice.entities.City;
import com.turkcell.customerservice.services.dtos.requests.cityRequests.CreateCityRequest;
import com.turkcell.customerservice.services.dtos.requests.cityRequests.UpdateCityRequest;
import com.turkcell.customerservice.services.dtos.responses.cityResponses.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CityMapper {

    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    GetAllCityResponse getAllCityResponseFromCity(City city);

    GetCityResponse getCityResponseFromCity(City city);

    City cityFromCreateCityRequest(CreateCityRequest createCityRequest);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "countryId", source = "country.id")
    CreatedCityResponse createdCityResponseFromCity(City city);

    City cityFromUpdateCityRequest(UpdateCityRequest updateCityRequest);

    UpdatedCityResponse updatedCityResponseFromCity(City city);
}
