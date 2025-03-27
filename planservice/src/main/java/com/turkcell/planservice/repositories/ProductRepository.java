package com.turkcell.planservice.repositories;

import com.turkcell.planservice.dtos.productdtos.responses.ProductResponse;
import com.turkcell.planservice.entities.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {


    boolean existsByProductName(String productName);

    Optional<Product> findByIdAndDeletedAtIsNull(UUID id);

    @Transactional
    @Modifying
    @Query("UPDATE Product e SET e.deletedAt = :deletedAt, e.deletedBy = :deletedBy WHERE e.id = :id")
    void softDelete(@Param("id") UUID id, @Param("deletedAt") LocalDateTime deletedAt, @Param("deletedBy") String deletedBy);
}
