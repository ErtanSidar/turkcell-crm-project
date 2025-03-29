package com.turkcell.planservice.services.concretes;


import com.essoft.dto.customer.GetCustomerResponse;
import com.turkcell.planservice.client.CustomerClient;
import com.turkcell.planservice.dtos.plandtos.responses.PlanResponse;
import com.turkcell.planservice.dtos.subscriptiondtos.requests.CreateSubscriptionRequest;
import com.turkcell.planservice.dtos.subscriptiondtos.responses.SubscriptionResponse;
import com.turkcell.planservice.entities.Subscription;
import com.turkcell.planservice.repositories.SubscriptionRepository;
import com.turkcell.planservice.rules.SubscriptionBusinessRules;
import com.turkcell.planservice.services.abstracts.PlanService;
import io.github.ertansidar.audit.AuditAwareImpl;
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
    private PlanService planService;
    private AuditAwareImpl auditAware;
    @Mock
    private CustomerClient customerClient;
    @InjectMocks
    private SubscriptionServiceImpl subscriptionService;
    private CreateSubscriptionRequest createSubscriptionRequest;
    private GetCustomerResponse customerResponse;
    private PlanResponse planResponse;

    @BeforeEach
    void setUp() {

        UUID customerId = UUID.randomUUID();
        UUID planId = UUID.randomUUID();

        createSubscriptionRequest = new CreateSubscriptionRequest();
        createSubscriptionRequest.setCustomerId(customerId);
        createSubscriptionRequest.setPlanId(planId);
        // customerResponse = new GetCustomerResponse();
        customerResponse.setId(customerId);

        planResponse = new PlanResponse();
        planResponse.setId(planId);
    }

    @Test
    void getOneSubs_WhenSubscriptionExist_ReturnSubscriptionResponse() {
        UUID subscriptionId = UUID.randomUUID();
        Subscription subscription = new Subscription();
        subscription.setId(subscriptionId);

        when(subscriptionRepository.findById(subscriptionId))
                .thenReturn(Optional.of(subscription));

        SubscriptionResponse subscriptionResponse = subscriptionService.getOneSubs(subscriptionId);

        assertNotNull(subscriptionResponse);

        assertEquals(subscriptionId, subscriptionResponse.getId());

    }

    @Test
    void createSubscription_WithValidRequest_ShouldCreateSuccessfully() {

        when(customerClient.findById(createSubscriptionRequest.getCustomerId()))
                .thenReturn(customerResponse);

        subscriptionService.createSubscription(createSubscriptionRequest);

        verify(customerClient).findById(createSubscriptionRequest.getCustomerId());
        verify(subscriptionRepository).save(any(Subscription.class));
    }

    @Test
    void createSubscription_WhenCustomerAlreadySubscribed_ShouldThrowException() {
        // Arrange
        UUID customerId = UUID.randomUUID();
        UUID planId = UUID.randomUUID();

        CreateSubscriptionRequest createRequest = new CreateSubscriptionRequest();
        createRequest.setCustomerId(customerId);
        createRequest.setPlanId(planId);

        // Simulate exception when customer is already subscribed
        doThrow(new RuntimeException("Customer already subscribed to this plan"))
                .when(subscriptionBusinessRules)
                .checkIfCustomerAlreadySubscribedToPlan(customerId, planId);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            subscriptionService.createSubscription(createRequest);
        });
    }

    @Test
    void createSubscription_WhenPlanNotExists_ShouldThrowException() {
        // Arrange
        UUID customerId = UUID.randomUUID();
        UUID planId = UUID.randomUUID();

        CreateSubscriptionRequest createRequest = new CreateSubscriptionRequest();
        createRequest.setCustomerId(customerId);
        createRequest.setPlanId(planId);

        // Simulate exception when plan does not exist
        doThrow(new RuntimeException("Plan not found"))
                .when(subscriptionBusinessRules)
                .checkIfPlanExistsForSubscription(planId.toString());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            subscriptionService.createSubscription(createRequest);
        });
    }

    @Test
    void createSubscription_WhenCustomerServiceUnavailable_ShouldThrowRuntimeException() {
        // Arrange
        UUID customerId = UUID.randomUUID();
        UUID planId = UUID.randomUUID();

        CreateSubscriptionRequest createRequest = new CreateSubscriptionRequest();
        createRequest.setCustomerId(customerId);
        createRequest.setPlanId(planId);

        // Mock business rules
        doNothing().when(subscriptionBusinessRules)
                .checkIfCustomerAlreadySubscribedToPlan(customerId, planId);

        doNothing().when(subscriptionBusinessRules)
                .checkIfPlanExistsForSubscription(planId.toString());

        // Simulate customer service unavailability
        when(customerClient.findById(customerId))
                .thenThrow(new RuntimeException("Customer service error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            subscriptionService.createSubscription(createRequest);
        }, "Customer Service is not available or customer not found");
    }

    @Test
    void createSubscription_WhenCustomerAlreadySubscribedToPlan_ShouldThrowBusinessException() {
        // Arrange
        UUID customerId = UUID.randomUUID();
        UUID planId = UUID.randomUUID();

        CreateSubscriptionRequest createRequest = new CreateSubscriptionRequest();
        createRequest.setCustomerId(customerId);
        createRequest.setPlanId(planId);

        // Mock business rules to throw exception
        doThrow(new RuntimeException("Customer already subscribed to this plan"))
                .when(subscriptionBusinessRules)
                .checkIfCustomerAlreadySubscribedToPlan(customerId, planId);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            subscriptionService.createSubscription(createRequest);
        }, "Customer already subscribed to this plan");
    }

    @Test
    void updateSubscription_WithValidRequest_ShouldUpdateSubscription() {
//        UUID subscriptionId = UUID.randomUUID();
//        UpdateSubscriptionRequest updateRequest = new UpdateSubscriptionRequest();
//        updateRequest.setStatus("updated-status");
//
//        Subscription existingSub = new Subscription();
//        existingSub.setId(subscriptionId);
//
//        doNothing().when(subscriptionBusinessRules)
//                .checkIfSubscriptionExists(subscriptionId);
//
//        when(subscriptionRepository.findById(subscriptionId))
//                .thenReturn(Optional.of(existingSub));
//
//        // Act
//        subscriptionService.updateSubscription(subscriptionId, updateRequest);
//
//        // Assert
//        verify(subscriptionBusinessRules).checkIfSubscriptionExists(subscriptionId);
//        verify(subscriptionRepository).save(existingSub);
//        assertEquals("updated-status", existingSub.getStatus());

    }

    @Test
    void getAllSubscriptions_shouldReturnSubscriptionList() {

        PageInfo pageInfo = new PageInfo();
        pageInfo.setPage(0);
        pageInfo.setSize(10);

        Subscription sub1 = new Subscription();
        sub1.setId(UUID.randomUUID());
        Subscription sub2 = new Subscription();
        sub2.setId(UUID.randomUUID());
        List<Subscription> subscriptions = Arrays.asList(sub1, sub2);

        Page<Subscription> subscriptionPage = new PageImpl<>(subscriptions,
                PageRequest.of(pageInfo.getPage(), pageInfo.getSize()),
                subscriptions.size()
        );

        when(subscriptionRepository.findAll(any(PageRequest.class)))
                .thenReturn(subscriptionPage);


        GetListResponse<SubscriptionResponse> response =
                subscriptionService.getAllSubscriptions(pageInfo);

        // Assert
        assertNotNull(response);
        // You might want to add more specific assertions based on your implementation
        verify(subscriptionRepository).findAll(any(PageRequest.class));
    }

    @Test
    void deleteById_ShouldSoftDeletePlan() {
        UUID subscriptionId = UUID.randomUUID();

        doNothing().when(subscriptionBusinessRules)
                .checkIfSubscriptionExists(subscriptionId);

        subscriptionService.delete(subscriptionId);

        verify(subscriptionBusinessRules).checkIfSubscriptionExists(subscriptionId);
        verify(subscriptionRepository)
                .softDelete(
                        eq(subscriptionId),
                        any(LocalDateTime.class),
                        eq(auditAware.getCurrentAuditor().toString())

                );
    }
}