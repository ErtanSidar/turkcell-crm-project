package com.turkcell.customerservice.entities;

import io.github.ertansidar.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "customers")
@Inheritance(strategy = InheritanceType.JOINED)
public class Customer extends BaseEntity<UUID> {

    @Column(name = "customer_number", nullable = false, unique = true)
    private String customerNumber;

    @OneToOne(mappedBy = "customer")
    private Contact contact;

    @OneToMany(mappedBy = "customer")
    private List<Address> addresses;
}
