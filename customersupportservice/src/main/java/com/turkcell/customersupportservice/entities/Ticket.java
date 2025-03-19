package com.turkcell.customersupportservice.entities;

import io.github.ertansidar.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;


@Document(collation = "tickets")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ticket extends BaseEntity<UUID> {

    @Field(name = "customer_id")
    private UUID customerId;

    @Field(name = "subject")
    private String subject;

    @Field(name = "description")
    private String description;

    @Field(name = "status")
    private String status;

    @Override
    protected UUID generateId(){
        return UUID.randomUUID();
    }


}
