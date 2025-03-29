package com.turkcell.contractservice.controllers;

import com.turkcell.contractservice.dtos.requests.CreateContractRequest;
import com.turkcell.contractservice.dtos.requests.UpdateContractRequest;
import com.turkcell.contractservice.dtos.responses.ContractResponse;
import com.turkcell.contractservice.services.abstracts.ContractService;
import com.turkcell.contractservice.utils.GenericResponse;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/contracts")
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;

    @GetMapping
    public GetListResponse<ContractResponse> getAllContracts(@RequestParam int page, @RequestParam int size) {

        return contractService.getAllContracts(new PageInfo(page,size));
    }

    @GetMapping("/getOneContract")
    public ContractResponse getContractById(@RequestParam UUID id) {

        return contractService.getOneContract(id);
    }

    @PostMapping
    public GenericResponse<String> addContract(@RequestBody @Valid CreateContractRequest createContractRequest) {
        contractService.createContract(createContractRequest);
        return GenericResponse.success("generic.contract.created");
    }

    @PutMapping
    public GenericResponse<String> updateContract(@RequestParam UUID id,@RequestBody @Valid UpdateContractRequest updateContractRequest) {
        contractService.updateContract(id,updateContractRequest);
        return GenericResponse.success("generic.contract.updated");
    }

    @DeleteMapping
    public GenericResponse<String> deleteContract(@RequestParam UUID id) {
        contractService.deleteContract(id);
        return GenericResponse.success("generic.contract.deleted");
    }
}
