package com.turkcell.customerservice.services.mappers;

import com.turkcell.customerservice.entities.Customer;
import com.turkcell.customerservice.services.dtos.responses.customerResponses.GetAllCustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    GetAllCustomerResponse getAllCustomerResponseFromCustomer(Customer customer);
}
