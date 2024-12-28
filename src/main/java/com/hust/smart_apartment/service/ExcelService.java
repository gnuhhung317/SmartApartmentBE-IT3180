package com.hust.smart_apartment.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ExcelService {

    String importInvoice(MultipartFile file);
}
