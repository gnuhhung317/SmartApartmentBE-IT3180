package com.hust.smart_apartment.repository;
import com.hust.smart_apartment.entity.Floor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Long> {
}
