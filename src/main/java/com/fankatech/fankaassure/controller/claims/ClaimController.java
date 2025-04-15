package com.fankatech.fankaassure.controller.claims;

import com.fankatech.fankaassure.domain.claims.service.ClaimService;
import com.fankatech.fankaassure.dto.claims.ClaimDTO;
import com.fankatech.fankaassure.dto.claims.ClaimProcessingDTO;
import com.fankatech.fankaassure.dto.claims.ClaimStatusUpdateDTO;
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
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/claims")
@RequiredArgsConstructor
@Tag(name = "Claims Management", description = "APIs for managing and processing insurance claims")
public class ClaimController {

    private final ClaimService claimService;

    @PostMapping
    @Operation(summary = "Register a new claim")
    public ResponseEntity<ClaimDTO> registerClaim(@Valid @RequestBody ClaimDTO claimDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(claimService.registerClaim(claimDTO));
    }

    @GetMapping
    @Operation(summary = "Get all claims with optional filters")
    public ResponseEntity<Page<ClaimDTO>> getAllClaims(
            @RequestParam(required = false) String claimNumber,
            @RequestParam(required = false) String policyNumber,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
            Pageable pageable) {
        return ResponseEntity.ok(claimService.getAllClaims(claimNumber, policyNumber, status, fromDate, toDate, pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get claim by ID")
    public ResponseEntity<ClaimDTO> getClaimById(@PathVariable Long id) {
        return ResponseEntity.ok(claimService.getClaimById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update claim details")
    public ResponseEntity<ClaimDTO> updateClaim(
            @PathVariable Long id,
            @Valid @RequestBody ClaimDTO claimDTO) {
        return ResponseEntity.ok(claimService.updateClaim(id, claimDTO));
    }

    @PostMapping("/{id}/documents")
    @Operation(summary = "Upload supporting documents for a claim")
    public ResponseEntity<ClaimDTO> uploadClaimDocuments(
            @PathVariable Long id,
            @RequestParam("files") List<MultipartFile> files,
            @RequestParam("documentType") String documentType) {
        return ResponseEntity.ok(claimService.addClaimDocuments(id, files, documentType));
    }

    @GetMapping("/{id}/documents/{documentId}")
    @Operation(summary = "Download claim document")
    public ResponseEntity<byte[]> downloadClaimDocument(
            @PathVariable Long id,
            @PathVariable Long documentId) {
        byte[] document = claimService.getClaimDocument(id, documentId);
        return ResponseEntity
                .ok()
                .header("Content-Type", "application/octet-stream")
                .body(document);
    }

    @PostMapping("/{id}/process")
    @Operation(summary = "Process a claim")
    public ResponseEntity<ClaimProcessingDTO> processClaim(
            @PathVariable Long id,
            @Valid @RequestBody ClaimProcessingDTO processingDTO) {
        return ResponseEntity.ok(claimService.processClaim(id, processingDTO));
    }

    @PostMapping("/{id}/status")
    @Operation(summary = "Update claim status")
    public ResponseEntity<ClaimDTO> updateClaimStatus(
            @PathVariable Long id,
            @Valid @RequestBody ClaimStatusUpdateDTO statusUpdateDTO) {
        return ResponseEntity.ok(claimService.updateClaimStatus(id, statusUpdateDTO));
    }

    @PostMapping("/{id}/approve")
    @Operation(summary = "Approve a claim")
    public ResponseEntity<ClaimDTO> approveClaim(
            @PathVariable Long id,
            @RequestParam(required = false) String notes) {
        return ResponseEntity.ok(claimService.approveClaim(id, notes));
    }

    @PostMapping("/{id}/reject")
    @Operation(summary = "Reject a claim")
    public ResponseEntity<ClaimDTO> rejectClaim(
            @PathVariable Long id,
            @RequestParam String rejectionReason) {
        return ResponseEntity.ok(claimService.rejectClaim(id, rejectionReason));
    }

    @GetMapping("/policy/{policyId}")
    @Operation(summary = "Get claims by policy ID")
    public ResponseEntity<List<ClaimDTO>> getClaimsByPolicyId(@PathVariable Long policyId) {
        return ResponseEntity.ok(claimService.getClaimsByPolicyId(policyId));
    }

    @GetMapping("/customer/{customerId}")
    @Operation(summary = "Get claims by customer ID")
    public ResponseEntity<List<ClaimDTO>> getClaimsByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(claimService.getClaimsByCustomerId(customerId));
    }
}