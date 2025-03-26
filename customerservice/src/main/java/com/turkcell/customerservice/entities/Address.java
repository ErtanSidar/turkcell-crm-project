package com.turkcell.customerservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.ertansidar.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.util.UUID;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "deleted_at is null")
public class Address extends BaseEntity<UUID> {

    @Column(name = "description")
    private String description;

    @Column(name = "neighbourhood")
    private String neighbourhood;

    @Column(name = "houseNumber")
    private String houseNumber;

    @Column(name = "street")
    private String street;

    @Enumerated(EnumType.STRING)
    private AddressType addressType;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "country_id")
    @JsonIgnore
    private Country country;

    @ManyToOne
    @JoinColumn(name = "city_id")
    @JsonIgnore
    private City city;

    @ManyToOne
    @JoinColumn(name = "district_id")
    @JsonIgnore
    private District district;

    @Override
    protected UUID generateId() {
        return UUID.randomUUID();
    }
}
