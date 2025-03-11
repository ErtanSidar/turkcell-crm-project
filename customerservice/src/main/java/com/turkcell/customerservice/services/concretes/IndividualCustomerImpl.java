package com.turkcell.customerservice.services.concretes;

import com.turkcell.customerservice.adapters.CustomerCheckService;
import com.turkcell.customerservice.entities.IndividualCustomer;
import com.turkcell.customerservice.repositories.IndividualCustomerRepository;
import com.turkcell.customerservice.services.abstracts.IndividualCustomerService;
import com.turkcell.customerservice.services.dtos.requests.individualCustomerRequests.CheckTurkishCitizenRequest;
import com.turkcell.customerservice.services.dtos.requests.individualCustomerRequests.CreateIndividualCustomerRequest;
import com.turkcell.customerservice.services.dtos.requests.individualCustomerRequests.UpdateIndividualCustomerRequest;
import com.turkcell.customerservice.services.dtos.responses.IndividualCustomerResponses.*;
import com.turkcell.customerservice.services.mappers.IndividualCustomerMapper;
import com.turkcell.customerservice.services.rules.IndividualCustomerBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class IndividualCustomerImpl implements IndividualCustomerService {
    private IndividualCustomerRepository individualCustomerRepository;
    private IndividualCustomerBusinessRules individualCustomerBusinessRules;
    private CustomerCheckService customerCheckService;

    @Override
    public CreatedIndividualCustomerResponse add(CreateIndividualCustomerRequest createIndividualCustomerRequest) throws Exception {
        String fullName = createIndividualCustomerRequest.getFirstName();
        if (!createIndividualCustomerRequest.getMiddleName().isEmpty()) {
            fullName += " " + createIndividualCustomerRequest.getMiddleName();
        }
        individualCustomerBusinessRules.checkIdNationalIdentityExists(createIndividualCustomerRequest.getNationalityId(),
                fullName,
                createIndividualCustomerRequest.getLastName(),
                createIndividualCustomerRequest.getBirthDate().getYear());
        individualCustomerBusinessRules.individualCustomerNationalityIdCannotBeDuplicated(createIndividualCustomerRequest.getNationalityId());

        IndividualCustomer individualCustomer = new IndividualCustomer();
        IndividualCustomer createdCustomer = individualCustomerRepository.save(individualCustomer);

        CreatedIndividualCustomerResponse createdIndividualCustomerResponse =
                IndividualCustomerMapper.INSTANCE.createdIndividualCustomerResponseFromIndividualCustomer(createdCustomer);
        return createdIndividualCustomerResponse;
    }

    @Override
    public List<GetAllIndividualCustomerResponse> findAll() {
        List<IndividualCustomer> allIndividualCustomers = individualCustomerRepository.findAll();

        return null;
    }

    @Override
    public boolean isIndividualCustomerExistsByNationalityId(String nationalityId) {
        Optional<IndividualCustomer> individualCustomer = individualCustomerRepository.findByNationalityIdAndDeletedDateIsNull(nationalityId);
        if (individualCustomer.isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public GetIndividualCustomerResponse findById(UUID id) {
        individualCustomerBusinessRules.individualCustomerIdMustExist(id);

        IndividualCustomer foundIndividualCustomer = individualCustomerRepository.findById(id).get();

        return IndividualCustomerMapper.INSTANCE.getIndividualCustomerFromIndividualCustomer(foundIndividualCustomer);
    }

    @Override
    public UpdatedIndividualCustomerResponse update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest, UUID id) throws Exception {
        individualCustomerBusinessRules.individualCustomerIdMustExist(id);
        individualCustomerBusinessRules.individualCustomerNationalityIdIsExist(id, updateIndividualCustomerRequest.getNationalityId());
        individualCustomerBusinessRules.checkAndFormatFullName(updateIndividualCustomerRequest);

        IndividualCustomer foundIndividualCustomer = individualCustomerRepository.findById(id).get();
        IndividualCustomer individualCustomer =
                IndividualCustomerMapper.INSTANCE.individualCustomerFromUpdateIndividualCustomerRequest(updateIndividualCustomerRequest);

        individualCustomer.setId(id);
        IndividualCustomer updatedIndividualCustomer = individualCustomerRepository.save(individualCustomer);

        return IndividualCustomerMapper.INSTANCE.updatedIndividualCustomerResponseFromIndividualCustomer(updatedIndividualCustomer);
    }

    @Override
    public DeletedIndividualCustomerResponse delete(UUID id) {
        individualCustomerBusinessRules.individualCustomerIdMustExist(id);

        IndividualCustomer foundIndividualCustomer = individualCustomerRepository.findById(id).get();

        return null;
    }

    @Override
    public boolean checkIfTurkishCitizen(CheckTurkishCitizenRequest checkTurkishCitizenRequest) throws Exception {
        return customerCheckService.checkIfRealPerson(checkTurkishCitizenRequest.getNationalityId(), checkTurkishCitizenRequest.getFirstName(),
                checkTurkishCitizenRequest.getLastName(), checkTurkishCitizenRequest.getBirthDate().getYear());
    }
}
