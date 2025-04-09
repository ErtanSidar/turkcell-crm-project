package com.turkcell.planservice.services.concretes;


import com.essoft.dto.customer.GetCorporateCustomerResponse;
import com.essoft.dto.customer.GetCustomerResponse;
import com.essoft.dto.customer.GetIndividualCustomerResponse;
import com.turkcell.planservice.client.CustomerClient;
import com.turkcell.planservice.dtos.plandtos.responses.PlanResponse;
import com.turkcell.planservice.dtos.subscriptiondtos.requests.CreateSubscriptionRequest;
import com.turkcell.planservice.dtos.subscriptiondtos.requests.UpdateSubscriptionRequest;
import com.turkcell.planservice.dtos.subscriptiondtos.responses.SubscriptionResponse;
import com.turkcell.planservice.entities.Plan;
import com.turkcell.planservice.entities.Subscription;
import com.turkcell.planservice.repositories.SubscriptionRepository;
import com.turkcell.planservice.rules.PlanBusinessRules;
import com.turkcell.planservice.rules.SubscriptionBusinessRules;
import com.turkcell.planservice.services.abstracts.PlanService;
import io.github.ertansidar.audit.AuditAwareImpl;
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
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class SubscriptionServiceImplTest {

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @Mock
    private SubscriptionBusinessRules subscriptionBusinessRules;

    @Mock
    private PlanBusinessRules planBusinessRules;

    @Mock
    private AuditAwareImpl auditAware;

    @Mock
    private CustomerClient customerClient;

    @Mock
    private PlanService planService;

    private SubscriptionServiceImpl subscriptionService;

    private UUID customerId;
    private UUID planId;
    private UUID subscriptionId;
    private CreateSubscriptionRequest createSubscriptionRequest;
    private UpdateSubscriptionRequest updateSubscriptionRequest;
    private GetCustomerResponse customerResponse;
    private PlanResponse planResponse;
    private Subscription subscription;
    private Plan plan;

    @BeforeEach
    void setUp() {
        // Initialize service with mocks
        subscriptionService = new SubscriptionServiceImpl(
                subscriptionRepository,
                subscriptionBusinessRules,
                planBusinessRules,
                auditAware,
                customerClient,
                planService
        );

        // Initialize test data
        customerId = UUID.randomUUID();
        planId = UUID.randomUUID();
        subscriptionId = UUID.randomUUID();

        // Setup CreateSubscriptionRequest
        createSubscriptionRequest = new CreateSubscriptionRequest();
        createSubscriptionRequest.setCustomerId(customerId);
        createSubscriptionRequest.setPlanId(planId);
        createSubscriptionRequest.setStatus("ACTIVE");

        // Setup UpdateSubscriptionRequest
        updateSubscriptionRequest = new UpdateSubscriptionRequest();
        updateSubscriptionRequest.setPlanId(planId);
        updateSubscriptionRequest.setStatus("UPDATED");


        // Setup PlanResponse
        planResponse = new PlanResponse();
        planResponse.setId(planId);
        planResponse.setPlanName("Test Plan");

        // Setup Plan
        plan = new Plan();
        plan.setId(planId);
        plan.setPlanName("Test Plan");

        // Setup Subscription
        subscription = new Subscription();
        subscription.setId(subscriptionId);
        subscription.setCustomerId(customerId);
        subscription.setPlan(plan);
        subscription.setStatus("ACTIVE");
    }
    @Test
    void createSubscription_WithValidIndividualCustomer_ShouldCreateSuccessfully() {
        // Arrange
        UUID customerId = UUID.randomUUID();
        UUID planId = UUID.randomUUID();

        CreateSubscriptionRequest createSubscriptionRequest = new CreateSubscriptionRequest();
        createSubscriptionRequest.setCustomerId(customerId);
        createSubscriptionRequest.setPlanId(planId);



        // Mock Individual Customer
        GetIndividualCustomerResponse individualCustomer = new GetIndividualCustomerResponse();
        individualCustomer.setId(customerId);
        individualCustomer.setCustomerNumber("ind123");
        individualCustomer.setCustomerType("individual");
        individualCustomer.setFirstName("Cebrail");
        individualCustomer.setLastName("Kaya");

        // Mock Plan
        PlanResponse planResponse = new PlanResponse();
        planResponse.setId(planId);
        planResponse.setPlanName("Test Plan");

        // Stubbing
        doNothing().when(subscriptionBusinessRules).checkIfCustomerAlreadySubscribedToPlan(customerId, planId);
        doNothing().when(subscriptionBusinessRules).checkIfPlanExistsForSubscription(planId.toString());
        when(customerClient.findById(customerId)).thenReturn(individualCustomer);
        when(planService.getOnePlan(planId)).thenReturn(planResponse);

        // Act
        subscriptionService.createSubscription(createSubscriptionRequest);

        // Assert
        verify(subscriptionBusinessRules).checkIfCustomerAlreadySubscribedToPlan(customerId, planId);
        verify(subscriptionBusinessRules).checkIfPlanExistsForSubscription(planId.toString());
        verify(customerClient).findById(customerId);
        verify(planService).getOnePlan(planId);
        verify(subscriptionRepository).save(any(Subscription.class));
    }

    @Test
    void createSubscription_WithValidCorporateCustomer_ShouldCreateSuccessfully() {
        // Arrange
        UUID customerId = UUID.randomUUID();
        UUID planId = UUID.randomUUID();

        CreateSubscriptionRequest createSubscriptionRequest = new CreateSubscriptionRequest();
        createSubscriptionRequest.setCustomerId(customerId);
        createSubscriptionRequest.setPlanId(planId);


        // Mock Corporate Customer
        GetCorporateCustomerResponse corporateCustomer = new GetCorporateCustomerResponse();
        corporateCustomer.setId(customerId);
        corporateCustomer.setCustomerNumber("corp123");
        corporateCustomer.setCustomerType("Corporate");
        corporateCustomer.setCompanyName("TURKCELL");

        // Mock Plan
        PlanResponse planResponse = new PlanResponse();
        planResponse.setId(planId);
        planResponse.setPlanName("Corporate Plan");

        // Stubbing
        doNothing().when(subscriptionBusinessRules).checkIfCustomerAlreadySubscribedToPlan(customerId, planId);
        doNothing().when(subscriptionBusinessRules).checkIfPlanExistsForSubscription(planId.toString());
        when(customerClient.findById(customerId)).thenReturn(corporateCustomer);
        when(planService.getOnePlan(planId)).thenReturn(planResponse);

        // Act
        subscriptionService.createSubscription(createSubscriptionRequest);

        // Assert
        verify(subscriptionBusinessRules).checkIfCustomerAlreadySubscribedToPlan(customerId, planId);
        verify(subscriptionBusinessRules).checkIfPlanExistsForSubscription(planId.toString());
        verify(customerClient).findById(customerId);
        verify(planService).getOnePlan(planId);
        verify(subscriptionRepository).save(any(Subscription.class));
    }

    @Test
    void createSubscription_WhenCustomerNotFound_ThrowsBusinessException() {
        // Arrange
        doNothing().when(subscriptionBusinessRules)
                .checkIfCustomerAlreadySubscribedToPlan(any(), any());

        doNothing().when(subscriptionBusinessRules)
                .checkIfPlanExistsForSubscription(any());

        when(customerClient.findById(customerId)).thenReturn(null);

        // Act & Assert
        assertThrows(BusinessException.class, () -> {
            subscriptionService.createSubscription(createSubscriptionRequest);
        });

        verify(subscriptionBusinessRules).checkIfCustomerAlreadySubscribedToPlan(customerId, planId);
        verify(subscriptionBusinessRules).checkIfPlanExistsForSubscription(planId.toString());
        verify(customerClient).findById(customerId);
        verify(subscriptionRepository, never()).save(any());
    }

    @Test
    void getOneSubs_WhenSubscriptionExists_ReturnSubscriptionResponse() {
        // Arrange
        when(subscriptionRepository.findById(subscriptionId))
                .thenReturn(Optional.of(subscription));

        // Act
        SubscriptionResponse result = subscriptionService.getOneSubs(subscriptionId);

        // Assert
        assertNotNull(result);
        assertEquals(subscriptionId, result.getId());
        verify(subscriptionRepository).findById(subscriptionId);
    }

    @Test
    void getOneSubs_WhenSubscriptionDoesNotExist_ThrowsBusinessException() {
        // Arrange
        when(subscriptionRepository.findById(subscriptionId))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(BusinessException.class, () -> {
            subscriptionService.getOneSubs(subscriptionId);
        });

        verify(subscriptionRepository).findById(subscriptionId);
    }




    @Test
    void updateSubscription_WithValidRequest_ShouldUpdateSubscription() {
        // Arrange
        when(subscriptionRepository.findById(subscriptionId))
                .thenReturn(Optional.of(subscription));

        doNothing().when(subscriptionBusinessRules)
                .checkIfSubscriptionExists(subscriptionId);

        doNothing().when(planBusinessRules)
                .checkIfPlanExists(planId);

        when(planService.getOnePlan(planId)).thenReturn(planResponse);

        // Act
        subscriptionService.updateSubscription(subscriptionId, updateSubscriptionRequest);

        // Assert
        verify(subscriptionBusinessRules).checkIfSubscriptionExists(subscriptionId);
        verify(planBusinessRules).checkIfPlanExists(planId);
        verify(planService).getOnePlan(planId);
        verify(subscriptionRepository).save(subscription);

        assertEquals("UPDATED", subscription.getStatus());
    }

    @Test
    void updateSubscription_WhenSubscriptionNotExists_ThrowsBusinessException() {
        // Arrange
        doNothing().when(subscriptionBusinessRules)
                .checkIfSubscriptionExists(subscriptionId);

        when(subscriptionRepository.findById(subscriptionId))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(BusinessException.class, () -> {
            subscriptionService.updateSubscription(subscriptionId, updateSubscriptionRequest);
        });

        verify(subscriptionBusinessRules).checkIfSubscriptionExists(subscriptionId);
        verify(subscriptionRepository).findById(subscriptionId);
        verify(subscriptionRepository, never()).save(any());
    }

    @Test
    void getAllSubscriptions_shouldReturnListOfSubscriptions() {
        // Arrange
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPage(0);
        pageInfo.setSize(10);

        List<Subscription> subscriptions = Arrays.asList(subscription);
        Page<Subscription> subscriptionPage = new PageImpl<>(
                subscriptions,
                PageRequest.of(pageInfo.getPage(), pageInfo.getSize()),
                subscriptions.size()
        );

        when(subscriptionRepository.findAll(any(PageRequest.class)))
                .thenReturn(subscriptionPage);

        // Act
        GetListResponse<SubscriptionResponse> response = subscriptionService.getAllSubscriptions(pageInfo);

        // Assert
        assertNotNull(response);
        verify(subscriptionRepository).findAll(any(PageRequest.class));
    }

    @Test
    void delete_WhenSubscriptionExists_ShouldSoftDeleteSubscription() {
        // Arrange
        doNothing().when(subscriptionBusinessRules)
                .checkIfSubscriptionExists(subscriptionId);

        when(auditAware.getCurrentAuditor())
                .thenReturn(Optional.of("test-user"));

        // Act
        subscriptionService.delete(subscriptionId);

        // Assert
        verify(subscriptionBusinessRules).checkIfSubscriptionExists(subscriptionId);
        verify(auditAware).getCurrentAuditor();
        verify(subscriptionRepository).softDelete(
                eq(subscriptionId),
                any(LocalDateTime.class),
                anyString()
        );
    }

    @Test
    void delete_WhenAuditAwareReturnsEmptyOptional_ShouldUseEmptyString() {
        // Arrange
        doNothing().when(subscriptionBusinessRules)
                .checkIfSubscriptionExists(subscriptionId);

        when(auditAware.getCurrentAuditor())
                .thenReturn(Optional.empty());

        // Act
        subscriptionService.delete(subscriptionId);

        // Assert
        verify(subscriptionBusinessRules).checkIfSubscriptionExists(subscriptionId);
        verify(auditAware).getCurrentAuditor();
        verify(subscriptionRepository).softDelete(
                eq(subscriptionId),
                any(LocalDateTime.class),
                eq("Optional.empty")
        );
    }
}