package com.turkcell.planservice.entities;

import io.github.ertansidar.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "plans")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Plan extends BaseEntity<UUID> {

    @Column(name = "plan_name")
    private String planName;

    @Column(name = "plan_type")
    private String planType;

    @Column(name = "monthly_fee")
    private double monthlyFee;

    @Column(name = "internet_quota")
    private int internetQuota;

    @Column(name = "call_minutes")
    private int callMinutes;

    @Column(name = "sms_count")
    private int smsCount;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "plan")
    private List<Product> products;

    @OneToMany(mappedBy = "plan")
    private List<Subscription> subscriptions;


    @Override
    protected UUID generateId() {
        return UUID.randomUUID();
    }

}
