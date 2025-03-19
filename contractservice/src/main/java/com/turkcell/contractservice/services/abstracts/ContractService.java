package com.turkcell.contractservice.services.abstracts;

import com.turkcell.contractservice.dtos.requests.CreateContractRequest;

public interface ContractService {
    void createContract(CreateContractRequest createContractRequest);
}
