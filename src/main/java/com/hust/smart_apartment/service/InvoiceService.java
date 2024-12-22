package com.hust.smart_apartment.service;

import com.hust.smart_apartment.dto.request.SearchRequest;
import com.hust.smart_apartment.dto.response.InvoiceResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface InvoiceService {
    public Page<InvoiceResponse> search(SearchRequest request);
}
