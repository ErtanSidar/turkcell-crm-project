package com.turkcell.contractservice.controllers;

import com.turkcell.contractservice.dtos.requests.CreateContractRequest;
import com.turkcell.contractservice.dtos.responses.ContractResponse;
import com.turkcell.contractservice.services.abstracts.ContractService;
import com.turkcell.contractservice.util.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/contracts")
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;

    @PostMapping
    public GenericResponse<String> addContract(@RequestBody CreateContractRequest createContractRequest) {

        contractService.createContract(createContractRequest);
        return GenericResponse.success("generic.contract.created");

    }
}
