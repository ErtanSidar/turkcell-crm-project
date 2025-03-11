package com.turkcell.customerservice.repositories;

import com.turkcell.customerservice.entities.IndividualCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface IndividualCustomerRepository extends JpaRepository<IndividualCustomer, UUID> {
    @Query(value = "SELECT ic.* FROM individual_customers ic INNER JOIN customers c ON ic.customer_id=c.id WHERE c.deleted_date IS null And ic.nationality_id=:nationalityId", nativeQuery = true)
    Optional<IndividualCustomer> findByNationalityIdAndDeletedDateIsNull(@Param("nationalityId") String nationalityId);

}
