package com.turkcell.customerservice.services.rules;

import com.turkcell.customerservice.entities.Contact;
import com.turkcell.customerservice.repositories.ContactRepository;
import com.turkcell.customerservice.services.messages.Messages;
import io.github.ertansidar.exception.type.BusinessException;
import io.github.ertansidar.language.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ContactBusinessRules {
    private ContactRepository contactRepository;
    private MessageService messageService;

    public void contactMediumNotFound(UUID id) {
        Optional<Contact> contact = contactRepository.findById(id);

        if (contact.isEmpty()) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.CONTACT_NOT_FOUND));
        }
    }

    public void contactMediumIsDeleted(UUID id) {
        Optional<Contact> contactMedium = contactRepository.findById(id);

        if (contactMedium.isPresent() && contactMedium.get().getDeletedAt() != null) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.CONTACT_IS_DELETED));
        }
    }
}
