package com.turkcell.analyticservice.application.subscription.command.delete;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeletedSubscriptionResponse {
    private UUID id;
    private String message;
}