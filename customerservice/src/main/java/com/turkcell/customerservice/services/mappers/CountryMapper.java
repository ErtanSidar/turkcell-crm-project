package com.turkcell.customerservice.services.mappers;

import com.turkcell.customerservice.entities.Country;
import com.turkcell.customerservice.services.dtos.requests.countryRequests.CreateCountryRequest;
import com.turkcell.customerservice.services.dtos.requests.countryRequests.UpdateCountryRequest;
import com.turkcell.customerservice.services.dtos.responses.countryResponses.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CountryMapper {

    CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);

    GetAllCountryResponse getAllCountryResponseFromCountry(Country country);

    GetCountryResponse getCountryResponseFromCountry(Country country);
    @Mapping(source = "name", target = "name")
    Country countryFromCreateCountryRequest(CreateCountryRequest createCountryRequest);

    CreatedCountryResponse createdCountryResponseFromCountry(Country country);

    Country countryFromUpdateCountryRequest(UpdateCountryRequest updateCountryRequest);

    UpdatedCountryResponse updatedCountryResponseFromCountry(Country country);

    DeletedCountryResponse deletedCountryResponseFromCountry(Country country);
}
