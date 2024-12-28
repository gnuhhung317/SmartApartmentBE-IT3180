package com.hust.smart_apartment.service.impl;

import com.hust.smart_apartment.constants.CodeGeneratorType;
import com.hust.smart_apartment.constants.FeeCategory;
import com.hust.smart_apartment.constants.InvoiceStatus;
import com.hust.smart_apartment.dto.FeeInvoiceDto;
import com.hust.smart_apartment.entity.*;
import com.hust.smart_apartment.repository.*;
import com.hust.smart_apartment.service.InvoiceJobService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
@Log4j2
public class InvoiceJobServiceImpl implements InvoiceJobService {
    private final InvoiceRepository invoiceRepository;
    private final ApartmentRepository apartmentRepository;
    private final CodeGeneratorRepository codeGeneratorRepository;
    private final FeeTypeRepository feeTypeRepository;
    private static int staticMonth = 0;
    @Override
    @Transactional
//    @Scheduled(cron = "*/4 * * * * ?")
    public void createInvoiceEveryMonth() {
        LocalDateTime time = LocalDateTime.now();
        log.info("Create invoice every month at"+ new Date());
        List<Apartment> apartments = apartmentRepository.findApartmentsByResidentIdNotNull();
        Map<Long, List<FeeInvoiceDto>> fees = getFees(apartments);

        CodeGenerator codeGenerator = codeGeneratorRepository.findById(CodeGeneratorType.INVOICE).orElseThrow(() -> new RuntimeException("Code generator not found"));
        String prefix =codeGenerator.getPrefix().getPrefix();
        AtomicInteger lastIndex = new AtomicInteger(codeGenerator.getLastIndex());
        List<Invoice> invoices =apartments.stream().map(apartment -> {

            StringBuilder code = new StringBuilder();
            String formattedNumber = String.format("%08d", lastIndex.incrementAndGet()); // format thành 6 chữ số
            code.append(prefix).append(formattedNumber);

            Invoice invoice = new Invoice();
            invoice.setApartment(apartment);
            invoice.setStatus(InvoiceStatus.CHUA_THANH_TOAN.getName());
            invoice.setStartDate(time);
            invoice.setDueDate(time.plusDays(7));
            invoice.setPaidAmount(0.0);
            invoice.setFees(fees.get(apartment.getApartmentId()));
            invoice.setTotalAmount(calculateTotalFee(invoice.getFees()));
            invoice.setInvoiceCode(code.toString());

            invoice.setInternetFee(FeeInvoiceDto.builder().feeName("Hóa đơn internet tháng "+time.getMonth()).feeAmount(0.0).feeDescription("Đây là một loại phí ok").build());
            invoice.setWaterFee(FeeInvoiceDto.builder().feeName("Hóa đơn nước tháng "+time.getMonth()).feeAmount(0.0).feeDescription("Đây là một loại phí ok").build());
            invoice.setElectricityFee(FeeInvoiceDto.builder().feeName("Hóa đơn điện tháng "+time.getMonth()).feeAmount(0.0).feeDescription("Đây là một loại phí ok").build());

            return invoice;
        }).toList();

        invoiceRepository.saveAll(invoices);
        codeGenerator.setLastIndex(lastIndex.get());
        codeGeneratorRepository.save(codeGenerator);
    }
    public Map<Long, List<FeeInvoiceDto>> getFees(List<Apartment> apartments){
        Map<Long, List<FeeInvoiceDto>> result = new HashMap<>();
        for (Apartment apartment:apartments) {
            result.put(apartment.getApartmentId(), getFeesByApartment(apartment));
        }
        return result;
    }
    private List<FeeInvoiceDto> getFeesByApartment(Apartment apartment){
        FeeInvoiceDto managementFee = FeeInvoiceDto.builder().feeName(FeeCategory.MANAGEMENT_FEE.getName()).feeAmount(0.0).feeDescription("Đây là một loại phí ok").build();
        FeeInvoiceDto serviceFee = FeeInvoiceDto.builder().feeName(FeeCategory.SERVICE_FEE.getName()).feeAmount(0.0).feeDescription("Đây là một loại phí ok").build();
        FeeInvoiceDto carParkingFee = FeeInvoiceDto.builder().feeName(FeeCategory.PARKING_CAR.getName()).feeAmount(0.0).feeDescription("Đây là một loại phí ok").build();
        FeeInvoiceDto motorcycleParkingFee = FeeInvoiceDto.builder().feeName(FeeCategory.PARKING_MOTORCYCLE.getName()).feeAmount(0.0).feeDescription("Đây là một loại phí ok").build();
        apartment.getVehicles().forEach(vehicle -> {
            VehicleType feeCategory = vehicle.getVehicleType();
            if(FeeCategory.PARKING_CAR.equals(feeCategory.getFeeCategory())){
                carParkingFee.addFeeAmount(feeCategory.getUnitPrice().doubleValue());
            }else if(FeeCategory.PARKING_MOTORCYCLE.equals(feeCategory.getFeeCategory())){
                motorcycleParkingFee.addFeeAmount(feeCategory.getUnitPrice().doubleValue());
            }
        });
        FeeType managementFeeType = apartment.getManagementFee();
        FeeType serviceFeeType = apartment.getServiceFee();
        managementFee.setFeeAmount(managementFeeType.getUnitPrice()*apartment.getArea().doubleValue());
        managementFee.setFeeDescription(managementFeeType.getDescription());
        serviceFee.setFeeAmount(serviceFeeType.getUnitPrice()*apartment.getArea().doubleValue());
        serviceFee.setFeeDescription(serviceFeeType.getDescription());
        return List.of(managementFee,serviceFee,carParkingFee,motorcycleParkingFee);
    }

    private Double calculateTotalFee(List<FeeInvoiceDto> fees){return fees.stream().mapToDouble(FeeInvoiceDto::getFeeAmount).sum();}
}
