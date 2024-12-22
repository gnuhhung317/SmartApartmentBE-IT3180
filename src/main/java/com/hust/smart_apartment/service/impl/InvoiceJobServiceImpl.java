package com.hust.smart_apartment.service.impl;

import com.hust.smart_apartment.constants.FeeCategory;
import com.hust.smart_apartment.constants.InvoiceStatus;
import com.hust.smart_apartment.dto.FeeInvoiceDto;
import com.hust.smart_apartment.entity.*;
import com.hust.smart_apartment.repository.*;
import com.hust.smart_apartment.service.InvoiceJobService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class InvoiceJobServiceImpl implements InvoiceJobService {
    private final InvoiceRepository invoiceRepository;
    private final ApartmentRepository apartmentRepository;
    private final FeeTypeRepository feeTypeRepository;

    @Override
    @Transactional
    public void createInvoiceEveryMonth() {

        log.info("Create invoice every month at"+ new Date());
        List<Apartment> apartments = apartmentRepository.findAll();
        Map<Long, List<FeeInvoiceDto>> fees = getFees(apartments);
        List<Invoice> invoices =apartments.stream().map(apartment -> {
            Invoice invoice = new Invoice();
            invoice.setApartment(apartment);
            invoice.setStatus(InvoiceStatus.CHUA_THANH_TOAN.getName());
            invoice.setStartDate(LocalDateTime.now());
            invoice.setDueDate(LocalDateTime.now().plusDays(7));
            invoice.setPaidAmount(0);
            invoice.setFees(fees.get(apartment.getApartmentId()));
            invoice.setTotalAmount(calculateTotalFee(invoice.getFees()));

            return invoice;
        }).toList();

        invoiceRepository.saveAll(invoices);
    }
    public Map<Long, List<FeeInvoiceDto>> getFees(List<Apartment> apartments){
        Map<Long, List<FeeInvoiceDto>> result = new HashMap<>();
        for (Apartment apartment:apartments) {
            result.put(apartment.getApartmentId(), getFeesByApartment(apartment));
        }
        return result;
    }
    private List<FeeInvoiceDto> getFeesByApartment(Apartment apartment){
        FeeInvoiceDto managementFee = FeeInvoiceDto.builder().feeName(FeeCategory.MANAGEMENT_FEE.getName()).feeAmount(0).feeDescription("Đây là một loại phí ok").build();
        FeeInvoiceDto serviceFee = FeeInvoiceDto.builder().feeName(FeeCategory.SERVICE_FEE.getName()).feeAmount(0).feeDescription("Đây là một loại phí ok").build();
        FeeInvoiceDto carParkingFee = FeeInvoiceDto.builder().feeName(FeeCategory.PARKING_CAR.getName()).feeAmount(0).feeDescription("Đây là một loại phí ok").build();
        FeeInvoiceDto motorcycleParkingFee = FeeInvoiceDto.builder().feeName(FeeCategory.PARKING_MOTORCYCLE.getName()).feeAmount(0).feeDescription("Đây là một loại phí ok").build();
        apartment.getVehicles().forEach(vehicle -> {

            VehicleType feeCategory = vehicle.getVehicleType();
            if(FeeCategory.PARKING_CAR.equals(feeCategory.getFeeCategory())){
                carParkingFee.addFeeAmount(feeCategory.getUnitPrice());
            }else if(FeeCategory.PARKING_MOTORCYCLE.equals(feeCategory.getFeeCategory())){
                motorcycleParkingFee.addFeeAmount(feeCategory.getUnitPrice());
            }
        });
        FeeType managementFeeType = apartment.getManagementFee();
        FeeType serviceFeeType = apartment.getServiceFee();
        managementFee.setFeeAmount(managementFeeType.getUnitPrice());
        managementFee.setFeeDescription(managementFeeType.getDescription());
        serviceFee.setFeeAmount(serviceFeeType.getUnitPrice());
        serviceFee.setFeeDescription(serviceFeeType.getDescription());
        return List.of(managementFee,serviceFee,carParkingFee,motorcycleParkingFee);
    }

    private Integer calculateTotalFee(List<FeeInvoiceDto> fees){return fees.stream().mapToInt(FeeInvoiceDto::getFeeAmount).sum();}
}
