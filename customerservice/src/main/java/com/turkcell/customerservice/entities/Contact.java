package com.turkcell.customerservice.entities;

import io.github.ertansidar.entities.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact extends BaseEntity<Long> {

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
