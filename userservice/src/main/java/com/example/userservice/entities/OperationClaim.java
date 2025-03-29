package com.example.userservice.entities;

import io.github.ertansidar.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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
@Table(name = "operation_claims")
public class OperationClaim extends BaseEntity<UUID> {

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "operationClaims")
    private List<User> users;

    @Override
    protected UUID generateId() {
        return UUID.randomUUID();
    }
}
