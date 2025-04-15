package com.fankatech.fankaassure.domain.pension.service;

import com.fankatech.fankaassure.dto.pension.BenefitCalculationDTO;
import com.fankatech.fankaassure.dto.pension.PensionMemberDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PensionMemberService {
    public PensionMemberDTO registerMember(@Valid PensionMemberDTO memberDTO) {
        return null;
    }

    public Page<PensionMemberDTO> getAllMembers(String memberNumber, String name, Pageable pageable) {
        return null;
    }

    public PensionMemberDTO getMemberById(Long id) {
        return null;
    }

    public PensionMemberDTO updateMember(Long id, @Valid PensionMemberDTO memberDTO) {
        return null;
    }

    public byte[] generateMemberStatement(Long id) {
        return new byte[0];
    }

    public BenefitCalculationDTO calculateBenefits(@Valid BenefitCalculationDTO calculationDTO) {
        return null;
    }

    public BenefitCalculationDTO getBenefitsProjection(Long id, int retirementAge) {
        return null;
    }
}
