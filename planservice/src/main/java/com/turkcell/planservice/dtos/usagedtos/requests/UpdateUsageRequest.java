package com.turkcell.planservice.dtos.usagedtos.requests;


import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUsageRequest {

    @PositiveOrZero(message = "Internet usage cannot be negative")
    private int internetUsed;

    @PositiveOrZero(message = "Call minutes used cannot be negative")
    private int callMinutesUsed;

    @PositiveOrZero(message = "SMS used cannot be negative")
    private int smsUsed;

    @PositiveOrZero(message = "TV hours watched cannot be negative")
    private int tvHoursWatched;
}
