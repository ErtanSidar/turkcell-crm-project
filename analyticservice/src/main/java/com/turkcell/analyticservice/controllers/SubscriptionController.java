package com.turkcell.analyticservice.controllers;



import an.awesome.pipelinr.Pipeline;
import com.turkcell.analyticservice.application.subscription.command.create.CreateSubscriptionCommand;
import com.turkcell.analyticservice.application.subscription.command.create.CreatedSubscriptionResponse;
import com.turkcell.analyticservice.application.subscription.command.delete.DeleteSubscriptionCommand;
import com.turkcell.analyticservice.application.subscription.command.delete.DeletedSubscriptionResponse;
import com.turkcell.analyticservice.application.subscription.command.update.UpdateSubscriptionCommand;
import com.turkcell.analyticservice.application.subscription.command.update.UpdatedSubscriptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final Pipeline pipeline;

    @PostMapping
    public ResponseEntity<CreatedSubscriptionResponse> createSubscription(@RequestBody CreateSubscriptionCommand command) {
        return ResponseEntity.ok(pipeline.send(command));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdatedSubscriptionResponse> updateSubscription(
            @PathVariable UUID id, @RequestBody UpdateSubscriptionCommand command) {
        command.setId(id);
        return ResponseEntity.ok(pipeline.send(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeletedSubscriptionResponse> deleteSubscription(@PathVariable UUID id) {
        return ResponseEntity.ok(pipeline.send(new DeleteSubscriptionCommand(id)));
    }
}
