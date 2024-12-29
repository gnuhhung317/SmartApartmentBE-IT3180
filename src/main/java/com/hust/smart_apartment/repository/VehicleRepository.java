package com.hust.smart_apartment.repository;

import com.hust.smart_apartment.dto.CountDto;
import com.hust.smart_apartment.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {

    @Query("SELECT new com.hust.smart_apartment.dto.CountDto(v.apartment.apartmentId, COUNT(v.vehicleId)) " +
            "FROM Vehicle v " +
            "WHERE v.apartment.apartmentId IN :ids " +
            "GROUP BY v.apartment.apartmentId")
    List<CountDto> countAllByApartmentIdId(List<Long> ids);
}
