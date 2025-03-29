package com.turkcell.customerservice.services.mappers;

import com.essoft.dto.address.GetAddressResponse;
import com.turkcell.customerservice.entities.Address;
import com.turkcell.customerservice.services.dtos.requests.addressRequests.CreateAddressRequest;
import com.turkcell.customerservice.services.dtos.requests.addressRequests.UpdateAddressRequest;
import com.turkcell.customerservice.services.dtos.responses.addressResponses.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    GetAllAddressResponse getAllAddressResponseFromAddress(Address address);

    GetAddressResponse getAddressResponseFromAddress(Address address);

    @Mapping(source = "createAddressRequest.customerId", target = "customer.id")
    @Mapping(source = "createAddressRequest.cityId", target = "city.id")
    @Mapping(source = "createAddressRequest.districtId", target = "district.id")
    @Mapping(source = "createAddressRequest.countryId", target = "country.id")
    Address addressFromCreateAddressRequest(CreateAddressRequest createAddressRequest);

    CreatedAddressResponse createdAddressResponseFromAddress(Address address);

    Address addressFromUpdateAddressRequest(UpdateAddressRequest updateAddressRequest);

    UpdatedAddressResponse updatedAddressResponseFromAddress(Address address);

}
