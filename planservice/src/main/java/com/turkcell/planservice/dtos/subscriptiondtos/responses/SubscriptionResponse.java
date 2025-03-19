package com.turkcell.planservice.dtos.subscriptiondtos.responses;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionResponse {

    private UUID id;
    private UUID customerId;
    private String startDate;
    private String status;
    private UUID planId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private LocalDateTime deletedDate;
}
