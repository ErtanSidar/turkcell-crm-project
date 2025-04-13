package com.turkcell.planservice.entities;

import io.github.ertansidar.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity<UUID> {

    @Column(name = "product_name")
    private String productName;

    @Column(name = "description")
    private String description;

    @Column(name = "product_type")
    private ProductType productType;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;


    @OneToMany(mappedBy = "product")
    private List<Usage> usages;


    @ManyToOne
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;


    @Override
    protected UUID generateId() {
        return UUID.randomUUID();
    }

}
