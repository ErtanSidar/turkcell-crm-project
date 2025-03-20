package com.turkcell.analyticservice.domain.entity;

import io.github.ertansidar.entities.BaseEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class Product extends BaseEntity<UUID> {

    @Override
    protected UUID generateId() {
        return UUID.randomUUID();
    }

    @Field(name = "product_name")
    private String productName;

    @Field(name = "description")
    private String description;

    @Field(name = "product_type")
    private String productType;

}
