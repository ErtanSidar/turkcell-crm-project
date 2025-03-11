package com.turkcell.customerservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "corporate_customer")
@PrimaryKeyJoinColumn(name = "customer_id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CorporateCustomer extends Customer {

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "tax_number", nullable = false)
    private String taxNumber;
}
