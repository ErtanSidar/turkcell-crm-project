package com.turkcell.analyticservice.application.packages.query.getbyid;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class GetPackageByIdItemDto {

    private UUID id;
    private String packageName;
    private String packageType;
    private int quota;
    private double price;
    private int validityPeriod;
}