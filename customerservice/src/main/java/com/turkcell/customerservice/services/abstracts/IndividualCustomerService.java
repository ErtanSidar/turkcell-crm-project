package com.turkcell.customerservice.services.abstracts;

import com.turkcell.customerservice.services.dtos.requests.individualCustomerRequests.CheckTurkishCitizenRequest;
import com.turkcell.customerservice.services.dtos.requests.individualCustomerRequests.CreateIndividualCustomerRequest;
import com.turkcell.customerservice.services.dtos.requests.individualCustomerRequests.UpdateIndividualCustomerRequest;
import com.turkcell.customerservice.services.dtos.responses.IndividualCustomerResponses.CreatedIndividualCustomerResponse;
import com.turkcell.customerservice.services.dtos.responses.IndividualCustomerResponses.GetAllIndividualCustomerResponse;
import com.turkcell.customerservice.services.dtos.responses.IndividualCustomerResponses.GetIndividualCustomerResponse;
import com.turkcell.customerservice.services.dtos.responses.IndividualCustomerResponses.UpdatedIndividualCustomerResponse;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;

import java.util.UUID;

public interface IndividualCustomerService {
    CreatedIndividualCustomerResponse add(CreateIndividualCustomerRequest request) throws Exception;

    GetListResponse<GetAllIndividualCustomerResponse> getAll(PageInfo pageInfo);

    GetIndividualCustomerResponse findById(UUID id);

    boolean isIndividualCustomerExistsByNationalityId(String nationalityId);

    UpdatedIndividualCustomerResponse update(UpdateIndividualCustomerRequest request, UUID id) throws Exception;

    void delete(UUID id);

    boolean checkIfTurkishCitizen(CheckTurkishCitizenRequest request) throws Exception;
}
