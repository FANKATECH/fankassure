package com.fankatech.fankaassure.controller.pension;

import com.fankatech.fankaassure.domain.pension.service.ContributionService;
import com.fankatech.fankaassure.domain.pension.service.PensionMemberService;
import com.fankatech.fankaassure.domain.pension.service.PensionPlanService;
import com.fankatech.fankaassure.dto.pension.PensionPlanDTO;
import com.fankatech.fankaassure.dto.pension.PensionMemberDTO;
import com.fankatech.fankaassure.dto.pension.ContributionDTO;
import com.fankatech.fankaassure.dto.pension.BenefitCalculationDTO;
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
@RequestMapping("/pension")
@RequiredArgsConstructor
@Tag(name = "Pension Management", description = "APIs for managing pension plans, members and contributions")
public class PensionController {

    private final PensionPlanService pensionPlanService;
    private final PensionMemberService pensionMemberService;
    private final ContributionService contributionService;

    // Pension Plan endpoints
    @PostMapping("/plans")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create a new pension plan")
    public ResponseEntity<PensionPlanDTO> createPensionPlan(@Valid @RequestBody PensionPlanDTO planDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pensionPlanService.createPensionPlan(planDTO));
    }

    @GetMapping("/plans")
    @Operation(summary = "Get all pension plans")
    public ResponseEntity<Page<PensionPlanDTO>> getAllPensionPlans(Pageable pageable) {
        return ResponseEntity.ok(pensionPlanService.getAllPensionPlans(pageable));
    }

    @GetMapping("/plans/{id}")
    @Operation(summary = "Get pension plan by ID")
    public ResponseEntity<PensionPlanDTO> getPensionPlanById(@PathVariable Long id) {
        return ResponseEntity.ok(pensionPlanService.getPensionPlanById(id));
    }

    @PutMapping("/plans/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update a pension plan")
    public ResponseEntity<PensionPlanDTO> updatePensionPlan(
            @PathVariable Long id,
            @Valid @RequestBody PensionPlanDTO planDTO) {
        return ResponseEntity.ok(pensionPlanService.updatePensionPlan(id, planDTO));
    }

    // Pension Member endpoints
    @PostMapping("/members")
    @Operation(summary = "Register a new pension member")
    public ResponseEntity<PensionMemberDTO> registerMember(@Valid @RequestBody PensionMemberDTO memberDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pensionMemberService.registerMember(memberDTO));
    }

    @GetMapping("/members")
    @Operation(summary = "Get all pension members")
    public ResponseEntity<Page<PensionMemberDTO>> getAllMembers(
            @RequestParam(required = false) String memberNumber,
            @RequestParam(required = false) String name,
            Pageable pageable) {
        return ResponseEntity.ok(pensionMemberService.getAllMembers(memberNumber, name, pageable));
    }

    @GetMapping("/members/{id}")
    @Operation(summary = "Get pension member by ID")
    public ResponseEntity<PensionMemberDTO> getMemberById(@PathVariable Long id) {
        return ResponseEntity.ok(pensionMemberService.getMemberById(id));
    }

    @PutMapping("/members/{id}")
    @Operation(summary = "Update pension member information")
    public ResponseEntity<PensionMemberDTO> updateMember(
            @PathVariable Long id,
            @Valid @RequestBody PensionMemberDTO memberDTO) {
        return ResponseEntity.ok(pensionMemberService.updateMember(id, memberDTO));
    }

    @GetMapping("/members/{id}/statement")
    @Operation(summary = "Generate member statement")
    public ResponseEntity<byte[]> generateMemberStatement(@PathVariable Long id) {
        byte[] statement = pensionMemberService.generateMemberStatement(id);
        return ResponseEntity
                .ok()
                .header("Content-Type", "application/pdf")
                .header("Content-Disposition", "attachment; filename=statement_" + id + ".pdf")
                .body(statement);
    }

    // Contribution endpoints
    @PostMapping("/contributions")
    @Operation(summary = "Record a new contribution")
    public ResponseEntity<ContributionDTO> recordContribution(@Valid @RequestBody ContributionDTO contributionDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contributionService.recordContribution(contributionDTO));
    }

    @GetMapping("/contributions/member/{memberId}")
    @Operation(summary = "Get contributions by member ID")
    public ResponseEntity<List<ContributionDTO>> getContributionsByMemberId(@PathVariable Long memberId) {
        return ResponseEntity.ok(contributionService.getContributionsByMemberId(memberId));
    }

    @PostMapping("/contributions/batch")
    @Operation(summary = "Record batch contributions")
    public ResponseEntity<List<ContributionDTO>> recordBatchContributions(
            @Valid @RequestBody List<ContributionDTO> contributionDTOs) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contributionService.recordBatchContributions(contributionDTOs));
    }

    // Benefit calculation endpoints
    @PostMapping("/benefits/calculate")
    @Operation(summary = "Calculate retirement benefits")
    public ResponseEntity<BenefitCalculationDTO> calculateBenefits(@Valid @RequestBody BenefitCalculationDTO calculationDTO) {
        return ResponseEntity.ok(pensionMemberService.calculateBenefits(calculationDTO));
    }

    @GetMapping("/members/{id}/projection")
    @Operation(summary = "Get retirement benefits projection")
    public ResponseEntity<BenefitCalculationDTO> getBenefitsProjection(
            @PathVariable Long id,
            @RequestParam(required = false, defaultValue = "65") int retirementAge) {
        return ResponseEntity.ok(pensionMemberService.getBenefitsProjection(id, retirementAge));
    }
}