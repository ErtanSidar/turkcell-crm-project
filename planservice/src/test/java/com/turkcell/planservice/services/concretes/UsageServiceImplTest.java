package com.turkcell.planservice.services.concretes;

import com.essoft.dto.customer.GetCorporateCustomerResponse;
import com.essoft.dto.customer.GetCustomerResponse;
import com.essoft.dto.customer.GetIndividualCustomerResponse;
import com.turkcell.planservice.client.CustomerClient;
import com.turkcell.planservice.dtos.productdtos.responses.ProductResponse;
import com.turkcell.planservice.dtos.usagedtos.requests.CreateUsageRequest;
import com.turkcell.planservice.dtos.usagedtos.requests.UpdateUsageRequest;
import com.turkcell.planservice.dtos.usagedtos.responses.UsageResponse;
import com.turkcell.planservice.entities.Product;
import com.turkcell.planservice.entities.Usage;
import com.turkcell.planservice.mappers.ProductMapper;
import com.turkcell.planservice.mappers.UsageMapper;
import com.turkcell.planservice.repositories.UsageRepository;
import com.turkcell.planservice.rules.UsageBusinessRules;
import com.turkcell.planservice.services.abstracts.ProductService;
import io.github.ertansidar.audit.AuditAwareImpl;
import io.github.ertansidar.exception.type.BusinessException;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsageServiceImplTest {

    @Mock
    private UsageRepository usageRepository;

    @Mock
    private UsageBusinessRules usageBusinessRules;

    @Mock
    private AuditAwareImpl auditAware;

    @Mock
    private CustomerClient customerClient;

    @Mock
    private ProductService productService;

    private UsageServiceImpl usageService;

    private UUID testUsageId;
    private UUID testCustomerId;
    private UUID testProductId;
    private Usage testUsage;
    private CreateUsageRequest testCreateUsageRequest;
    private UpdateUsageRequest testUpdateUsageRequest;
    private GetCustomerResponse testCustomerResponse;
    private ProductResponse testProductResponse;
    private Product testProduct;

    @BeforeEach
    void setUp() {
        // Initialize service with mocks
        usageService = new UsageServiceImpl(
                usageRepository,
                usageBusinessRules,
                auditAware,
                customerClient,
                productService
        );

        // Setup test data
        testUsageId = UUID.randomUUID();
        testCustomerId = UUID.randomUUID();
        testProductId = UUID.randomUUID();

        // Setup Usage entity
        testUsage = new Usage();
        testUsage.setId(testUsageId);

        // Setup CreateUsageRequest
        testCreateUsageRequest = new CreateUsageRequest();
        testCreateUsageRequest.setCustomerId(testCustomerId);
        testCreateUsageRequest.setProductId(testProductId);
        testCreateUsageRequest.setCallMinutesUsed(50);

        // Setup UpdateUsageRequest
        testUpdateUsageRequest = new UpdateUsageRequest();
        testUpdateUsageRequest.setCallMinutesUsed(100);

//        // Setup Customer Response
//        testCustomerResponse = new GetCustomerResponse();
//        testCustomerResponse.setId(testCustomerId);

        // Setup Product Response
        testProductResponse = new ProductResponse();
        testProductResponse.setId(testProductId);
        testProductResponse.setProductName("Test Product");

        // Setup Product
        testProduct = new Product();
        testProduct.setId(testProductId);
        testProduct.setProductName("Test Product");
    }

    @Test
    void getOneUsage_WhenUsageExists_ShouldReturnUsageResponse() {
        // Arrange
        when(usageRepository.findById(testUsageId)).thenReturn(Optional.of(testUsage));

        // Act
        UsageResponse result = usageService.getOneUsage(testUsageId);

        // Assert
        assertNotNull(result);
        verify(usageRepository).findById(testUsageId);
    }

    @Test
    void getOneUsage_WhenUsageNotExists_ShouldThrowBusinessException() {
        // Arrange
        when(usageRepository.findById(testUsageId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(BusinessException.class, () -> {
            usageService.getOneUsage(testUsageId);
        });

        verify(usageRepository).findById(testUsageId);
    }

    @Test
    void createUsage_WithIndividualCustomerAndValidProduct_ShouldCreateUsageSuccessfully() {
        // Arrange
        UUID testCustomerId = UUID.randomUUID();
        UUID testProductId = UUID.randomUUID();

        // Create request
        CreateUsageRequest testCreateUsageRequest = CreateUsageRequest.builder()
                .customerId(testCustomerId)
                .productId(testProductId)
                .internetUsed(100)
                .callMinutesUsed(50)
                .smsUsed(10)
                .tvHoursWatched(5)
                .build();

        // Individual Customer mock
        GetIndividualCustomerResponse individualCustomer = new GetIndividualCustomerResponse();
        individualCustomer.setId(testCustomerId);
        individualCustomer.setCustomerNumber("IND-1001");
        individualCustomer.setCustomerType("INDIVIDUAL");
        individualCustomer.setFirstName("Jane");
        individualCustomer.setLastName("Doe");

        // Product mock
        ProductResponse testProductResponse = new ProductResponse();
        testProductResponse.setId(testProductId);
        testProductResponse.setProductName("Test Product");

        when(customerClient.findById(testCustomerId)).thenReturn(individualCustomer);
        when(productService.getOneProduct(testProductId)).thenReturn(testProductResponse);

        // Act
        usageService.createUsage(testCreateUsageRequest);

        // Assert
        verify(customerClient).findById(testCustomerId);
        verify(productService).getOneProduct(testProductId);
        verify(usageRepository).save(any(Usage.class));
    }

    @Test
    void createUsage_WithCorporateCustomerAndValidProduct_ShouldCreateUsageSuccessfully() {
        // Arrange
        UUID testCustomerId = UUID.randomUUID();
        UUID testProductId = UUID.randomUUID();

        CreateUsageRequest testCreateUsageRequest = CreateUsageRequest.builder()
                .customerId(testCustomerId)
                .productId(testProductId)
                .internetUsed(300)
                .callMinutesUsed(150)
                .smsUsed(25)
                .tvHoursWatched(20)
                .build();

        // Corporate Customer mock
        GetCorporateCustomerResponse corporateCustomer = new GetCorporateCustomerResponse();
        corporateCustomer.setId(testCustomerId);
        corporateCustomer.setCustomerNumber("CORP-2001");
        corporateCustomer.setCustomerType("CORPORATE");
        corporateCustomer.setCompanyName("GlobalTech");

        // Product mock
        ProductResponse testProductResponse = new ProductResponse();
        testProductResponse.setId(testProductId);
        testProductResponse.setProductName("Corporate Plan");

        when(customerClient.findById(testCustomerId)).thenReturn(corporateCustomer);
        when(productService.getOneProduct(testProductId)).thenReturn(testProductResponse);

        // Act
        usageService.createUsage(testCreateUsageRequest);

        // Assert
        verify(customerClient).findById(testCustomerId);
        verify(productService).getOneProduct(testProductId);
        verify(usageRepository).save(any(Usage.class));
    }




    @Test
    void createUsage_WhenCustomerNotExists_ShouldThrowBusinessException() {
        // Arrange
        when(customerClient.findById(testCustomerId)).thenReturn(null);

        // Act & Assert
        assertThrows(BusinessException.class, () -> {
            usageService.createUsage(testCreateUsageRequest);
        });

        verify(customerClient).findById(testCustomerId);
        verify(usageRepository, never()).save(any());
    }

    @Test
    void createUsage_WhenProductDoesNotExist_ShouldThrowBusinessException() {
        // Arrange
        UUID testCustomerId = UUID.randomUUID();
        UUID testProductId = UUID.randomUUID();

        CreateUsageRequest testCreateUsageRequest = CreateUsageRequest.builder()
                .customerId(testCustomerId)
                .productId(testProductId)
                .internetUsed(100)
                .callMinutesUsed(50)
                .smsUsed(10)
                .tvHoursWatched(5)
                .build();

        // Mock customer (valid)
        GetIndividualCustomerResponse individualCustomer = new GetIndividualCustomerResponse();
        individualCustomer.setId(testCustomerId);
        individualCustomer.setCustomerNumber("ind123");
        individualCustomer.setCustomerType("indiviual");
        individualCustomer.setFirstName("Cebrail");
        individualCustomer.setLastName("Kaya");


        when(customerClient.findById(testCustomerId)).thenReturn(individualCustomer);
        when(productService.getOneProduct(testProductId)).thenReturn(null);

        // Act & Assert
        BusinessException exception = assertThrows(
                BusinessException.class,
                () -> usageService.createUsage(testCreateUsageRequest)
        );

        assertEquals("Product not found with ID: " + testProductId, exception.getMessage());
        verify(customerClient).findById(testCustomerId);
        verify(productService).getOneProduct(testProductId);
        verify(usageRepository, never()).save(any()); // hiç kayıt yapılmamalı
    }



    @Test
    void updateUsage_WhenUsageExists_ShouldUpdateUsage() {
        // Arrange
        when(usageRepository.findById(testUsageId)).thenReturn(Optional.of(testUsage));

        // Act
        usageService.updateUsage(testUsageId, testUpdateUsageRequest);

        // Assert
        verify(usageRepository).findById(testUsageId);
        verify(usageRepository).save(any(Usage.class));
    }

    @Test
    void updateUsage_WhenUsageNotExists_ShouldThrowBusinessException() {
        // Arrange
        when(usageRepository.findById(testUsageId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(BusinessException.class, () -> {
            usageService.updateUsage(testUsageId, testUpdateUsageRequest);
        });

        verify(usageRepository).findById(testUsageId);
        verify(usageRepository, never()).save(any());
    }

    @Test
    void getAllUsages_shouldReturnListOfUsages() {
        // Arrange
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPage(0);
        pageInfo.setSize(10);

        List<Usage> usages = Arrays.asList(testUsage);
        Page<Usage> usagePage = new PageImpl<>(
                usages,
                PageRequest.of(pageInfo.getPage(), pageInfo.getSize()),
                usages.size()
        );

        when(usageRepository.findAll(any(PageRequest.class))).thenReturn(usagePage);

        // Act
        GetListResponse<UsageResponse> response = usageService.getAllUsages(pageInfo);

        // Assert
        assertNotNull(response);
        verify(usageRepository).findAll(any(PageRequest.class));
    }

    @Test
    void delete_WhenUsageExists_ShouldSoftDeleteUsage() {
        // Arrange
        doNothing().when(usageBusinessRules).checkIfUsageExists(testUsageId);
        when(auditAware.getCurrentAuditor()).thenReturn(Optional.of("test-user"));

        // Act
        usageService.delete(testUsageId);

        // Assert
        verify(usageBusinessRules).checkIfUsageExists(testUsageId);
        verify(auditAware).getCurrentAuditor();
        verify(usageRepository).softDelete(
                eq(testUsageId),
                any(LocalDateTime.class),
                anyString()
        );
    }

    @Test
    void delete_WhenAuditAwareReturnsEmptyOptional_ShouldUseEmptyString() {
        // Arrange
        doNothing().when(usageBusinessRules).checkIfUsageExists(testUsageId);
        when(auditAware.getCurrentAuditor()).thenReturn(Optional.empty());

        // Act
        usageService.delete(testUsageId);

        // Assert
        verify(usageBusinessRules).checkIfUsageExists(testUsageId);
        verify(auditAware).getCurrentAuditor();
        verify(usageRepository).softDelete(
                eq(testUsageId),
                any(LocalDateTime.class),
                eq("Optional.empty")
        );
    }
}