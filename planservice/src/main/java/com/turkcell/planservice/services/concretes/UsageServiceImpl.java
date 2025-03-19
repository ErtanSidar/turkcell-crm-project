package com.turkcell.planservice.services.concretes;

import com.turkcell.planservice.dtos.usagedtos.responses.UsageResponse;
import com.turkcell.planservice.entities.Usage;
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

import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class UsageServiceImpl implements UsageService {

    private final UsageRepository usageRepository;
    private final UsageBusinessRules usageBusinessRules;

    public UsageServiceImpl(UsageRepository usageRepository, UsageBusinessRules usageBusinessRules) {
        this.usageRepository = usageRepository;
        this.usageBusinessRules = usageBusinessRules;
    }

    @Override
    public Usage getOneUsage(UUID id) {
        return usageRepository.findById(id).orElseThrow(
                () -> new BusinessException("Usage not found with id: " + id));
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
        log.info("Deleting Usage by ID {}", id);
        usageRepository.deleteById(id);
    }


}
