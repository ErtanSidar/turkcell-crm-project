package com.turkcell.customerservice.repositories;

import com.turkcell.customerservice.entities.CustomerCampaign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerCampaignRepository extends JpaRepository<CustomerCampaign, UUID> {
}
