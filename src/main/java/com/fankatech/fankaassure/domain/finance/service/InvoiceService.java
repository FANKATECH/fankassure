package com.fankatech.fankaassure.domain.finance.service;

import com.fankatech.fankaassure.dto.finance.InvoiceDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {
    public InvoiceDTO generateInvoice(@Valid InvoiceDTO invoiceDTO) {
        return null;
    }

    public InvoiceDTO getInvoiceById(Long id) {
        return null;
    }
}
