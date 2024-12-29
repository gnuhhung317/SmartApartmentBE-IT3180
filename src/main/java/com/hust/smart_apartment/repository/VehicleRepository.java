package com.hust.smart_apartment.repository;

import com.hust.smart_apartment.dto.CountDto;
import com.hust.smart_apartment.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {

    @Query("SELECT new com.hust.smart_apartment.dto.CountDto(v.vehicleId, COUNT(v.vehicleId))" +
            "FROM Vehicle v " +
            "GROUP BY v.apartment.apartmentId " +
            "HAVING v.apartment.apartmentId IN :ids")
    List<CountDto> countAllByApartmentIdId(List<Long> ids);
}
