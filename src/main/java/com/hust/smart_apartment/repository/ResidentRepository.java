package com.hust.smart_apartment.repository;

import com.hust.smart_apartment.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidentRepository extends JpaRepository<Long, Resident> {
}
