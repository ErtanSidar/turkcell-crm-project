package com.turkcell.customerservice.services.mappers;

import com.turkcell.customerservice.entities.Address;
import com.turkcell.customerservice.services.dtos.requests.addressRequests.CreateAddressRequest;
import com.turkcell.customerservice.services.dtos.requests.addressRequests.UpdateAddressRequest;
import com.turkcell.customerservice.services.dtos.responses.addressResponses.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    GetAllAddressResponse getAllAddressResponseFromAddress(Address address);

    GetAddressResponse getAddressResponseFromAddress(Address address);

    Address addressFromCreateAddressRequest(CreateAddressRequest createAddressRequest);

    CreatedAddressResponse createdAddressResponseFromAddress(Address address);

    Address addressFromUpdateAddressRequest(UpdateAddressRequest updateAddressRequest);

    UpdatedAddressResponse updatedAddressResponseFromAddress(Address address);

    DeletedAddressResponse deletedAddressResponseFromAddress(Address address);

    GetAddressByCustomerIdResponse getAddressByCustomerIdResponseFromAddress(Address address);
}
