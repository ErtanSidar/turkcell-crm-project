package com.turkcell.planservice.entities;

import io.github.ertansidar.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "subscriptions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Subscription extends BaseEntity<UUID> {

    @Column(name = "customer_id")
    private UUID customerId;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "subscription")
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;


    @Override
    protected UUID generateId() {
        return UUID.randomUUID();
    }

}
