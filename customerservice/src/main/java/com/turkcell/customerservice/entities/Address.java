package com.turkcell.customerservice.entities;

import com.turkcell.customerservice.core.entities.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public abstract class Address extends BaseEntity<Long> {

    @Column(name = "description")
    private String description;

    @Column(name = "neighbourhood")
    private String neighbourhood;

    @Column(name = "houseNumber")
    private String houseNumber;

    @Column(name = "street")
    private String street;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;
}
