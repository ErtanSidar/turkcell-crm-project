package com.turkcell.analyticservice.application.plan.command.delete;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeletedPlanResponse {
    private UUID id;
    private String message;
}