package com.turkcell.customerservice.services.rules;

import com.turkcell.customerservice.adapters.CustomerCheckService;
import com.turkcell.customerservice.entities.Customer;
import com.turkcell.customerservice.repositories.CustomerRepository;
import com.turkcell.customerservice.services.messages.Messages;
import io.github.ertansidar.exception.type.BusinessException;
import io.github.ertansidar.language.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerBusinessRules {

    private final MessageService messageService;
    private final CustomerRepository customerRepository;
    private final CustomerCheckService customerCheckService;

    public void checkCustomerNationalityIdIsUnique(String nationalityId) {
        Optional<Customer> customer = customerRepository.findByNationalityId(nationalityId);
        if (customer.isPresent()) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.NATIONALITY_ID_EXISTS));
        }
    }

    public void checkCustomerIdExists(UUID id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.CUSTOMER_NOT_FOUND));
        }
    }
}
