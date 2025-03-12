package com.turkcell.analyticservice.application.usage.command.delete;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeletedUsageResponse {
    private UUID id;
    private String message;
}
