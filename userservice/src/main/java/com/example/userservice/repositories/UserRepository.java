package com.example.userservice.repositories;

import com.example.userservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {


    // Kullanıcı adına göre kullanıcıyı getir
    Optional<User> findByUsername(String username);

    // Kullanıcı adı mevcut mu?
    boolean existsByUsername(String username);
}
