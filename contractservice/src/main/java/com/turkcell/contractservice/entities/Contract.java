package com.turkcell.contractservice.entities;

import io.github.ertansidar.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "contracts")
public class Contract extends BaseEntity<UUID> {

    @Column(name = "ticket_id")
    private UUID ticketId;

    @Column(name = "customer_id")
    private UUID customerId;

    @Column(name = "subject")
    private String subject;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

}
