package com.turkcell.planservice.services.concretes;

import com.essoft.dto.customer.GetCustomerResponse;
import com.essoft.event.usage.UsageCreatedEvent;
import com.turkcell.planservice.client.CustomerClient;
import com.turkcell.planservice.dtos.productdtos.responses.ProductResponse;
import com.turkcell.planservice.dtos.usagedtos.requests.CreateUsageRequest;
import com.turkcell.planservice.dtos.usagedtos.requests.UpdateUsageRequest;
import com.turkcell.planservice.dtos.usagedtos.responses.UsageResponse;
import com.turkcell.planservice.entities.Product;
import com.turkcell.planservice.entities.Usage;
import com.turkcell.planservice.kafka.UsageCreatedProducer;
import com.turkcell.planservice.mappers.ProductMapper;
import com.turkcell.planservice.mappers.UsageMapper;
import com.turkcell.planservice.repositories.UsageRepository;
import com.turkcell.planservice.rules.UsageBusinessRules;
import com.turkcell.planservice.services.abstracts.ProductService;
import com.turkcell.planservice.services.abstracts.UsageService;
import io.github.ertansidar.audit.AuditAwareImpl;
import io.github.ertansidar.exception.type.BusinessException;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import io.github.ertansidar.response.ListResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class UsageServiceImpl implements UsageService {

    private final UsageRepository usageRepository;
    private final UsageBusinessRules usageBusinessRules;
    private final AuditAwareImpl auditAware;
    private final CustomerClient customerClient;
    private final ProductService productService;
    private final UsageCreatedProducer usageCreatedProducer;

    public UsageServiceImpl(UsageRepository usageRepository, UsageBusinessRules usageBusinessRules, AuditAwareImpl auditAware, CustomerClient customerClient, ProductService productService, UsageCreatedProducer usageCreatedProducer) {
        this.usageRepository = usageRepository;
        this.usageBusinessRules = usageBusinessRules;
        this.auditAware = auditAware;
        this.customerClient = customerClient;
        this.productService = productService;
        this.usageCreatedProducer = usageCreatedProducer;
    }

    @Override
    public UsageResponse getOneUsage(UUID id) {
        Usage usage = usageRepository.findById(id).orElseThrow(
                () -> new BusinessException("Usage not found with id: " + id));

        UsageResponse usageResponse = UsageMapper.INSTANCE.createUsageResponseFromUsage(usage);

        return usageResponse;
    }

    @Override
    public void createUsage(CreateUsageRequest createUsageRequest) {

        GetCustomerResponse customerResponse = customerClient.findById(createUsageRequest.getCustomerId());
        if (customerResponse == null) {
            throw new BusinessException("Customer not found with ID: " + createUsageRequest.getCustomerId());
        }
        // Validate if the product exists
        ProductResponse productResponse = productService.getOneProduct(createUsageRequest.getProductId());
        if (productResponse == null) {
            throw new BusinessException("Product not found with ID: " + createUsageRequest.getProductId());
        }
        Product product = ProductMapper.INSTANCE.createProductFromProductResponse(productResponse);

        log.info("Product found: " + productResponse.getProductName());

        // Map request to Usage entity
        Usage usage = UsageMapper.INSTANCE.createUsageFromCreateUsageRequest(createUsageRequest);
        usage.setProduct(product);

        // Save to database
        usageRepository.save(usage);

        log.info("Usage record created successfully for customer ID: " + createUsageRequest.getCustomerId());

        UsageCreatedEvent event = new UsageCreatedEvent();
        event.setCustomerId(createUsageRequest.getCustomerId());
        event.setInternetUsed(createUsageRequest.getInternetUsed());
        event.setCallMinutesUsed(createUsageRequest.getCallMinutesUsed());
        event.setSmsUsed(createUsageRequest.getSmsUsed());
        event.setTvHoursWatched(createUsageRequest.getTvHoursWatched());
        event.setProductId(createUsageRequest.getProductId());
        usageCreatedProducer.sendMessage(event);

    }

    @Override
    public void updateUsage(UUID id, UpdateUsageRequest updateUsageRequest) {
        // Fetch existing usage entity
        Usage usage = usageRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Usage record not found with ID: " + id));

        log.info("Updating usage record with ID: " + id);

        // Map the new usage details (excluding customer and product)
        UsageMapper.INSTANCE.updateUsageFromUpdateUsageRequest(usage, updateUsageRequest);

        // Save the updated entity
        usageRepository.save(usage);

        log.info("Usage record updated successfully for ID: " + id);

    }

    @Override
    public GetListResponse<UsageResponse> getAllUsages(PageInfo pageInfo) {
        return ListResponse.get(pageInfo,
                usageRepository,
                UsageMapper.INSTANCE::createUsageResponseFromUsage);
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        usageBusinessRules.checkIfUsageExists(id);
        log.info("Deleting Usage by ID {}", id);
        usageRepository.softDelete(id, LocalDateTime.now(), auditAware.getCurrentAuditor().toString());
    }


}
