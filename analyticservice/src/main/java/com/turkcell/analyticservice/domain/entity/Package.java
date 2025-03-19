package com.turkcell.analyticservice.domain.entity;

import io.github.ertansidar.entities.BaseEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "packages")
public class Package extends BaseEntity<UUID> {

    @Override
    protected UUID generateId() {
        return UUID.randomUUID();
    }

    @Field(name = "product")
    private Product product;

    @Field(name = "package_name")
    private String packageName;

    @Field(name = "package_type")
    private String packageType;

    @Field(name = "quota")
    private int quota;

    @Field(name = "price")
    private double price;

    @Field(name = "validity_period")
    private int validityPeriod;


}
