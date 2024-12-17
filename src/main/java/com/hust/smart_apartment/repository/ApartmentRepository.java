package com.hust.smart_apartment.repository;

import com.hust.smart_apartment.entity.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    @Modifying
    int deleteApartmentByApartmentId(Long id);
}
