package com.fankatech.fankaassure.domain.finance.service;

import com.fankatech.fankaassure.dto.finance.PaymentDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PaymentService {
    public PaymentDTO recordPayment(@Valid PaymentDTO paymentDTO) {
        return null;
    }

    public Page<PaymentDTO> getAllPayments(String paymentReference, LocalDate fromDate, LocalDate toDate, Pageable pageable) {
        return null;
    }

    public List<PaymentDTO> getPaymentsByCustomerId(Long customerId) {
        return null;
    }
}
