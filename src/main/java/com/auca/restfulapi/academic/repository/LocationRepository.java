package com.auca.restfulapi.academic.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auca.restfulapi.academic.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location,UUID > {
    
    Boolean existsByCode(String code);
    Location findByCode(String code);
}
