package com.turkcell.contractservice.services.concretes;

import com.turkcell.contractservice.dtos.requests.CreateContractRequest;
import com.turkcell.contractservice.dtos.requests.UpdateContractRequest;
import com.turkcell.contractservice.dtos.responses.ContractResponse;
import com.turkcell.contractservice.entities.Contract;
import com.turkcell.contractservice.mappers.ContractMapper;
import com.turkcell.contractservice.repositories.ContractRepository;
import com.turkcell.contractservice.services.abstracts.ContractService;
import io.github.ertansidar.exception.type.BusinessException;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import io.github.ertansidar.response.ListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;

    @Override
    public void createContract(CreateContractRequest createContractRequest) {

        Contract contract = ContractMapper
                .INSTANCE
                .createContractFromCreateContractRequest(createContractRequest);

        contractRepository.save(contract);
    }

    @Override
    public GetListResponse<ContractResponse> getAllContracts(PageInfo pageInfo) {
        return ListResponse.get(pageInfo,
                contractRepository,
                ContractMapper.INSTANCE::createContractResponseFromContract);
    }

    @Override
    public ContractResponse getOneContract(UUID id) {

        Contract contract = contractRepository.findById(id)
                .orElseThrow(()->new BusinessException("Contract with id: " + id + " not found"));

        return ContractMapper.INSTANCE.createContractResponseFromContract(contract);
    }

    @Override
    public void updateContract(UUID id, UpdateContractRequest updateContractRequest) {

        Contract contract = contractRepository.findById(id)
                .orElseThrow(()->new BusinessException("Contract with id: " + id + " not found"));

        ContractMapper.INSTANCE.updateContractFromRequest(updateContractRequest,contract);

        contractRepository.save(contract);
    }

    @Override
    public void deleteContract(UUID id) {

        Contract contract = contractRepository.findById(id)
                .orElseThrow(()->new BusinessException("Contract with id: " + id + " not found"));

        contractRepository.delete(contract);
    }
}
