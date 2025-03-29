package com.turkcell.planservice.dtos.usagedtos.responses;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsageResponse {
    private UUID id;
    private UUID customerId;
    private int internetUsed;
    private int callMinutesUsed;
    private int smsUsed;
    private int tvHoursWatched;
    private UUID productId;
    private String created_by;
    private LocalDateTime created_at;
    private String updated_by;
    private LocalDateTime updated_at;
    private String deleted_by;
    private LocalDateTime deleted_at;

}
