package com.hust.smart_apartment.mapper;

import com.hust.smart_apartment.dto.request.InvoiceRequest;
import com.hust.smart_apartment.dto.response.InvoiceResponse;
import com.hust.smart_apartment.dto.response.InvoiceSearchResponse;
import com.hust.smart_apartment.entity.Invoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class InvoiceMapper extends BaseMapper<InvoiceRequest, InvoiceResponse, Invoice> {

    @Override
    public abstract InvoiceResponse entityToResponse(Invoice invoice) ;

    @Override
    public abstract Invoice requestToEntity(InvoiceRequest invoiceRequest) ;

    public abstract InvoiceResponse convertSearchResponseToResponse(InvoiceSearchResponse response) ;
}
