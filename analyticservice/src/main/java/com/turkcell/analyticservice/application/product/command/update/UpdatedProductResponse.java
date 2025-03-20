package com.turkcell.analyticservice.application.product.command.update;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatedProductResponse {
    private UUID id;
    private String productName;
    private String description;
    private String productType;
}