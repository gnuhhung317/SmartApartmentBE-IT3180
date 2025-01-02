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
    @QuickSearchInput(columnName = "invoice_code", keyOption = QuickSearchKeyOption.LIKE)
    private String invoiceCode;

    @DbColumnMapper("apartment_id")
    private Long apartmentId;

    @DbColumnMapper("note")
    @QuickSearchInput(columnName = "note", keyOption = QuickSearchKeyOption.LIKE)
    private String note;


}
