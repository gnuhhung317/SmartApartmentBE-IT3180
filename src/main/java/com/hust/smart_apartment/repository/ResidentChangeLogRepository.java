package com.hust.smart_apartment.repository;

import com.hust.smart_apartment.entity.ResidentChangeLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidentChangeLogRepository extends JpaRepository<ResidentChangeLog, Long> {
}
