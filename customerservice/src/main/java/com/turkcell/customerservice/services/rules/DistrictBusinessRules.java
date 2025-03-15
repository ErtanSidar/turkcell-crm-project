package com.turkcell.customerservice.services.rules;

import com.turkcell.customerservice.entities.District;
import com.turkcell.customerservice.repositories.DistrictRepository;
import com.turkcell.customerservice.services.messages.Messages;
import io.github.ertansidar.exception.type.BusinessException;
import io.github.ertansidar.language.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DistrictBusinessRules {
    private MessageService messageService;
    private DistrictRepository districtRepository;

    public void districtNameCanNotBeDuplicated(String name) {
        Optional<District> district = districtRepository.findByNameIgnoreCase(name);

        if (district.isPresent()) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.DISTRICT_NAME_EXISTS));
        }
    }

    public void districtNotFound(UUID id) {
        Optional<District> district = districtRepository.findById(id);

        if (district.isEmpty()) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.DISTRICT_NOT_FOUND));
        }
    }

    public void districtIsDeleted(UUID id) {
        Optional<District> district = districtRepository.findById(id);

        if (district.isPresent() && district.get().getDeletedAt() != null) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.DISTRICT_IS_DELETED));
        }
    }
}
