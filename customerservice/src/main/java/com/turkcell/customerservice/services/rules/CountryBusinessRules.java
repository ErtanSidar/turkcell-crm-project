package com.turkcell.customerservice.services.rules;

import com.turkcell.customerservice.entities.Country;
import com.turkcell.customerservice.repositories.CountryRepository;
import com.turkcell.customerservice.services.messages.Messages;
import io.github.ertansidar.exception.type.BusinessException;
import io.github.ertansidar.language.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CountryBusinessRules {
    private MessageService messageService;
    private CountryRepository countryRepository;

    public void checkCountryNameIsUnique(String name) {
        Optional<Country> country = countryRepository.findByNameIgnoreCase(name);

        if (country.isPresent()) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.COUNTRY_NAME_EXISTS));
        }
    }

    public void checkCountryIdExists(UUID id) {
        Optional<Country> country = countryRepository.findById(id);

        if (country.isEmpty()) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.COUNTRY_NOT_FOUND));
        }
    }

    public void checkCountryIsDeleted(UUID id) {
        Optional<Country> country = countryRepository.findById(id);

        if (country.isPresent() && country.get().getDeletedAt() != null) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.COUNTRY_IS_DELETED));
        }
    }
}
