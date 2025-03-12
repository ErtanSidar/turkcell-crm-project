package com.turkcell.analyticservice.application.packages.command.create;

import com.turkcell.analyticservice.domain.entity.Product;
import lombok.*;


import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatedPackageResponse
{
    private UUID id;
    private Product product;
    private String packageName;
    private String packageType;
    private int quota;
    private double price;
    private int validityPeriod;
}