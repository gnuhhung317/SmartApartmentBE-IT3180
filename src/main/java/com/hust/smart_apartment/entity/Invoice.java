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

    private String invoiceCode;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    private LocalDateTime dueDate;

    private LocalDateTime startDate;

    private LocalDateTime completedPayDate;

    private LocalDateTime lastPayDate;

    private Double totalAmount;

    private Double paidAmount;

    private String status;

    @Lob
    private String note;

    @JdbcTypeCode(SqlTypes.JSON)
    private List<FeeInvoiceDto> fees;

    @JdbcTypeCode(SqlTypes.JSON)
    private FeeInvoiceDto waterFee = new FeeInvoiceDto();

    @JdbcTypeCode(SqlTypes.JSON)
    private FeeInvoiceDto electricityFee = new FeeInvoiceDto();

    @JdbcTypeCode(SqlTypes.JSON)
    private FeeInvoiceDto internetFee = new FeeInvoiceDto();
}
