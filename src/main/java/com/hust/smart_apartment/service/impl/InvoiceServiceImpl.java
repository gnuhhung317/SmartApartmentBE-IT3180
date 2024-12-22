package com.hust.smart_apartment.service.impl;

import com.hust.smart_apartment.dto.request.SearchRequest;
import com.hust.smart_apartment.dto.response.InvoiceResponse;
import com.hust.smart_apartment.dto.response.InvoiceSearchResponse;
import com.hust.smart_apartment.mapper.InvoiceMapper;
import com.hust.smart_apartment.repository.InvoiceRepository;
import com.hust.smart_apartment.repository.SearchRepository;
import com.hust.smart_apartment.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceMapper invoiceMapper;
    private final InvoiceRepository invoiceRepository;
    private final SearchRepository<InvoiceSearchResponse> searchRepository;

    @Override
    public Page<InvoiceResponse> search(SearchRequest request) {
        Page<InvoiceSearchResponse> page = searchRepository.search(request, InvoiceSearchResponse.class);
        List<InvoiceResponse> responseList = page.stream()
                .map(invoiceMapper::convertSearchResponseToResponse)
                .toList();
        return new PageImpl<>(responseList, page.getPageable(), page.getTotalElements());
    }
}
