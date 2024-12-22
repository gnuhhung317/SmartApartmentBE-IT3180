package com.hust.smart_apartment.repository;
import com.hust.smart_apartment.constants.FeeCategory;
import com.hust.smart_apartment.entity.FeeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeeTypeRepository extends JpaRepository<FeeType, Long> {
    Optional<FeeType> findByCategory(FeeCategory category);
}
