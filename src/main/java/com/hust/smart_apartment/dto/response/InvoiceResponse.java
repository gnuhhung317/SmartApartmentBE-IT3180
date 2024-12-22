package com.hust.smart_apartment.dto.response;

import com.hust.smart_apartment.annotations.QuickSearchDomain;
import com.hust.smart_apartment.dto.FeeInvoiceDto;
import com.hust.smart_apartment.entity.Apartment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceResponse {

    private Long id;

    private ApartmentResponse apartment;

    private LocalDateTime dueDate;

    private LocalDateTime startDate;

    private LocalDateTime completedPayDate;

    private LocalDateTime lastPayDate;

    private Integer totalAmount;

    private Integer paidAmount;

    private String status;

    private List<FeeInvoiceDto> fees;
}
