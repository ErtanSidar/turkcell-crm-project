package com.turkcell.analyticservice.application.product.query.getbyid;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class GetProductByIdItemDto {

    private UUID id;
    private String productName;
    private String description;
    private String productType;
}
