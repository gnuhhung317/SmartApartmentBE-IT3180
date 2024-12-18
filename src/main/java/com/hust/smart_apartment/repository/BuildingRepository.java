package com.hust.smart_apartment.repository;
import com.hust.smart_apartment.entity.Building;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
}
