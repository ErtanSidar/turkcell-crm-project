package com.turkcell.planservice.services.concretes;

import com.turkcell.planservice.dtos.plandtos.requests.CreatePlanRequest;
import com.turkcell.planservice.dtos.plandtos.requests.UpdatePlanRequest;
import com.turkcell.planservice.dtos.plandtos.responses.PlanResponse;
import com.turkcell.planservice.entities.Plan;
import com.turkcell.planservice.repositories.PlanRepository;
import com.turkcell.planservice.rules.PlanBusinessRules;
import com.turkcell.planservice.rules.SubscriptionBusinessRules;
import io.github.ertansidar.audit.AuditAwareImpl;
import io.github.ertansidar.exception.type.BusinessException;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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

class PlanServiceImplTest {

    @Mock
    private PlanRepository planRepository;

    @Mock
    private PlanBusinessRules planBusinessRules;

    @Mock
    private SubscriptionBusinessRules subscriptionBusinessRules;

    private AuditAwareImpl auditAware;

    private PlanServiceImpl planService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        planService = new PlanServiceImpl(
                planRepository,
                planBusinessRules,
                subscriptionBusinessRules,
                auditAware
        );
    }

    @Test
    void getOnePlan_WhenPlanExists_ReturnPlanResponse() {

        UUID planId = UUID.randomUUID();
        Plan plan = new Plan();
        plan.setId(planId);
        plan.setPlanName("Test Plan");

        when(planRepository.findById(planId))
                .thenReturn(Optional.of(plan));

        // Act
        PlanResponse response = planService.getOnePlan(planId);

        // Assert
        assertNotNull(response);
        verify(planRepository).findById(planId);
    }

    @Test
    void getOnePlan_WhenPlanNotFound_ShouldThrowBusinessException() {
        // Arrange
        UUID planId = UUID.randomUUID();

        when(planRepository.findById(planId))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(BusinessException.class,
                () -> planService.getOnePlan(planId),
                "Plan with id: " + planId + " not found"
        );
    }

    @Test
    void createPlan_WithValidRequest_ShouldSavePlan() {
        // Arrange
        CreatePlanRequest request = new CreatePlanRequest();
        request.setPlanName("New Plan");

        doNothing().when(planBusinessRules)
                .checkIfPlanNameExists(request.getPlanName());

        // Act
        planService.createPlan(request);

        // Assert
        verify(planBusinessRules)
                .checkIfPlanNameExists(request.getPlanName());
        verify(planRepository).save(any(Plan.class));
    }

    @Test
    void createPlan_WithExistingPlanName_ShouldThrowException() {
        // Arrange
        CreatePlanRequest request = new CreatePlanRequest();
        request.setPlanName("Existing Plan");

        doThrow(new RuntimeException("A plan with this name already exists"))
                .when(planBusinessRules)
                .checkIfPlanNameExists(request.getPlanName());

        // Act & Assert
        assertThrows(RuntimeException.class,
                () -> planService.createPlan(request),
                "A plan with this name already exists"
        );
    }

    @Test
    void updatePlan_WithValidRequest_ShouldUpdatePlan() {
        // Arrange
        UUID planId = UUID.randomUUID();
        UpdatePlanRequest updateRequest = new UpdatePlanRequest();
        updateRequest.setPlanName("Updated Plan");

        Plan existingPlan = new Plan();
        existingPlan.setId(planId);

        doNothing().when(planBusinessRules)
                .checkIfPlanExists(planId);

        when(planRepository.findById(planId))
                .thenReturn(Optional.of(existingPlan));

        // Act
        planService.updatePlan(planId, updateRequest);

        // Assert
        verify(planBusinessRules).checkIfPlanExists(planId);
        verify(planRepository).save(existingPlan);
        assertEquals("Updated Plan", existingPlan.getPlanName());
    }

    @Test
    void updatePlan_WhenPlanNotFound_ShouldThrowBusinessException() {
        // Arrange
        UUID planId = UUID.randomUUID();
        UpdatePlanRequest updateRequest = new UpdatePlanRequest();

        doNothing().when(planBusinessRules)
                .checkIfPlanExists(planId);

        when(planRepository.findById(planId))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(BusinessException.class,
                () -> planService.updatePlan(planId, updateRequest),
                "Package with id: " + planId + " not found"
        );
    }

    @Test
    void getAllPlans_ShouldReturnPlanList() {
        // Arrange
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPage(0);
        pageInfo.setSize(10);

        // Create mock plans
        Plan plan1 = new Plan();
        plan1.setId(UUID.randomUUID());
        plan1.setPlanName("Plan 1");
        Plan plan2 = new Plan();
        plan2.setId(UUID.randomUUID());
        plan2.setPlanName("Plan 2");
        List<Plan> plans = Arrays.asList(plan1, plan2);

        // Create a mock Page
        Page<Plan> planPage = new PageImpl<>(plans,
                PageRequest.of(pageInfo.getPage(), pageInfo.getSize()),
                plans.size()
        );

        // Mock the repository findAll method
        when(planRepository.findAll(any(PageRequest.class)))
                .thenReturn(planPage);

        // Act
        GetListResponse<PlanResponse> response =
                planService.getAllPlans(pageInfo);

        // Assert
        assertNotNull(response);
        verify(planRepository).findAll(any(PageRequest.class));
    }

    @Test
    void deleteById_ShouldSoftDeletePlan() {
        // Arrange
        UUID planId = UUID.randomUUID();

        doNothing().when(planBusinessRules)
                .checkIfPlanExists(planId);

        doNothing().when(subscriptionBusinessRules)
                .checkIfPlanCanBeDeleted(planId);

        // Act
        planService.delete(planId);

        // Assert
        verify(planBusinessRules).checkIfPlanExists(planId);
        verify(subscriptionBusinessRules).checkIfPlanCanBeDeleted(planId);
        verify(planRepository).softDelete(
                eq(planId),
                any(LocalDateTime.class),
                eq(auditAware.getCurrentAuditor().toString())
        );
    }

    @Test
    void deleteById_WhenSubscriptionsExist_ShouldThrowException() {
        // Arrange
        UUID planId = UUID.randomUUID();

        doNothing().when(planBusinessRules)
                .checkIfPlanExists(planId);

        doThrow(new RuntimeException("Cannot delete plan because there are active subscriptions"))
                .when(subscriptionBusinessRules)
                .checkIfPlanCanBeDeleted(planId);

        // Act & Assert
        assertThrows(RuntimeException.class,
                () -> planService.delete(planId),
                "Cannot delete plan because there are active subscriptions"
        );
    }
}