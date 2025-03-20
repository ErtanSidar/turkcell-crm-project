package com.turkcell.customerservice.services.mappers;

import com.turkcell.customerservice.entities.Customer;
import com.turkcell.customerservice.services.dtos.requests.customerRequests.CreateCustomerRequest;
import com.turkcell.customerservice.services.dtos.requests.customerRequests.UpdateCustomerRequest;
import com.turkcell.customerservice.services.dtos.responses.customerResponses.CreatedCustomerResponse;
import com.turkcell.customerservice.services.dtos.responses.customerResponses.GetAllCustomerResponse;
import com.turkcell.customerservice.services.dtos.responses.customerResponses.GetCustomerResponse;
import com.turkcell.customerservice.services.dtos.responses.customerResponses.UpdatedCustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    GetAllCustomerResponse getAllCustomerResponseFromCustomer(Customer customer);

    GetCustomerResponse getCustomerResponseFromCustomer(Customer customer);

    Customer customerFromCreateCustomerRequest(CreateCustomerRequest request);

    CreatedCustomerResponse createdCustomerResponseFromCustomer(Customer createdCustomer);

    Customer customerFromUpdateCustomerRequest(UpdateCustomerRequest request);

    UpdatedCustomerResponse updatedCustomerResponseFromCustomer(Customer updatedCustomer);
}
