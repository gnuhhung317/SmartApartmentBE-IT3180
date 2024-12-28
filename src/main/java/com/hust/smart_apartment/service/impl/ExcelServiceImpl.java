package com.hust.smart_apartment.service.impl;

import com.hust.smart_apartment.entity.Apartment;
import com.hust.smart_apartment.entity.Invoice;
import com.hust.smart_apartment.repository.ApartmentRepository;
import com.hust.smart_apartment.repository.InvoiceRepository;
import com.hust.smart_apartment.service.ExcelService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;


@Service
@RequiredArgsConstructor
@Log4j2
public class ExcelServiceImpl implements ExcelService {

    private final InvoiceRepository invoiceRepository;
    @Override
    @Transactional
    public String importInvoice(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)){
             Sheet sheet = workbook.getSheetAt(0);
            Map<String, Invoice> invoiceMap = invoiceRepository.findLastCreatedInvoices()
                    .stream().collect(Collectors.toMap(i -> i.getApartment().getCode(), i -> i));
            int rows = sheet.getPhysicalNumberOfRows();
             for (int i = 1; i < rows; i++) {
                 String apartmentCode = sheet.getRow(i).getCell(0).getStringCellValue();
                 double electricity = sheet.getRow(i).getCell(1).getNumericCellValue();
                 double water = sheet.getRow(i).getCell(2).getNumericCellValue();
                 double internet = sheet.getRow(i).getCell(3).getNumericCellValue();

                 Invoice invoice = invoiceMap.get(apartmentCode);
                 if (invoice != null) {
                     invoice.getElectricityFee().setFeeAmount(electricity);
                     invoice.getWaterFee().setFeeAmount(water);
                     invoice.getInternetFee().setFeeAmount(internet);
                 }
             }
             invoiceRepository.saveAll(invoiceMap.values());
            return "Success";
        }catch (Exception e){
            log.error(e.getMessage());
            return "Fail";
        }
    }
}
