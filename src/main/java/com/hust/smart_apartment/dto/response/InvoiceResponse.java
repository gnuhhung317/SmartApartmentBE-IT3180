package com.hust.smart_apartment.dto.response;

import com.hust.smart_apartment.annotations.DbColumnMapper;
import com.hust.smart_apartment.annotations.QuickSearchDomain;
import com.hust.smart_apartment.constants.QuickSearchKeyOption;
import com.hust.smart_apartment.dto.FeeInvoiceDto;
import com.hust.smart_apartment.dto.model.QuickSearchInput;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@QuickSearchDomain(tableName = "invoices")
public class InvoiceResponse {

    @DbColumnMapper("id")
    private Long id;

    @DbColumnMapper("invoice_code")
    @QuickSearchInput(columnName = "invoice_code", keyOption = QuickSearchKeyOption.ILIKE)
    private String invoiceCode;

    private ApartmentResponse apartment;

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
    @DbColumnMapper("note")
    private String note;

    private List<FeeInvoiceDto> fees;
}
