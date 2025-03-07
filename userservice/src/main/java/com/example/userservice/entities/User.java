package com.example.userservice.entities;

import io.github.ertansidar.entities.BaseEntity;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User extends BaseEntity<UUID> {

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany()
    @JoinTable(
            name="user_operation_claims",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="operation_claim_id")
    )
    private List<OperationClaim> operationClaims;
}
