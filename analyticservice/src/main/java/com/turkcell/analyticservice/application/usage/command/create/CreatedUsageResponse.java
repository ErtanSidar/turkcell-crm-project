package com.turkcell.analyticservice.application.usage.command.create;

import com.turkcell.analyticservice.domain.entity.Product;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatedUsageResponse {
    private UUID id;
    private UUID customerId;
    private Product product;
    private int internetUsed;
    private int callMinutesUsed;
    private int smsUsed;
    private int tvHoursWatched;
}
