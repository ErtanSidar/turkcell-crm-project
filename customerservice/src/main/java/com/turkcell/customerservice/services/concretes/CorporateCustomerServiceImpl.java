package com.turkcell.customerservice.services.concretes;

import com.turkcell.customerservice.repositories.CorporateCustomerRepository;
import com.turkcell.customerservice.services.abstracts.CorporateCustomerService;
import com.turkcell.customerservice.services.dtos.requests.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.turkcell.customerservice.services.dtos.requests.corporateCustomerRequests.UpdateCorporateCustomerRequest;
import com.turkcell.customerservice.services.dtos.responses.corporateCustomerResponses.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CorporateCustomerServiceImpl implements CorporateCustomerService {

    private final CorporateCustomerRepository corporateCustomerRepository;

    @Override
    public CreatedCorporateCustomerResponse add(CreateCorporateCustomerRequest createCorporateCustomerRequest) throws Exception {
        return null;
    }

    @Override
    public List<GetAllCorporateCustomerResponse> findAll() {
        return List.of();
    }

    @Override
    public GetCorporateCustomerResponse findById(UUID id) {
        return null;
    }

    @Override
    public UpdatedCorporateCustomerResponse update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest, UUID id) throws Exception {
        return null;
    }

    @Override
    public DeletedCorporateCustomerResponse delete(UUID id) {
        return null;
    }
}
