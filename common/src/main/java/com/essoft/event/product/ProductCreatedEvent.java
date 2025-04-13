package com.essoft.event.product;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreatedEvent {


    private String productName;

    private String description;

    private String productType;

    private UUID planId;

    private UUID subscriptionId;
}
