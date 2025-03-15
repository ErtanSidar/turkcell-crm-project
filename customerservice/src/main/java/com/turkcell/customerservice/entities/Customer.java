package com.turkcell.customerservice.entities;

import io.github.ertansidar.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "customers")
@Inheritance(strategy = InheritanceType.JOINED)
@Where(clause = "deleted_at is null")
public class Customer extends BaseEntity<UUID> {

    @Column(name = "customer_number", nullable = false, unique = true)
    private String customerNumber;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Contact> contacts;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Address> addresses;

    @Override
    protected UUID generateId() {
        return UUID.randomUUID();
    }
}
