package com.turkcell.customerservice.services.concretes;

import com.turkcell.customerservice.entities.CustomerType;
import com.turkcell.customerservice.entities.IndividualCustomer;
import com.turkcell.customerservice.repositories.CustomerRepository;
import com.turkcell.customerservice.services.abstracts.CustomerService;
import com.turkcell.customerservice.services.dtos.requests.customerRequests.CreateCustomerRequest;
import com.turkcell.customerservice.services.dtos.requests.customerRequests.UpdateCustomerRequest;
import com.turkcell.customerservice.services.dtos.responses.customerResponses.CreatedCustomerResponse;
import com.turkcell.customerservice.services.dtos.responses.customerResponses.GetAllCustomerResponse;
import com.turkcell.customerservice.services.dtos.responses.customerResponses.GetCustomerResponse;
import com.turkcell.customerservice.services.dtos.responses.customerResponses.UpdatedCustomerResponse;
import com.turkcell.customerservice.services.mappers.CustomerMapper;
import com.turkcell.customerservice.services.mappers.IndividualCustomerMapper;
import com.turkcell.customerservice.services.rules.CustomerBusinessRules;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import io.github.ertansidar.response.ListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerBusinessRules customerBusinessRules;

    @Override
    public CreatedCustomerResponse add(CreateCustomerRequest request) {
        if (request.getCustomerType() == CustomerType.INDIVIDUAL) {
            checkCustomerIsRealPerson(request.getFirstName(),
                    request.getLastName(),
                    request.getNationalityId(),
                    request.getBirthDate().getYear());
        }
        customerBusinessRules.checkIdNationalIdentityExists(request.getNationalityId(),
                fullName,
                request.getLastName(),
                request.getBirthDate().getYear());
        individualCustomerBusinessRules.individualCustomerNationalityIdCannotBeDuplicated(request.getNationalityId());

        IndividualCustomer individualCustomer = new IndividualCustomer();
        IndividualCustomer createdCustomer = individualCustomerRepository.save(individualCustomer);

        return IndividualCustomerMapper.INSTANCE.createdIndividualCustomerResponseFromIndividualCustomer(createdCustomer);
    }

    @Override
    public GetListResponse<GetAllCustomerResponse> getAll(PageInfo pageInfo) {
        return ListResponse.get(pageInfo, customerRepository, CustomerMapper.INSTANCE::getAllCustomerResponseFromCustomer);
    }

    @Override
    public GetCustomerResponse findById(UUID id) {
        return null;
    }

    @Override
    public UpdatedCustomerResponse update(UpdateCustomerRequest request, UUID id) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
