package com.turkcell.analyticservice.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collation = "packages")
public class Package {

    @Id
    @Field(name = "id")
    private UUID id;

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
