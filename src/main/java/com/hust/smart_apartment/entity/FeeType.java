package com.hust.smart_apartment.entity;

import com.hust.smart_apartment.constants.FeeCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "fee_types")
public class FeeType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feeTypeId;

    @Enumerated(EnumType.STRING)
    private FeeCategory category;

    private BigDecimal unitPrice;

    private String description;
}