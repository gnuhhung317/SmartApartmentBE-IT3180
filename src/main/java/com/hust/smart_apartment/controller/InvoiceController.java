package com.hust.smart_apartment.controller;

import com.hust.smart_apartment.dto.BaseResponse;
import com.hust.smart_apartment.dto.request.SearchRequest;
import com.hust.smart_apartment.dto.response.InvoiceResponse;
import com.hust.smart_apartment.service.InvoiceService;
import com.hust.smart_apartment.service.impl.InvoiceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/invoices")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    @PostMapping("/search")
    public BaseResponse<Page<InvoiceResponse>> search(@RequestBody SearchRequest request) {
        Page<InvoiceResponse> response = invoiceService.search(request);
        return BaseResponse.ok(response);
    }
    @PostMapping("/import")
    public BaseResponse<String> importInvoice(@RequestParam("file") MultipartFile file) {
        String response = invoiceService.importInvoice(file);
        return BaseResponse.ok(response);
    }
}
