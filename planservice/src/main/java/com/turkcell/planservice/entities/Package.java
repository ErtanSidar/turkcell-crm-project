package com.turkcell.planservice.entities;

import io.github.ertansidar.entities.BaseEntity;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "packages")
public class Package extends BaseEntity<UUID> {

    @Column(name = "package_name")
    private String packageName;

    @Column(name = "package_type")
    private String packageType;

    @Column(name = "quota")
    private int quota;

    @Column(name = "price")
    private double price;

    @Column(name = "validity_period")
    private int validityPeriod;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
