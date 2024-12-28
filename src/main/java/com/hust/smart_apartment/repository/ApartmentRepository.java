package com.hust.smart_apartment.repository;

import com.hust.smart_apartment.entity.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    @Modifying
    int deleteApartmentByApartmentId(Long id);

    @Query("""
    SELECT a FROM Apartment a
    WHERE a.owner IS NOT NULL
    """)
    List<Apartment> findApartmentsByResidentIdNotNull();
}
