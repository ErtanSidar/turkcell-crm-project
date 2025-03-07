package com.turkcell.customerservice.entities;

import io.github.ertansidar.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "campaigns")
public class Campaign extends BaseEntity<UUID> {

    @Column(name = "campaign_id")
    private UUID campaignId;

    @Column(name = "campaign_name")
    private String campaignName;

    @Column(name = "discount_percentage")
    private double discountPercentage;

    @Column(name = "valid_from")
    private String validFrom;

    @Column(name = "valid_until")
    private String validUntil;

}
