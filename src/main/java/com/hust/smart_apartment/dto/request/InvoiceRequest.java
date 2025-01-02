package com.hust.smart_apartment.dto.request;

import com.hust.smart_apartment.constants.InvoiceStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceRequest {

    private String note;
    private InvoiceStatus status;
}
