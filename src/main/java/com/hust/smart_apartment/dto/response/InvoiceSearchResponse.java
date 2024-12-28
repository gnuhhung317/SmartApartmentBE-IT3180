package com.hust.smart_apartment.dto.response;

import com.hust.smart_apartment.annotations.DbColumnMapper;
import com.hust.smart_apartment.annotations.QuickSearchDomain;
import com.hust.smart_apartment.constants.QuickSearchKeyOption;
import com.hust.smart_apartment.dto.model.QuickSearchInput;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@QuickSearchDomain(tableName = "invoices")
public class InvoiceSearchResponse {
    @DbColumnMapper("id")
    private Long id;

    @DbColumnMapper("invoice_code")
    @QuickSearchInput(columnName = "invoice_code", keyOption = QuickSearchKeyOption.ILIKE)
    private String invoiceCode;

    @DbColumnMapper("apartment_id")
    private Long apartmentId;

    @DbColumnMapper("due_date")
    private Date dueDate;

    @DbColumnMapper("start_date")
    private Date startDate;

    @DbColumnMapper("completed_pay_date")
    private Date completedPayDate;

    @DbColumnMapper("last_pay_date")
    private Date lastPayDate;

    @DbColumnMapper("total_amount")
    private Double totalAmount;

    @DbColumnMapper("paid_amount")
    private Double paidAmount;

    @DbColumnMapper("status")
    private String status;
}
