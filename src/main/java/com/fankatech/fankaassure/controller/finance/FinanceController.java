package com.fankatech.fankaassure.controller.finance;

import com.fankatech.fankaassure.domain.finance.service.*;
import com.fankatech.fankaassure.dto.finance.PremiumDTO;
import com.fankatech.fankaassure.dto.finance.PaymentDTO;
import com.fankatech.fankaassure.dto.finance.InvoiceDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/finance")
@RequiredArgsConstructor
@Tag(name = "Financial Management", description = "APIs for managing premiums, payments, invoices and investments")
public class FinanceController {

    private final PremiumService premiumService;
    private final PaymentService paymentService;
    private final InvoiceService invoiceService;
    private final ReinsuranceService reinsuranceService;
    private final InvestmentService investmentService;

    // Premium management endpoints
    @PostMapping("/premiums")
    @Operation(summary = "Register a premium")
    public ResponseEntity<PremiumDTO> registerPremium(@Valid @RequestBody PremiumDTO premiumDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(premiumService.registerPremium(premiumDTO));
    }

    @GetMapping("/premiums/policy/{policyId}")
    @Operation(summary = "Get premiums by policy ID")
    public ResponseEntity<List<PremiumDTO>> getPremiumsByPolicyId(@PathVariable Long policyId) {
        return ResponseEntity.ok(premiumService.getPremiumsByPolicyId(policyId));
    }

    @GetMapping("/premiums/due")
    @Operation(summary = "Get all due premiums")
    public ResponseEntity<Page<PremiumDTO>> getAllDuePremiums(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
            Pageable pageable) {
        return ResponseEntity.ok(premiumService.getAllDuePremiums(fromDate, toDate, pageable));
    }

    @PostMapping("/premiums/{id}/reminder")
    @Operation(summary = "Send payment reminder")
    public ResponseEntity<Void> sendPaymentReminder(@PathVariable Long id) {
        premiumService.sendPaymentReminder(id);
        return ResponseEntity.ok().build();
    }

    // Payment management endpoints
    @PostMapping("/payments")
    @Operation(summary = "Record a payment")
    public ResponseEntity<PaymentDTO> recordPayment(@Valid @RequestBody PaymentDTO paymentDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.recordPayment(paymentDTO));
    }

    @GetMapping("/payments")
    @Operation(summary = "Get all payments")
    public ResponseEntity<Page<PaymentDTO>> getAllPayments(
            @RequestParam(required = false) String paymentReference,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
            Pageable pageable) {
        return ResponseEntity.ok(paymentService.getAllPayments(paymentReference, fromDate, toDate, pageable));
    }

    @GetMapping("/payments/customer/{customerId}")
    @Operation(summary = "Get payments by customer ID")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(paymentService.getPaymentsByCustomerId(customerId));
    }

    // Invoice management endpoints
    @PostMapping("/invoices")
    @Operation(summary = "Generate an invoice")
    public ResponseEntity<InvoiceDTO> generateInvoice(@Valid @RequestBody InvoiceDTO invoiceDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceService.generateInvoice(invoiceDTO));
    }

    @GetMapping("/invoices/{id}")
    @Operation(summary = "Get invoice by ID")
    public ResponseEntity<InvoiceDTO> getInvoiceById(@PathVariable Long id) {
        return ResponseEntity.ok(invoiceService.getInvoiceById(id));
    }
}