package com.turkcell.analyticservice.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Document(collation = "products")
public class Product {

    @Id
    @Field(name = "id")
    private UUID id;

    @Field(name = "product_name")
    private String productName;

    @Field(name = "description")
    private String description;

    @Field(name = "product_type")
    private String productType;

}
