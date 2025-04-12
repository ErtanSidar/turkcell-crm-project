package com.essoft.event.usage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsageCreatedEvent {


    private UUID customerId;

    private int internetUsed;

    private int callMinutesUsed;

    private int smsUsed;

    private int tvHoursWatched;

    private UUID productId;

}
