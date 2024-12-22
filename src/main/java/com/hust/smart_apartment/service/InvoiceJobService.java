package com.hust.smart_apartment.service;

import org.springframework.stereotype.Service;

@Service
public interface InvoiceJobService {

    void createInvoiceEveryMonth();
}
