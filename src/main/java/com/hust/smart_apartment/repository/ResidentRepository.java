package com.hust.smart_apartment.repository;

import com.hust.smart_apartment.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResidentRepository extends JpaRepository<Resident, Long> {

    @Modifying
    int deleteResidentByResidentIdNotInAndLivingApartment(List<Long> residentIds, Long apartmentId);
}
