package com.hust.smart_apartment.service.impl;

import com.hust.smart_apartment.dto.request.SearchRequest;
import com.hust.smart_apartment.dto.response.ApartmentResponse;
import com.hust.smart_apartment.dto.response.InvoiceResponse;
import com.hust.smart_apartment.dto.response.InvoiceSearchResponse;
import com.hust.smart_apartment.entity.Apartment;
import com.hust.smart_apartment.mapper.ApartmentMapper;
import com.hust.smart_apartment.mapper.InvoiceMapper;
import com.hust.smart_apartment.repository.ApartmentRepository;
import com.hust.smart_apartment.repository.InvoiceRepository;
import com.hust.smart_apartment.repository.SearchRepository;
import com.hust.smart_apartment.service.ExcelService;
import com.hust.smart_apartment.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceMapper invoiceMapper;
    private final InvoiceRepository invoiceRepository;
    private final SearchRepository<InvoiceSearchResponse> searchRepository;
    private final ApartmentMapper apartmentMapper;
    private final ApartmentRepository apartmentRepository;
    private final ExcelService excelService;
    @Override
    public Page<InvoiceResponse> search(SearchRequest request) {
        Page<InvoiceSearchResponse> page = searchRepository.search(request, InvoiceSearchResponse.class);
        Set<Long> apartmentIds = page.stream().map(InvoiceSearchResponse::getApartmentId).collect(Collectors.toSet());
        Map<Long, ApartmentResponse> apartmentResponseMap = apartmentRepository.findAllById(apartmentIds).stream().collect(Collectors.toMap(Apartment::getApartmentId, apartmentMapper::entityToResponse));
        List<InvoiceResponse> responseList = page.stream()
                .map(x->{
                    InvoiceResponse response = invoiceMapper.convertSearchResponseToResponse(x);
                    response.setApartment(apartmentResponseMap.get(x.getApartmentId()));
                    return response;
                })
                .toList();
        return new PageImpl<>(responseList, page.getPageable(), page.getTotalElements());
    }

    @Override
    public String importInvoice(MultipartFile file) {
        return excelService.importInvoice(file);
    }
}
