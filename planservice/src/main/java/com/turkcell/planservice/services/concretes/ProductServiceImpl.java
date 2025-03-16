package com.turkcell.planservice.services.concretes;


import com.turkcell.planservice.dtos.productdtos.requests.CreateProductRequest;
import com.turkcell.planservice.dtos.productdtos.requests.UpdateProductRequest;
import com.turkcell.planservice.dtos.productdtos.responses.ProductResponse;
import com.turkcell.planservice.entities.Product;
import com.turkcell.planservice.mappers.ProductMapper;
import com.turkcell.planservice.repositories.ProductRepository;
import com.turkcell.planservice.rules.ProductBusinessRules;
import com.turkcell.planservice.rules.SubscriptionBusinessRules;
import com.turkcell.planservice.services.abstracts.ProductService;
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

import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final SubscriptionBusinessRules subscriptionBusinessRules;
    private final ProductBusinessRules productBusinessRules;

    public ProductServiceImpl(ProductRepository productRepository, SubscriptionBusinessRules subscriptionBusinessRules, ProductBusinessRules productBusinessRules) {
        this.productRepository = productRepository;
        this.subscriptionBusinessRules = subscriptionBusinessRules;
        this.productBusinessRules = productBusinessRules;
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
                createProductRequest.getPlanId().toString(),
                createProductRequest.getPackageId().toString());
        log.info("Creating product: " + createProductRequest.getProductName());
        Product product = ProductMapper.INSTANCE.createProductFromCreateProductRequest(createProductRequest);
        productRepository.save(product);

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

    @Override
    public void deleteById(UUID id) {
//        subscriptionBusinessRules.checkIfProductCanBeDeleted(id);
        productBusinessRules.checkIfProductExists(id);
        log.info("Deleting product: " + id);
        productRepository.deleteById(id);
    }




}
