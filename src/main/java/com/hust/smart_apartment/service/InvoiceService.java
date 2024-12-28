package com.hust.smart_apartment.service;

import com.hust.smart_apartment.dto.request.SearchRequest;
import com.hust.smart_apartment.dto.response.InvoiceResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface InvoiceService {
    Page<InvoiceResponse> search(SearchRequest request);
    String importInvoice(MultipartFile file);
}
