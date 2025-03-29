package com.turkcell.planservice.dtos.productdtos.responses;

import com.turkcell.planservice.entities.ProductType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {

    private UUID id;
    private String productName;
    private String description;
    private String productType;
    private UUID planId;
    private UUID subscriptionId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private LocalDateTime deletedDate;

}
