package com.fankatech.fankaassure.domain.pension.service;

import com.fankatech.fankaassure.dto.pension.PensionPlanDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PensionPlanService {
    public PensionPlanDTO createPensionPlan(@Valid PensionPlanDTO planDTO) {
        return null;
    }

    public Page<PensionPlanDTO> getAllPensionPlans(Pageable pageable) {
        return null;
    }

    public PensionPlanDTO getPensionPlanById(Long id) {
        return null;
    }

    public PensionPlanDTO updatePensionPlan(Long id, @Valid PensionPlanDTO planDTO) {
        return null;
    }
}
