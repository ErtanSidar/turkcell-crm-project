package com.turkcell.analyticservice.controllers;


import an.awesome.pipelinr.Pipeline;
import com.turkcell.analyticservice.application.subscription.command.create.CreateSubscriptionCommand;
import com.turkcell.analyticservice.application.subscription.command.create.CreatedSubscriptionResponse;
import com.turkcell.analyticservice.application.subscription.command.delete.DeleteSubscriptionCommand;
import com.turkcell.analyticservice.application.subscription.command.delete.DeletedSubscriptionResponse;
import com.turkcell.analyticservice.application.subscription.command.update.UpdateSubscriptionCommand;
import com.turkcell.analyticservice.application.subscription.command.update.UpdatedSubscriptionResponse;
import com.turkcell.analyticservice.application.subscription.query.getbyid.GetSubscriptionByIdItemDto;
import com.turkcell.analyticservice.application.subscription.query.getbyid.GetSubscriptionByIdQuery;
import com.turkcell.analyticservice.application.subscription.query.list.GetListSubscriptionItemDto;
import com.turkcell.analyticservice.application.subscription.query.list.GetListSubscriptionQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping
    public ResponseEntity<List<GetListSubscriptionItemDto>> getListSubscriptions() {
        GetListSubscriptionQuery query = new GetListSubscriptionQuery();
        List<GetListSubscriptionItemDto> subscriptions = pipeline.send(query);
        return ResponseEntity.ok(subscriptions);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GetSubscriptionByIdItemDto> getSubscriptionById(@PathVariable UUID id) {
        GetSubscriptionByIdQuery query = new GetSubscriptionByIdQuery(id);
        GetSubscriptionByIdItemDto subscription = pipeline.send(query);
        return ResponseEntity.ok(subscription);
    }
}
