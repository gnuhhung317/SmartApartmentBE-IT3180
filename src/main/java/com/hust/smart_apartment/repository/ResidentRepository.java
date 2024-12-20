package com.hust.smart_apartment.repository;

import com.hust.smart_apartment.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResidentRepository extends JpaRepository<Resident, Long> {

    @Modifying
    @Query(value = "delete from resident where resident_id not in ?1 and apartment_id = ?2", nativeQuery = true)
    int deleteResidentByResidentIdNotInAndLivingApartment(List<Long> residentIds, Long apartmentId);
}
