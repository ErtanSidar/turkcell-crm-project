package com.turkcell.customerservice.entities;

import io.github.ertansidar.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "contacts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contact extends BaseEntity<UUID> {

    @Column(name = "email")
    private String email;

    @Column(name = "home_phone")
    private String homePhone;

    @Column(name = "mobile_phone")
    private String mobilePhone;

    @Column(name = "fax")
    private String fax;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
