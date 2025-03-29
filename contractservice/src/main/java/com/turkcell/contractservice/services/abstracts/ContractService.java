package com.turkcell.contractservice.services.abstracts;

import com.turkcell.contractservice.dtos.requests.CreateContractRequest;
import com.turkcell.contractservice.dtos.requests.UpdateContractRequest;
import com.turkcell.contractservice.dtos.responses.ContractResponse;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;

import java.util.UUID;

public interface ContractService {
    void createContract(CreateContractRequest createContractRequest);

    GetListResponse<ContractResponse> getAllContracts(PageInfo pageInfo);

    ContractResponse getOneContract(UUID id);

    void updateContract(UUID id, UpdateContractRequest updateContractRequest);

    void deleteContract(UUID id);
}
