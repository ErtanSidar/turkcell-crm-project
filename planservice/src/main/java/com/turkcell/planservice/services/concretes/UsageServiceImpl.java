package com.turkcell.planservice.services.concretes;

import com.turkcell.planservice.dtos.usagedtos.requests.CreateUsageRequest;
import com.turkcell.planservice.dtos.usagedtos.responses.UsageResponse;
import com.turkcell.planservice.entities.Usage;
import com.turkcell.planservice.events.dtos.customerdtos.response.GetCustomerResponse;
import com.turkcell.planservice.feign.CustomerClient;
import com.turkcell.planservice.mappers.UsageMapper;
import com.turkcell.planservice.repositories.UsageRepository;
import com.turkcell.planservice.rules.UsageBusinessRules;
import com.turkcell.planservice.services.abstracts.UsageService;
import io.github.ertansidar.exception.type.BusinessException;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import io.github.ertansidar.response.ListResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UsageServiceImpl implements UsageService {

    private final UsageRepository usageRepository;
    private final UsageBusinessRules usageBusinessRules;
    private final CustomerClient customerClient;

    public UsageServiceImpl(UsageRepository usageRepository, UsageBusinessRules usageBusinessRules, CustomerClient customerClient) {
        this.usageRepository = usageRepository;
        this.usageBusinessRules = usageBusinessRules;
        this.customerClient = customerClient;
    }

    @Override
    public Usage getOneUsage(UUID id) {
        return usageRepository.findById(id).orElseThrow(
                () -> new BusinessException("Usage not found with id: " + id));
    }

    @Override
    public void createUsage(CreateUsageRequest createUsageRequest) {

        GetCustomerResponse customerResponse;
        try {
            customerResponse = customerClient.findById(createUsageRequest.getCustomerId());
        } catch (Exception e) {
            throw new RuntimeException("Customer Service is not available or customer not found: " + e.getMessage());
        }

        Usage usage = UsageMapper.INSTANCE.createUsageFromCreateUsageRequest(createUsageRequest);
        usage.setCustomerId(customerResponse.getId());
        usageRepository.save(usage);

    }

    @Override
    public GetListResponse<UsageResponse> getAllUsages(PageInfo pageInfo) {
        return ListResponse.get(pageInfo,
                usageRepository,
                UsageMapper.INSTANCE::createUsageResponseFromUsage);
    }

    @Override
    public void deleteById(UUID id) {
        usageBusinessRules.checkIfUsageExists(id);
        usageRepository.softDelete(id, LocalDateTime.now(),AuditServiceImpl.USER);
    }


}
