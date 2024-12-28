package com.hust.smart_apartment.repository;

import com.hust.smart_apartment.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query("SELECT i FROM Invoice i WHERE i.startDate = (SELECT MAX(i2.startDate) FROM Invoice i2)")
    List<Invoice> findLastCreatedInvoices();
}
