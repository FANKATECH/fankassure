package com.fankatech.fankaassure.domain.pension.service;

import com.fankatech.fankaassure.dto.pension.ContributionDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContributionService {
    public ContributionDTO recordContribution(@Valid ContributionDTO contributionDTO) {
        return null;
    }

    public List<ContributionDTO> getContributionsByMemberId(Long memberId) {
        return null;
    }

    public List<ContributionDTO> recordBatchContributions(@Valid List<ContributionDTO> contributionDTOs) {
        return null;
    }
}
