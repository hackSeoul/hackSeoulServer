package com.hackseoul.repository;

import com.hackseoul.domain.Plant;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlantRepository extends JpaRepository<Plant, Long> {
    @Query("SELECT p FROM Plant p WHERE p.latitude BETWEEN :latitudeMin AND :latitudeMax AND p.longitude BETWEEN :longitudeMin AND :longitudeMax")
    List<Plant> findAllByLatitudeAndLongitudeRange(
            @Param("latitudeMin") double latitudeMin,
            @Param("latitudeMax") double latitudeMax,
            @Param("longitudeMin") double longitudeMin,
            @Param("longitudeMax") double longitudeMax
    );
    List<Plant> findAll();
    List<Plant> findAllByNickName(String nickName);

    Optional<Plant> findById(Long id);


}
