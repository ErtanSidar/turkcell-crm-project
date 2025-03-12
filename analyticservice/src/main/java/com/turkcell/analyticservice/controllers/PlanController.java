package com.turkcell.analyticservice.controllers;



import an.awesome.pipelinr.Pipeline;
import com.turkcell.analyticservice.application.plan.command.create.CreatePlanCommand;
import com.turkcell.analyticservice.application.plan.command.create.CreatedPlanResponse;
import com.turkcell.analyticservice.application.plan.command.delete.DeletePlanCommand;
import com.turkcell.analyticservice.application.plan.command.delete.DeletedPlanResponse;
import com.turkcell.analyticservice.application.plan.command.update.UpdatePlanCommand;
import com.turkcell.analyticservice.application.plan.command.update.UpdatedPlanResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
