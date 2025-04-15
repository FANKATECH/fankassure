package com.fankatech.fankaassure.controller.insurance;

import com.fankatech.fankaassure.domain.insurance.service.InsuranceProductService;
import com.fankatech.fankaassure.domain.insurance.service.PolicyService;
import com.fankatech.fankaassure.dto.insurance.InsuranceProductDTO;
import com.fankatech.fankaassure.dto.insurance.PolicyDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/insurance")
@RequiredArgsConstructor
@Tag(name = "Insurance Management", description = "APIs for managing insurance products and policies")
public class InsuranceController {

    private final InsuranceProductService insuranceProductService;
    private final PolicyService policyService;

    // Product management endpoints
    @PostMapping("/products")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create a new insurance product")
    public ResponseEntity<InsuranceProductDTO> createProduct(@Valid @RequestBody InsuranceProductDTO productDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(insuranceProductService.createProduct(productDTO));
    }

    @GetMapping("/products")
    @Operation(summary = "Get all insurance products")
    public ResponseEntity<Page<InsuranceProductDTO>> getAllProducts(Pageable pageable) {
        return ResponseEntity.ok(insuranceProductService.getAllProducts(pageable));
    }

    @GetMapping("/products/{id}")
    @Operation(summary = "Get insurance product by ID")
    public ResponseEntity<InsuranceProductDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(insuranceProductService.getProductById(id));
    }

    @PutMapping("/products/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update an insurance product")
    public ResponseEntity<InsuranceProductDTO> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody InsuranceProductDTO productDTO) {
        return ResponseEntity.ok(insuranceProductService.updateProduct(id, productDTO));
    }

    @DeleteMapping("/products/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete an insurance product")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        insuranceProductService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // Policy management endpoints
    @PostMapping("/policies")
    @Operation(summary = "Create a new insurance policy")
    public ResponseEntity<PolicyDTO> createPolicy(@Valid @RequestBody PolicyDTO policyDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(policyService.createPolicy(policyDTO));
    }

    @GetMapping("/policies")
    @Operation(summary = "Get all insurance policies")
    public ResponseEntity<Page<PolicyDTO>> getAllPolicies(
            @RequestParam(required = false) String customerName,
            @RequestParam(required = false) String policyNumber,
            @RequestParam(required = false) String productCode,
            Pageable pageable) {
        return ResponseEntity.ok(policyService.getAllPolicies(customerName, policyNumber, productCode, pageable));
    }

    @GetMapping("/policies/{id}")
    @Operation(summary = "Get insurance policy by ID")
    public ResponseEntity<PolicyDTO> getPolicyById(@PathVariable Long id) {
        return ResponseEntity.ok(policyService.getPolicyById(id));
    }

    @PutMapping("/policies/{id}")
    @Operation(summary = "Update an insurance policy")
    public ResponseEntity<PolicyDTO> updatePolicy(
            @PathVariable Long id,
            @Valid @RequestBody PolicyDTO policyDTO) {
        return ResponseEntity.ok(policyService.updatePolicy(id, policyDTO));
    }

    @GetMapping("/policies/customer/{customerId}")
    @Operation(summary = "Get policies by customer ID")
    public ResponseEntity<List<PolicyDTO>> getPoliciesByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(policyService.getPoliciesByCustomerId(customerId));
    }

    @PostMapping("/policies/{id}/renew")
    @Operation(summary = "Renew an insurance policy")
    public ResponseEntity<PolicyDTO> renewPolicy(@PathVariable Long id) {
        return ResponseEntity.ok(policyService.renewPolicy(id));
    }

    @PostMapping("/policies/{id}/cancel")
    @Operation(summary = "Cancel an insurance policy")
    public ResponseEntity<PolicyDTO> cancelPolicy(
            @PathVariable Long id,
            @RequestParam String cancellationReason) {
        return ResponseEntity.ok(policyService.cancelPolicy(id, cancellationReason));
    }

    @GetMapping("/policies/{id}/document")
    @Operation(summary = "Generate policy document")
    public ResponseEntity<byte[]> generatePolicyDocument(@PathVariable Long id) {
        byte[] document = policyService.generatePolicyDocument(id);
        return ResponseEntity
                .ok()
                .header("Content-Type", "application/pdf")
                .header("Content-Disposition", "attachment; filename=policy_" + id + ".pdf")
                .body(document);
    }
}