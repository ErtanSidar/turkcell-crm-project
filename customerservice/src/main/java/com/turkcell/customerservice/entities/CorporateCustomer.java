package com.turkcell.customerservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "corporate_customer")
@PrimaryKeyJoinColumn(name = "customer_id")
public class CorporateCustomer extends Customer {

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "tax_number", nullable = false)
    private String taxNumber;
}
