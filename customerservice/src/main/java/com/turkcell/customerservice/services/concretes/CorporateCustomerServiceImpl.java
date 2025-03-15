package com.turkcell.customerservice.services.concretes;

import com.turkcell.customerservice.repositories.CorporateCustomerRepository;
import com.turkcell.customerservice.services.abstracts.CorporateCustomerService;
import com.turkcell.customerservice.services.dtos.requests.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.turkcell.customerservice.services.dtos.requests.corporateCustomerRequests.UpdateCorporateCustomerRequest;
import com.turkcell.customerservice.services.dtos.responses.corporateCustomerResponses.*;
import com.turkcell.customerservice.services.mappers.CorporateCustomerMapper;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import io.github.ertansidar.response.ListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CorporateCustomerServiceImpl implements CorporateCustomerService {

    private final CorporateCustomerRepository corporateCustomerRepository;

    @Override
    public CreatedCorporateCustomerResponse add(CreateCorporateCustomerRequest request) throws Exception {
        return null;
    }

    @Override
    public GetListResponse<GetAllCorporateCustomerResponse> getAll(PageInfo pageInfo) {
        return ListResponse.get(pageInfo, corporateCustomerRepository, CorporateCustomerMapper.INSTANCE::getAllCorporateCustomerResponseFromCorporateCustomer);
    }

    @Override
    public GetCorporateCustomerResponse findById(UUID id) {
        return null;
    }

    @Override
    public UpdatedCorporateCustomerResponse update(UpdateCorporateCustomerRequest request, UUID id) throws Exception {
        return null;
    }

    @Transactional
    @Override
    public void delete(UUID id) {

    }
}
