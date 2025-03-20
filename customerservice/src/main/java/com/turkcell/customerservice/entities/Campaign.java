package com.turkcell.customerservice.entities;

import io.github.ertansidar.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "campaigns")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "deleted_at is null")
public class Campaign extends BaseEntity<UUID> {

    @Column(name = "campaign_name")
    private String name;

    @Column(name = "discount_percentage")
    private double discountPercentage;

    @Column(name = "valid_from")
    private String validFrom;

    @Column(name = "valid_until")
    private String validUntil;

    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL)
    private List<CustomerCampaign> customerCampaigns;

    @Override
    protected UUID generateId() {
        return UUID.randomUUID();
    }

}
