package com.hust.smart_apartment.repository;

import com.hust.smart_apartment.constants.FeeCategory;
import com.hust.smart_apartment.entity.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleTypeRepository extends JpaRepository<VehicleType,Long> {

    Optional<VehicleType> findByFeeCategory(FeeCategory category);
}
