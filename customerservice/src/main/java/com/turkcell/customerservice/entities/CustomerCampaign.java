package com.turkcell.customerservice.entities;

import io.github.ertansidar.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.Where;

import java.util.UUID;

@Entity
@Table(name = "customer_campaigns")
@Where(clause = "deleted_at is null")
public class CustomerCampaign extends BaseEntity<UUID> {

    @Column(name = "customer_campaign_id")
    private UUID customerCampaignId;

    @Column(name = "customer_id")
    private UUID customerId;

    @Column(name = "campaign_id")
    private Long campaignId;

    @Column(name = "assigned_date")
    private String assignedDate;

    @Override
    protected UUID generateId() {
        return UUID.randomUUID();
    }

}
