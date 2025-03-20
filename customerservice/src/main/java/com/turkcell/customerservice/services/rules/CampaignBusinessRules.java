package com.turkcell.customerservice.services.rules;

import com.turkcell.customerservice.entities.Campaign;
import com.turkcell.customerservice.entities.City;
import com.turkcell.customerservice.repositories.CampaignRepository;
import com.turkcell.customerservice.services.messages.Messages;
import io.github.ertansidar.exception.type.BusinessException;
import io.github.ertansidar.language.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CampaignBusinessRules {

    private CampaignRepository campaignRepository;
    private MessageService messageService;

    public void checkCampaignIdExists(UUID id) {
        Optional<Campaign> campaign = campaignRepository.findById(id);
        if (campaign.isEmpty()) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.CAMPAIGN_NOT_FOUND));
        }
    }
}
