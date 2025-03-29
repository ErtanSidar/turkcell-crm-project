package com.turkcell.planservice.controllers;

import com.turkcell.planservice.dtos.plandtos.requests.CreatePlanRequest;
import com.turkcell.planservice.dtos.plandtos.requests.UpdatePlanRequest;
import com.turkcell.planservice.dtos.plandtos.responses.PlanResponse;
import com.turkcell.planservice.entities.Plan;
import com.turkcell.planservice.services.abstracts.PlanService;
import com.turkcell.planservice.util.GenericResponse;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/plans")
@Log4j2
public class PlanController {

    private final PlanService planService;


    public PlanController(PlanService planService) {
        this.planService = planService;

    }


    @GetMapping
    public GetListResponse<PlanResponse> getAllPlans(@RequestParam int page, @RequestParam int size) {

        log.info("Received request to listing all packages");
        return planService.getAllPlans(new PageInfo(page,size));
    }


    @GetMapping("/getOnePlan")
    public PlanResponse getOnePlan(@RequestParam UUID id) {
        return planService.getOnePlan(id);
    }

    @PostMapping
    public GenericResponse<String> createPlan(@RequestBody @Valid CreatePlanRequest createPlanRequest) {
        planService.createPlan(createPlanRequest);
        return GenericResponse.success("generic.plan.created");
    }

    @PutMapping
    public GenericResponse<String> updatePlan(@RequestParam UUID id,@RequestBody @Valid UpdatePlanRequest updatePlanRequest) {
        planService.updatePlan(id, updatePlanRequest);
        return GenericResponse.success("generic.plan.updated");
    }

    @DeleteMapping
    public GenericResponse<String> deletePlan(@RequestParam UUID id) {
        planService.delete(id);
        return GenericResponse.success("generic.plan.deleted");
    }
}
