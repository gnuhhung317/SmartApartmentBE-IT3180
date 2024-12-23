package com.hust.smart_apartment.entity;

import com.hust.smart_apartment.dto.FeeInvoiceDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "invoices")
public class Invoice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    private LocalDateTime dueDate;

    private LocalDateTime startDate;

    private LocalDateTime completedPayDate;

    private LocalDateTime lastPayDate;

    private Integer totalAmount;

    private Integer paidAmount;

    private String status;

    @JdbcTypeCode(SqlTypes.JSON)
    private List<FeeInvoiceDto> fees;
}
