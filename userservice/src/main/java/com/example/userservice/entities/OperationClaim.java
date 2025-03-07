package com.example.userservice.entities;

import io.github.ertansidar.entities.BaseEntity;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "operation_claims")
public class OperationClaim extends BaseEntity<UUID> {

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "operationClaims")
    private List<User> users;
}
