package com.turkcell.analyticservice.controllers;

import an.awesome.pipelinr.Pipeline;
import com.turkcell.analyticservice.application.usage.command.create.CreateUsageCommand;
import com.turkcell.analyticservice.application.usage.command.create.CreatedUsageResponse;
import com.turkcell.analyticservice.application.usage.command.delete.DeleteUsageCommand;
import com.turkcell.analyticservice.application.usage.command.delete.DeletedUsageResponse;
import com.turkcell.analyticservice.application.usage.command.update.UpdateUsageCommand;
import com.turkcell.analyticservice.application.usage.command.update.UpdatedUsageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/usages")
@RequiredArgsConstructor
public class UsageController {
    private final Pipeline pipeline;

    @PostMapping
    public ResponseEntity<CreatedUsageResponse> createUsage(@RequestBody CreateUsageCommand command) {
        return ResponseEntity.ok(pipeline.send(command));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdatedUsageResponse> updateUsage(
            @PathVariable UUID id, @RequestBody UpdateUsageCommand command) {
        command.setId(id);
        return ResponseEntity.ok(pipeline.send(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeletedUsageResponse> deleteUsage(@PathVariable UUID id) {
        return ResponseEntity.ok(pipeline.send(new DeleteUsageCommand(id)));
    }
}
