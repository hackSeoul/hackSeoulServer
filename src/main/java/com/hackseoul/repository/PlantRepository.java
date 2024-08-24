package com.hackseoul.repository;

import com.hackseoul.domain.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlantRepository extends JpaRepository<Plant, Long> {
    List<Plant> findAll();

    Optional<Plant> findById(Long id);
}
