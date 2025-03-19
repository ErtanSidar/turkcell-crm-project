package com.turkcell.analyticservice.controllers;

import an.awesome.pipelinr.Pipeline;
import com.turkcell.analyticservice.application.usage.command.create.CreateUsageCommand;
import com.turkcell.analyticservice.application.usage.command.create.CreatedUsageResponse;
import com.turkcell.analyticservice.application.usage.command.delete.DeleteUsageCommand;
import com.turkcell.analyticservice.application.usage.command.delete.DeletedUsageResponse;
import com.turkcell.analyticservice.application.usage.command.update.UpdateUsageCommand;
import com.turkcell.analyticservice.application.usage.command.update.UpdatedUsageResponse;
import com.turkcell.analyticservice.application.usage.query.getbyid.GetUsageByIdItemDto;
import com.turkcell.analyticservice.application.usage.query.getbyid.GetUsageByIdQuery;
import com.turkcell.analyticservice.application.usage.query.list.GetListUsageItemDto;
import com.turkcell.analyticservice.application.usage.query.list.GetListUsageQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping
    public ResponseEntity<List<GetListUsageItemDto>> getListUsages() {
        GetListUsageQuery query = new GetListUsageQuery();
        List<GetListUsageItemDto> usages = pipeline.send(query);
        return ResponseEntity.ok(usages);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GetUsageByIdItemDto> getUsageById(@PathVariable UUID id) {
        GetUsageByIdQuery query = new GetUsageByIdQuery(id);
        GetUsageByIdItemDto usage = pipeline.send(query);
        return ResponseEntity.ok(usage);
    }
}
