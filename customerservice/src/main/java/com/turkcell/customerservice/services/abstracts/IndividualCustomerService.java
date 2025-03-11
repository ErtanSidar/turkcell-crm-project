package com.turkcell.customerservice.services.abstracts;

import com.turkcell.customerservice.services.dtos.requests.individualCustomerRequests.CheckTurkishCitizenRequest;
import com.turkcell.customerservice.services.dtos.requests.individualCustomerRequests.CreateIndividualCustomerRequest;
import com.turkcell.customerservice.services.dtos.requests.individualCustomerRequests.UpdateIndividualCustomerRequest;
import com.turkcell.customerservice.services.dtos.responses.IndividualCustomerResponses.*;

import java.util.List;
import java.util.UUID;

public interface IndividualCustomerService {
    CreatedIndividualCustomerResponse add(CreateIndividualCustomerRequest request) throws Exception;

    List<GetAllIndividualCustomerResponse> findAll();

    GetIndividualCustomerResponse findById(UUID id);

    boolean isIndividualCustomerExistsByNationalityId(String nationalityId);

    UpdatedIndividualCustomerResponse update(UpdateIndividualCustomerRequest request, UUID id) throws Exception;

    DeletedIndividualCustomerResponse delete(UUID id);

    boolean checkIfTurkishCitizen(CheckTurkishCitizenRequest checkTurkishCitizenRequest) throws Exception;
}
