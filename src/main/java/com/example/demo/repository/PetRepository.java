package com.example.demo.repository;

import com.example.demo.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> getByStatus(String status);
    Optional<Pet> getById(int id);
    boolean existsById(long id);
}
