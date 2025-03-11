package com.turkcell.customerservice.services.rules;

import com.turkcell.customerservice.entities.City;
import com.turkcell.customerservice.repositories.CityRepository;
import com.turkcell.customerservice.services.messages.Messages;
import io.github.ertansidar.exception.type.BusinessException;
import io.github.ertansidar.language.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CityBusinessRules {

    private MessageService messageService;
    private CityRepository cityRepository;

    public void cityNameCanNotBeDuplicated(String name) {
        Optional<City> city = cityRepository.findByNameIgnoreCase(name);

        if (city.isPresent()) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.CITY_NAME_EXISTS));
        }
    }

    public void cityNotFound(UUID id) {
        Optional<City> city = cityRepository.findById(id);

        if (city.isEmpty()) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.CITY_NOT_FOUND));
        }
    }

    public void cityIsDeleted(UUID id) {
        Optional<City> city = cityRepository.findById(id);

        if (city.isPresent() && city.get().getDeletedDate() != null) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.CITY_IS_DELETED));
        }
    }
}
