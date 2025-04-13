package com.turkcell.planservice.entities;

import io.github.ertansidar.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "packages")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @Override
    protected UUID generateId() {
        return UUID.randomUUID();
    }

}
