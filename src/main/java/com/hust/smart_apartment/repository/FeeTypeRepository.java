package com.hust.smart_apartment.repository;
import com.hust.smart_apartment.entity.FeeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeeTypeRepository extends JpaRepository<FeeType, Long> {
}
