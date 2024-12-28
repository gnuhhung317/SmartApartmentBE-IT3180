package com.hust.smart_apartment;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Test {

    public static void main(String[] args) {
        // Danh sách các mã căn hộ
        List<String> apartmentCodes = Arrays.asList(
                "101", "102", "103", "104", "201", "202", "203", "204",
                "301", "302", "303", "401", "402", "403", "404", "501",
                "502", "503", "504", "505", "506", "601", "602"
        );

        // Tạo workbook và sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Invoices");

        // Tạo dòng tiêu đề
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Apartment Code");
        headerRow.createCell(1).setCellValue("Electricity");
        headerRow.createCell(2).setCellValue("Water");
        headerRow.createCell(3).setCellValue("Internet");

        // Tạo nội dung cho các dòng tiếp theo
        Random random = new Random();
        for (int i = 0; i < apartmentCodes.size(); i++) {
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(apartmentCodes.get(i));
            row.createCell(1).setCellValue(50 + random.nextDouble() * 100); // Giá trị ngẫu nhiên cho tiền điện
            row.createCell(2).setCellValue(30 + random.nextDouble() * 70);  // Giá trị ngẫu nhiên cho tiền nước
            row.createCell(3).setCellValue(20 + random.nextDouble() * 50);  // Giá trị ngẫu nhiên cho tiền mạng
        }

        // Ghi workbook vào file
        try (FileOutputStream fileOut = new FileOutputStream("invoices_test.xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Đóng workbook
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Excel file created successfully.");
    }
}