package com.turkcell.analyticservice.application.packages.query.list;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
public class GetListPackageItemDto {
    private UUID id;
    private String packageName;
    private String packageType;
    private int quota;
    private double price;
    private int validityPeriod;
}