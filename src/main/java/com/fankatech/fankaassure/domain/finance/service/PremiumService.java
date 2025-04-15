package com.fankatech.fankaassure.domain.finance.service;

import com.fankatech.fankaassure.dto.finance.PremiumDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PremiumService {
    public PremiumDTO registerPremium(@Valid PremiumDTO premiumDTO) {
        return null;
    }

    public List<PremiumDTO> getPremiumsByPolicyId(Long policyId) {
        return null;
    }

    public Page<PremiumDTO> getAllDuePremiums(LocalDate fromDate, LocalDate toDate, Pageable pageable) {
        return null;
    }

    public void sendPaymentReminder(Long id) {
        return;
    }
}
