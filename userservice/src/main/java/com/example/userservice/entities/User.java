package com.example.userservice.entities;

import io.github.ertansidar.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntity<UUID> {

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @ManyToMany()
    @JoinTable(
            name = "user_operation_claims",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "operation_claim_id")
    )
    private List<OperationClaim> operationClaims;

    @Override
    protected UUID generateId() {
        return UUID.randomUUID();
    }
}
