package com.turkcell.contractservice.services.concretes;

import com.turkcell.contractservice.dtos.requests.CreateContractRequest;
import com.turkcell.contractservice.repositories.ContractRepository;
import com.turkcell.contractservice.services.abstracts.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;

    @Override
    public void createContract(CreateContractRequest createContractRequest) {

    }
}
