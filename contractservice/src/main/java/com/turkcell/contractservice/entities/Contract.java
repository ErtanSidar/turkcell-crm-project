package com.turkcell.contractservice.entities;

import io.github.ertansidar.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "contracts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @Override
    protected UUID generateId() {
        return UUID.randomUUID();
    }
}
