package com.fankatech.fankaassure.domain.member.service;

import com.fankatech.fankaassure.dto.member.BeneficiaryDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeneficiaryService {
    public BeneficiaryDTO addBeneficiary(@Valid BeneficiaryDTO beneficiaryDTO) {
        return null;
    }

    public List<BeneficiaryDTO> getBeneficiariesByCustomerId(Long customerId) {
        return null;
    }

    public BeneficiaryDTO updateBeneficiary(Long id, @Valid BeneficiaryDTO beneficiaryDTO) {
        return null;
    }

    public void removeBeneficiary(Long id) {
        return;
    }
}
