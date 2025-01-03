package com.hust.smart_apartment.entity;

import com.hust.smart_apartment.constants.LivingType;
import com.hust.smart_apartment.dto.response.ApartmentResponse;
import com.hust.smart_apartment.dto.response.ResidentResponse;
import jakarta.persistence.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "resident_change_log")
public class ResidentChangeLog extends BaseEntity{

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "resident_id")
    private Long residentId;

    @Column(name = "apartment_id")
    private Long apartmentId;

    @JdbcTypeCode(SqlTypes.JSON)
    private ResidentResponse resident;

    @JdbcTypeCode(SqlTypes.JSON)
    private ApartmentResponse apartment;

    @Column(name = "last_type")
    private LivingType lastType;

    @Column(name = "change_type")
    private LivingType changeType;

    @Column(name = "change_date")
    private Date changeDate;

    @Column(name = "notes")
    private String notes;
}