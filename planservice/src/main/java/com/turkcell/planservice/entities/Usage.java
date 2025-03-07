package com.turkcell.planservice.entities;

import io.github.ertansidar.entities.BaseEntity;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "usages")
public class Usage extends BaseEntity<UUID> {

    @Column(name = "customer_id")
    private UUID customerId;

    @Column(name = "internet_used")
    private int internetUsed;

    @Column(name = "call_minutes_used")
    private int callMinutesUsed;

    @Column(name = "sms_used")
    private int smsUsed;

    @Column(name = "tv_hours_watched")
    private int tvHoursWatched;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
