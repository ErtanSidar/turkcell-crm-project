package com.turkcell.planservice.services.concretes;


import com.essoft.event.product.ProductCreatedEvent;
import com.turkcell.planservice.dtos.plandtos.responses.PlanResponse;
import com.turkcell.planservice.dtos.productdtos.requests.CreateProductRequest;
import com.turkcell.planservice.dtos.productdtos.requests.UpdateProductRequest;
import com.turkcell.planservice.dtos.productdtos.responses.ProductResponse;
import com.turkcell.planservice.dtos.subscriptiondtos.responses.SubscriptionResponse;
import com.turkcell.planservice.entities.Plan;
import com.turkcell.planservice.entities.Product;
import com.turkcell.planservice.entities.Subscription;
import com.turkcell.planservice.kafka.ProductCreatedProducer;
import com.turkcell.planservice.mappers.PlanMapper;
import com.turkcell.planservice.mappers.ProductMapper;
import com.turkcell.planservice.mappers.SubscriptionMapper;
import com.turkcell.planservice.repositories.ProductRepository;
import com.turkcell.planservice.rules.ProductBusinessRules;
import com.turkcell.planservice.rules.SubscriptionBusinessRules;
import com.turkcell.planservice.services.abstracts.PlanService;
import com.turkcell.planservice.services.abstracts.ProductService;
import com.turkcell.planservice.services.abstracts.SubscriptionService;
import io.github.ertansidar.audit.AuditAwareImpl;
import io.github.ertansidar.exception.type.BusinessException;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import io.github.ertansidar.response.ListResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final SubscriptionBusinessRules subscriptionBusinessRules;
    private final ProductBusinessRules productBusinessRules;
    private final AuditAwareImpl auditAware;
    private final ProductCreatedProducer productCreatedProducer;
    private final PlanService planService;
    private final SubscriptionService subscriptionService;

    public ProductServiceImpl(ProductRepository productRepository, SubscriptionBusinessRules subscriptionBusinessRules, ProductBusinessRules productBusinessRules, AuditAwareImpl auditAware, ProductCreatedProducer productCreatedProducer, PlanService planService, SubscriptionService subscriptionService) {
        this.productRepository = productRepository;
        this.subscriptionBusinessRules = subscriptionBusinessRules;
        this.productBusinessRules = productBusinessRules;
        this.auditAware = auditAware;
        this.productCreatedProducer = productCreatedProducer;
        this.planService = planService;
        this.subscriptionService = subscriptionService;
    }

    @Override
    public ProductResponse getOneProduct(UUID id) {
        log.info("Getting one product with id: " + id);
        Product product=productRepository.findById(id).orElseThrow(
                ()-> new BusinessException("Product with id: " + id + " not found"));

        return ProductMapper.INSTANCE.createProductResponseFromProduct(product);
    }



    @Override
    public void createProduct(CreateProductRequest createProductRequest) {
        productBusinessRules.checkIfProductHasValidPlanAndPackage(
                createProductRequest.getPlanId().toString());
        log.info("Creating product: " + createProductRequest.getProductName());
        Product product = ProductMapper.INSTANCE.createProductFromCreateProductRequest(createProductRequest);

        PlanResponse planResponse = planService.getOnePlan(createProductRequest.getPlanId());
        Plan plan = PlanMapper.INSTANCE.createPlanFromPlanResponse(planResponse);
        product.setPlan(plan);

        SubscriptionResponse subscriptionResponse = subscriptionService.getOneSubs(createProductRequest.getSubscriptionId());
        Subscription subscription = SubscriptionMapper.INSTANCE.createSubscriptionFromSubscriptionResponse(subscriptionResponse);
        product.setSubscription(subscription);


        productRepository.save(product);

        ProductCreatedEvent event = new ProductCreatedEvent();
        event.setProductName(createProductRequest.getProductName());
        event.setDescription(createProductRequest.getDescription());
        event.setProductType(createProductRequest.getProductType());
        event.setPlanId(createProductRequest.getPlanId());
        event.setSubscriptionId(createProductRequest.getSubscriptionId());
        productCreatedProducer.sendMessage(event);

    }

    @Override
    public void updateProduct(UUID id, UpdateProductRequest updateProductRequest) {
        productBusinessRules.checkIfProductExists(id);
        log.info("Updating product: " + updateProductRequest.getProductName());

        Product product = productRepository.findById(id).orElseThrow(
                () -> new BusinessException("Product with id: " + id + " not found"));
        log.info("Product found: " + product.getProductName());

        ProductMapper.INSTANCE.updateProductFromRequest(updateProductRequest, product);
        productRepository.save(product);

    }


    @Override
    public GetListResponse<ProductResponse> getAllProducts(PageInfo pageInfo) {
        return ListResponse.get(pageInfo,
                productRepository,
                ProductMapper.INSTANCE::createProductResponseFromProduct);
    }

    @Transactional
    @Override
    public void delete(UUID id) {
//        subscriptionBusinessRules.checkIfProductCanBeDeleted(id);
        productBusinessRules.checkIfProductExists(id);
        log.info("Deleting product: " + id);
        productRepository.softDelete(id, LocalDateTime.now(), auditAware.getCurrentAuditor().toString());
    }




}
