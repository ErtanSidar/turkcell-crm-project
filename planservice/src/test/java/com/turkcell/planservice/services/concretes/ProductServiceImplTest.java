package com.turkcell.planservice.services.concretes;

import com.turkcell.planservice.dtos.productdtos.requests.CreateProductRequest;
import com.turkcell.planservice.dtos.productdtos.requests.UpdateProductRequest;
import com.turkcell.planservice.dtos.productdtos.responses.ProductResponse;
import com.turkcell.planservice.entities.Product;
import com.turkcell.planservice.repositories.ProductRepository;
import com.turkcell.planservice.rules.ProductBusinessRules;
import com.turkcell.planservice.rules.SubscriptionBusinessRules;
import io.github.ertansidar.audit.AuditAwareImpl;
import io.github.ertansidar.exception.type.BusinessException;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private SubscriptionBusinessRules subscriptionBusinessRules;

    @Mock
    private ProductBusinessRules productBusinessRules;

    @Mock
    private AuditAwareImpl auditAware;

    private ProductServiceImpl productService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductServiceImpl(
                productRepository,
                subscriptionBusinessRules,
                productBusinessRules,
                auditAware
        );
    }

    @Test
    void getOneProduct_WhenProductExists_ReturnsProductResponse() {
        UUID productId = UUID.randomUUID();
        Product product = new Product();
        product.setId(productId);

        when(productRepository.findById(productId))
                .thenReturn(Optional.of(product));

        // Act
        ProductResponse response = productService.getOneProduct(productId);

        // Assert
        assertNotNull(response);
        verify(productRepository).findById(productId);
    }
    @Test
    void getOneProduct_WhenProductNotFound_ShouldThrowBusinessException() {
        // Arrange
        UUID productId = UUID.randomUUID();

        when(productRepository.findById(productId))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(BusinessException.class,
                () -> productService.getOneProduct(productId),
                "Product with id: " + productId + " not found"
        );
    }

    @Test
    void createProduct_WithValidRequest_ShouldSaveProduct() {
        CreateProductRequest request = new CreateProductRequest();
        request.setPlanId(UUID.randomUUID());
        request.setPackageId(UUID.randomUUID());

        doNothing().when(productBusinessRules)
                .checkIfProductHasValidPlanAndPackage(
                        request.getPlanId().toString(),
                        request.getPackageId().toString()
                );

        // Act
        productService.createProduct(request);

        // Assert
        verify(productBusinessRules)
                .checkIfProductHasValidPlanAndPackage(
                        request.getPlanId().toString(),
                        request.getPackageId().toString()
                );
        verify(productRepository).save(any(Product.class));
    }

    @Test
    void updateProduct_WhenProductFound_ShouldSaveProduct() {
        UUID productId = UUID.randomUUID();
        UpdateProductRequest updateRequest = new UpdateProductRequest();
        Product existingProduct = new Product();

        doNothing().when(productBusinessRules)
                .checkIfProductExists(productId);

        when(productRepository.findById(productId))
                .thenReturn(Optional.of(existingProduct));

        // Act
        productService.updateProduct(productId, updateRequest);

        // Assert
        verify(productBusinessRules).checkIfProductExists(productId);
        verify(productRepository).save(existingProduct);
    }

    @Test
    void updateProduct_WhenProductNotFound_ShouldThrowBusinessException() {
        // Arrange
        UUID productId = UUID.randomUUID();
        UpdateProductRequest updateRequest = new UpdateProductRequest();

        doNothing().when(productBusinessRules)
                .checkIfProductExists(productId);

        when(productRepository.findById(productId))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(BusinessException.class,
                () -> productService.updateProduct(productId, updateRequest),
                "Product with id: " + productId + " not found"
        );
    }

    @Test
    void getAllProducts_shouldReturnProductList() {

        PageInfo pageInfo = new PageInfo();
        pageInfo.setPage(0);
        pageInfo.setSize(10);

        // Create mock products
        Product product1 = new Product();
        product1.setId(UUID.randomUUID());
        Product product2 = new Product();
        product2.setId(UUID.randomUUID());
        List<Product> products = Arrays.asList(product1, product2);

        // Create a mock Page
        Page<Product> productPage = new PageImpl<>(products,
                PageRequest.of(pageInfo.getPage(), pageInfo.getSize()),
                products.size()
        );

        // Mock the repository findAll method
        when(productRepository.findAll(any(PageRequest.class)))
                .thenReturn(productPage);

        // Act
        GetListResponse<ProductResponse> response =
                productService.getAllProducts(pageInfo);

        // Assert
        assertNotNull(response);
        // You might want to add more specific assertions based on your implementation
        verify(productRepository).findAll(any(PageRequest.class));
    }

    @Test
    void deleteById_ShouldSoftDeletePlan() {
        UUID productId = UUID.randomUUID();

        doNothing().when(productBusinessRules)
                .checkIfProductExists(productId);

        // Act
        productService.delete(productId);

        // Assert
        verify(productBusinessRules).checkIfProductExists(productId);
        verify(productRepository).softDelete(
                eq(productId),
                any(LocalDateTime.class),
                anyString()
        );
    }
}