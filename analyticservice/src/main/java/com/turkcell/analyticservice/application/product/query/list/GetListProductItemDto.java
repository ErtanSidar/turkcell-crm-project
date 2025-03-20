package com.turkcell.analyticservice.application.product.query.list;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class GetListProductItemDto {
    private UUID id;
    private String productName;
    private String description;
    private String productType;
}
