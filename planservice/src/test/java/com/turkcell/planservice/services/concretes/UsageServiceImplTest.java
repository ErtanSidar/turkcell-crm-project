package com.turkcell.planservice.services.concretes;

import com.turkcell.planservice.dtos.usagedtos.requests.CreateUsageRequest;
import com.turkcell.planservice.dtos.usagedtos.responses.UsageResponse;
import com.turkcell.planservice.entities.Usage;
import com.turkcell.planservice.events.dtos.customerdtos.response.GetCustomerResponse;
import com.turkcell.planservice.feign.CustomerClient;
import com.turkcell.planservice.repositories.UsageRepository;
import com.turkcell.planservice.rules.UsageBusinessRules;
import io.github.ertansidar.exception.type.BusinessException;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsageServiceImplTest {


    @Mock
    private UsageRepository usageRepository;

    @Mock
    private UsageBusinessRules usageBusinessRules;

    @Mock
    private CustomerClient customerClient;

    @InjectMocks
    private UsageServiceImpl usageService;

    private UUID testUsageId;
    private Usage testUsage;
    private CreateUsageRequest testCreateUsageRequest;
    private GetCustomerResponse testCustomerResponse;

    @BeforeEach
    void setUp() {
        testUsageId = UUID.randomUUID();
        testUsage = new Usage();
        testUsage.setId(testUsageId);

        testCreateUsageRequest = new CreateUsageRequest();
        testCreateUsageRequest.setCustomerId(UUID.randomUUID());

        testCustomerResponse = new GetCustomerResponse();
        testCustomerResponse.setId(testCreateUsageRequest.getCustomerId());
    }

    @Test
    void getOneUsage_WhenUsageExists_ShouldReturnUsage() {
        // Arrange
        when(usageRepository.findById(testUsageId)).thenReturn(Optional.of(testUsage));

        // Act
        Usage result = usageService.getOneUsage(testUsageId);

        // Assert
        assertNotNull(result);
        assertEquals(testUsageId, result.getId());
        verify(usageRepository).findById(testUsageId);
    }

    @Test
    void getOneUsage_WhenUsageNotExists_ShouldThrowBusinessException() {
        // Arrange
        when(usageRepository.findById(testUsageId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(BusinessException.class, () -> usageService.getOneUsage(testUsageId));
    }

    @Test
    void createUsage_WhenCustomerExists_ShouldCreateUsage() {
        // Arrange
        when(customerClient.findById(testCreateUsageRequest.getCustomerId())).thenReturn(testCustomerResponse);

        // Act
        usageService.createUsage(testCreateUsageRequest);

        // Assert
        verify(customerClient).findById(testCreateUsageRequest.getCustomerId());
        verify(usageRepository).save(any(Usage.class));
    }

    @Test
    void createUsage_WhenCustomerServiceUnavailable_ShouldThrowRuntimeException() {
        // Arrange
        when(customerClient.findById(testCreateUsageRequest.getCustomerId())).thenThrow(new RuntimeException("Service unavailable"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> usageService.createUsage(testCreateUsageRequest));
    }

    @Test
    void getAllUsages_ShouldReturnPagedResponse() {
        // Arrange
        PageInfo pageInfo = new PageInfo(0, 10);
        Page<Usage> mockPage = new PageImpl<>(Collections.singletonList(testUsage));

        when(usageRepository.findAll(any(Pageable.class))).thenReturn(mockPage);

        // Act
        GetListResponse<UsageResponse> result = usageService.getAllUsages(pageInfo);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getItems().size());
        verify(usageRepository).findAll(any(Pageable.class));
    }

    @Test
    void deleteById_WhenUsageExists_ShouldSoftDelete() {
        // Arrange
        doNothing().when(usageBusinessRules).checkIfUsageExists(testUsageId);

        // Act
        usageService.deleteById(testUsageId);

        // Assert
        verify(usageBusinessRules).checkIfUsageExists(testUsageId);
        verify(usageRepository).softDelete(eq(testUsageId), any(LocalDateTime.class), eq(AuditServiceImpl.USER));
    }
}