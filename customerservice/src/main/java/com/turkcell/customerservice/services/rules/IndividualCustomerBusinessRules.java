package com.turkcell.customerservice.services.rules;

import com.turkcell.customerservice.adapters.CustomerCheckService;
import com.turkcell.customerservice.entities.IndividualCustomer;
import com.turkcell.customerservice.repositories.IndividualCustomerRepository;
import com.turkcell.customerservice.services.dtos.requests.individualCustomerRequests.UpdateIndividualCustomerRequest;
import com.turkcell.customerservice.services.messages.Messages;
import io.github.ertansidar.exception.type.BusinessException;
import io.github.ertansidar.language.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class IndividualCustomerBusinessRules {
    private MessageService messageService;
    private IndividualCustomerRepository individualCustomerRepository;
    private CustomerCheckService customerCheckService;

    public void individualCustomerNationalityIdCannotBeDuplicated(String nationalityId) {
        //Optional<IndividualCustomer> individualCustomer = individualCustomerRepository.findByNationalityIdAndDeletedAtIsNull(nationalityId);
//        if(individualCustomer.isPresent() && individualCustomer.get().getCustomer().getDeletedDate() == null){
//            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.NATIONALITY_ID_EXISTS));
//        }
    }

    public void individualCustomerNationalityIdIsExist(UUID id, String nationalityId) {
//        Optional<IndividualCustomer> individualCustomer = individualCustomerRepository.findByNationalityIdAndDeletedAtIsNull(nationalityId);
//        if(individualCustomer.isPresent()){
//            if(!individualCustomer.get().getId().equals(id)){
//                throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.NATIONALITY_ID_EXISTS));
//            }
//        }
    }

    public void individualCustomerIdMustExist(UUID id) {
        Optional<IndividualCustomer> individualCustomer = individualCustomerRepository.findById(id);
//        if(!individualCustomer.isPresent() || individualCustomer.get().getCustomer().getDeletedDate() != null) {
//            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.INDIVIDUAL_CUSTOMER_NOT_FOUND));
//        }
    }

    public void checkIdNationalIdentityExists(String nationalityId,
                                              String firstName,
                                              String lastName,
                                              int birthDate) throws Exception {
        if(!customerCheckService.checkIfRealPerson(nationalityId, firstName, lastName, birthDate)){
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.NATIONALITY_ID_COULD_NOT_BE_VERIFIED));
        }
    }

    public void checkAndFormatFullName(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) throws Exception {
        String fullName = updateIndividualCustomerRequest.getFirstName();
        if (!updateIndividualCustomerRequest.getMiddleName().isEmpty()) {
            fullName += " " + updateIndividualCustomerRequest.getMiddleName();
        }
        checkIdNationalIdentityExists(updateIndividualCustomerRequest.getNationalityId(),
                fullName,
                updateIndividualCustomerRequest.getLastName(),
                updateIndividualCustomerRequest.getBirthDate().getYear());
    }

}
