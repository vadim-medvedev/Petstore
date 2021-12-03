package com.example.demo.repository;

import com.example.demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Order, Long> {
    boolean existsById(long id);
    Optional<Order> getById(long id);
}
