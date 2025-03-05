package com.turkcell.customerservice.entities;

import com.turkcell.customerservice.core.entities.BaseEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "districts")
public abstract class District extends BaseEntity<Long> {

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(mappedBy = "district")
    private List<Address> adresses;

}
