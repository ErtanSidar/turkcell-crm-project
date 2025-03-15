package com.turkcell.customerservice.services.mappers;

import com.turkcell.customerservice.entities.CorporateCustomer;
import com.turkcell.customerservice.services.dtos.responses.corporateCustomerResponses.GetAllCorporateCustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CorporateCustomerMapper {
    CorporateCustomerMapper INSTANCE = Mappers.getMapper(CorporateCustomerMapper.class);

    GetAllCorporateCustomerResponse getAllCorporateCustomerResponseFromCorporateCustomer(CorporateCustomer corporateCustomer);
}
