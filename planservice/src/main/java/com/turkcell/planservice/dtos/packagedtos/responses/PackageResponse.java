package com.turkcell.planservice.dtos.packagedtos.responses;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PackageResponse {

    private UUID id;
    private String packageName;
    private String packageType;
    private int quota;
    private double price;
    private int validityPeriod;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private LocalDateTime deletedDate;

}
