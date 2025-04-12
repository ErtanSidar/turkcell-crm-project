package com.essoft.event.subscription;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionCreatedEvent {

    private UUID customerId;

    private String startDate;

    private String status;

    private UUID planId;
}
