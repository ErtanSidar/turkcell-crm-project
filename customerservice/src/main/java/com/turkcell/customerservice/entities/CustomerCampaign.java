package com.turkcell.customerservice.entities;

import io.github.ertansidar.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "customer_campaigns")
@Where(clause = "deleted_at is null")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCampaign extends BaseEntity<UUID> {

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "campaign_id", nullable = false)
    private Campaign campaign;

    @Column(name = "assigned_date")
    private LocalDate assignedDate;

    @Override
    protected UUID generateId() {
        return UUID.randomUUID();
    }

}
