package com.turkcell.planservice.dtos.usagedtos.requests;


import lombok.*;

import java.util.UUID;
import jakarta.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUsageRequest {

    @NotNull(message = "Customer ID cannot be null")
    private UUID customerId;

    @PositiveOrZero(message = "Internet usage cannot be negative")
    private int internetUsed;

    @PositiveOrZero(message = "Call minutes used cannot be negative")
    private int callMinutesUsed;

    @PositiveOrZero(message = "SMS used cannot be negative")
    private int smsUsed;

    @PositiveOrZero(message = "TV hours watched cannot be negative")
    private int tvHoursWatched;

    @NotNull(message = "Product ID cannot be null")
    private UUID productId;
}
