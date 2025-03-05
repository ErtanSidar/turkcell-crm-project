package com.turkcell.customerservice.entities;

import com.turkcell.customerservice.core.entities.BaseEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cities")
public abstract class City extends BaseEntity<Long> {

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(mappedBy = "city")
    private List<District> districts;

    @OneToMany(mappedBy = "city")
    private List<Address> adresses;
}
