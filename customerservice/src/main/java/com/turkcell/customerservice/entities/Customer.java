package com.turkcell.customerservice.entities;

import com.turkcell.customerservice.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "customers")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Customer extends BaseEntity<Long> {

    @Column(name = "customer_number", nullable = false, unique = true)
    private String customerNumber;

    @OneToOne(mappedBy = "customer")
    private Contact contact;

    @OneToMany(mappedBy = "customer")
    private List<Address> addresses;
}
