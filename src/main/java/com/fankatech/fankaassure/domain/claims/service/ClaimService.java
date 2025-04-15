package com.fankatech.fankaassure.domain.claims.service;

import com.fankatech.fankaassure.dto.claims.ClaimDTO;
import com.fankatech.fankaassure.dto.claims.ClaimProcessingDTO;
import com.fankatech.fankaassure.dto.claims.ClaimStatusUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Service
public class ClaimService {
    public ClaimDTO registerClaim(@Valid ClaimDTO claimDTO) {
        return null;
    }

    public Page<ClaimDTO> getAllClaims(String claimNumber, String policyNumber, String status, LocalDate fromDate, LocalDate toDate, Pageable pageable) {
        return null;
    }

    public ClaimDTO getClaimById(Long id) {
        return null;
    }

    public ClaimDTO updateClaim(Long id, @Valid ClaimDTO claimDTO) {
        return null;
    }

    public ClaimDTO addClaimDocuments(Long id, List<MultipartFile> files, String documentType) {
        return null;
    }

    public byte[] getClaimDocument(Long id, Long documentId) {
        return null;
    }

    public ClaimProcessingDTO processClaim(Long id, @Valid ClaimProcessingDTO processingDTO) {
        return null;
    }

    public ClaimDTO updateClaimStatus(Long id, @Valid ClaimStatusUpdateDTO statusUpdateDTO) {
        return null;
    }

    public ClaimDTO approveClaim(Long id, String notes) {
        return null;
    }

    public ClaimDTO rejectClaim(Long id, String rejectionReason) {
        return null;
    }

    public List<ClaimDTO> getClaimsByPolicyId(Long policyId) {
        return null;
    }

    public List<ClaimDTO> getClaimsByCustomerId(Long customerId) {
        return null;
    }
}
