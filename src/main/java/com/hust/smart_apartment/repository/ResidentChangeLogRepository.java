package com.hust.smart_apartment.repository;

import com.hust.smart_apartment.entity.ResidentChangeLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ResidentChangeLogRepository extends JpaRepository<ResidentChangeLog, Long> {

    @Query("SELECT MAX(r2.changeDate) FROM ResidentChangeLog r2")
    Date findResidentChangeLogByChangeDateMax();
}
