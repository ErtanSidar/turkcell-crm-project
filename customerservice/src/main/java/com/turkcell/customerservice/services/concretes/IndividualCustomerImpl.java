package com.turkcell.customerservice.services.concretes;

import com.turkcell.customerservice.adapters.CustomerCheckService;
import com.turkcell.customerservice.entities.Customer;
import com.turkcell.customerservice.entities.IndividualCustomer;
import com.turkcell.customerservice.repositories.CustomerRepository;
import com.turkcell.customerservice.repositories.IndividualCustomerRepository;
import com.turkcell.customerservice.services.abstracts.IndividualCustomerService;
import com.turkcell.customerservice.services.dtos.requests.individualCustomerRequests.CheckTurkishCitizenRequest;
import com.turkcell.customerservice.services.dtos.requests.individualCustomerRequests.CreateIndividualCustomerRequest;
import com.turkcell.customerservice.services.dtos.requests.individualCustomerRequests.UpdateIndividualCustomerRequest;
import com.turkcell.customerservice.services.dtos.responses.IndividualCustomerResponses.*;
import com.turkcell.customerservice.services.dtos.responses.customerResponses.GetAllCustomerResponse;
import com.turkcell.customerservice.services.mappers.IndividualCustomerMapper;
import com.turkcell.customerservice.services.rules.IndividualCustomerBusinessRules;
import io.github.ertansidar.exception.type.BusinessException;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import io.github.ertansidar.response.ListResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class IndividualCustomerImpl implements IndividualCustomerService {

    private CustomerRepository customerRepository;
    private IndividualCustomerBusinessRules individualCustomerBusinessRules;
    private CustomerCheckService customerCheckService;


    @Override
    public GetListResponse<GetAllCustomerResponse> getAll(PageInfo pageInfo) {
        return ListResponse.get(pageInfo, customerRepository, CustomerMapper.INSTANCE::getAllCustomerResponseFromCustomer);
    }


    @Override
    public GetIndividualCustomerResponse findById(UUID id) {
        Customer foundedCustomer = individualCustomerRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Individual customer not found"));

        return IndividualCustomerMapper.INSTANCE.getIndividualCustomerFromIndividualCustomer(foundIndividualCustomer);
    }

    @Override
    public CreatedIndividualCustomerResponse add(CreateIndividualCustomerRequest request) {
        IndividualCustomer individualCustomer = new IndividualCustomer();
        IndividualCustomer createdCustomer = individualCustomerRepository.save(individualCustomer);

        return IndividualCustomerMapper.INSTANCE.createdIndividualCustomerResponseFromIndividualCustomer(createdCustomer);
    }

    @Override
    public UpdatedIndividualCustomerResponse update(UpdateIndividualCustomerRequest request, UUID id) {
        individualCustomerBusinessRules.individualCustomerIdMustExist(id);
        individualCustomerBusinessRules.individualCustomerNationalityIdIsExist(id, request.getNationalityId());

        IndividualCustomer foundIndividualCustomer = individualCustomerRepository.findById(id).orElseThrow(() -> new BusinessException("Individual customer not found"));
        IndividualCustomer individualCustomer =
                IndividualCustomerMapper.INSTANCE.individualCustomerFromUpdateIndividualCustomerRequest(request);

        individualCustomer.setId(id);
        IndividualCustomer updatedIndividualCustomer = individualCustomerRepository.save(individualCustomer);

        return IndividualCustomerMapper.INSTANCE.updatedIndividualCustomerResponseFromIndividualCustomer(updatedIndividualCustomer);
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        individualCustomerBusinessRules.individualCustomerIdMustExist(id);
    }

}
