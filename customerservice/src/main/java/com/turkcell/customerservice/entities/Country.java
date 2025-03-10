package com.turkcell.customerservice.entities;

import io.github.ertansidar.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "countries")
public class Country extends BaseEntity<Long> {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "country")
    private List<City> cities;

    @OneToMany(mappedBy = "country")
    private List<Address> addresses;
}
