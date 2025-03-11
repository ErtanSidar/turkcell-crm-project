package com.turkcell.customerservice.services.rules;

import com.turkcell.customerservice.entities.Address;
import com.turkcell.customerservice.repositories.AddressRepository;
import com.turkcell.customerservice.services.messages.Messages;
import io.github.ertansidar.exception.type.BusinessException;
import io.github.ertansidar.language.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AddressBusinessRules {

    private AddressRepository addressRepository;
    private MessageService messageService;

    public void addressNotFound(UUID id) {
        Optional<Address> address = addressRepository.findById(id);

        if (address.isEmpty()) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.ADDRESS_NOT_FOUND));
        }
    }

    public void addressIsDeleted(UUID id) {
        Optional<Address> address = addressRepository.findById(id);

        if (address.isPresent() && address.get().getDeletedDate() != null) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.ADDRESS_IS_DELETED));
        }
    }
}
