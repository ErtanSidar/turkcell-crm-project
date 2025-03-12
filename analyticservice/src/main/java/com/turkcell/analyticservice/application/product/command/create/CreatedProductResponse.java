package com.turkcell.analyticservice.application.product.command.create;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatedProductResponse {
    private UUID id;
    private String productName;
    private String description;
    private String productType;
}