package com.turkcell.contractservice.mappers;

import com.turkcell.contractservice.dtos.requests.CreateContractRequest;
import com.turkcell.contractservice.dtos.requests.UpdateContractRequest;
import com.turkcell.contractservice.dtos.responses.ContractResponse;
import com.turkcell.contractservice.entities.Contract;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContractMapper {

    ContractMapper INSTANCE = Mappers.getMapper(ContractMapper.class);

    ContractResponse createContractResponseFromContract(Contract contract);

    Contract createContractFromCreateContractRequest(CreateContractRequest createContractRequest);

    void updateContractFromRequest(UpdateContractRequest request, @MappingTarget Contract contract);
}
