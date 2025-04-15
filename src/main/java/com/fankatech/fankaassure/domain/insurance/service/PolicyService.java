package com.fankatech.fankaassure.domain.insurance.service;

import com.fankatech.fankaassure.dto.insurance.PolicyDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyService {
    public PolicyDTO createPolicy(@Valid PolicyDTO policyDTO) {
        return null;
    }

    public Page<PolicyDTO> getAllPolicies(String customerName, String policyNumber, String productCode, Pageable pageable) {
        return null;
    }

    public PolicyDTO getPolicyById(Long id) {
        return null;
    }

    public PolicyDTO updatePolicy(Long id, @Valid PolicyDTO policyDTO) {
        return null;
    }

    public List<PolicyDTO> getPoliciesByCustomerId(Long customerId) {
        return null;
    }

    public PolicyDTO renewPolicy(Long id) {
        return null;
    }

    public PolicyDTO cancelPolicy(Long id, String cancellationReason) {
        return null;
    }

    public byte[] generatePolicyDocument(Long id) {
        return null;
    }
}
