package com.turkcell.analyticservice.controllers;


import an.awesome.pipelinr.Pipeline;
import com.turkcell.analyticservice.application.plan.command.create.CreatePlanCommand;
import com.turkcell.analyticservice.application.plan.command.create.CreatedPlanResponse;
import com.turkcell.analyticservice.application.plan.command.delete.DeletePlanCommand;
import com.turkcell.analyticservice.application.plan.command.delete.DeletedPlanResponse;
import com.turkcell.analyticservice.application.plan.command.update.UpdatePlanCommand;
import com.turkcell.analyticservice.application.plan.command.update.UpdatedPlanResponse;
import com.turkcell.analyticservice.application.plan.query.getbyid.GetPlanByIdItemDto;
import com.turkcell.analyticservice.application.plan.query.getbyid.GetPlanByIdQuery;
import com.turkcell.analyticservice.application.plan.query.list.GetListPlanItemDto;
import com.turkcell.analyticservice.application.plan.query.list.GetListPlanQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/plans")
@RequiredArgsConstructor
public class PlanController {
    private final Pipeline pipeline;

    @PostMapping
    public ResponseEntity<CreatedPlanResponse> createPlan(@RequestBody CreatePlanCommand command) {
        return ResponseEntity.ok(pipeline.send(command));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdatedPlanResponse> updatePlan(
            @PathVariable UUID id, @RequestBody UpdatePlanCommand command) {
        command.setId(id);
        return ResponseEntity.ok(pipeline.send(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeletedPlanResponse> deletePlan(@PathVariable UUID id) {
        return ResponseEntity.ok(pipeline.send(new DeletePlanCommand(id)));
    }

    @GetMapping
    public ResponseEntity<List<GetListPlanItemDto>> getListPlans() {
        GetListPlanQuery query = new GetListPlanQuery();
        List<GetListPlanItemDto> plans = pipeline.send(query);
        return ResponseEntity.ok(plans);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GetPlanByIdItemDto> getPlanById(@PathVariable UUID id) {
        GetPlanByIdQuery query = new GetPlanByIdQuery(id);
        GetPlanByIdItemDto plan = pipeline.send(query);
        return ResponseEntity.ok(plan);
    }
}
