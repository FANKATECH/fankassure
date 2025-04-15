package com.fankatech.fankaassure.controller.member;

import com.fankatech.fankaassure.domain.member.service.BeneficiaryService;
import com.fankatech.fankaassure.domain.member.service.CustomerService;
import com.fankatech.fankaassure.domain.member.service.GroupService;
import com.fankatech.fankaassure.dto.member.CustomerDTO;
import com.fankatech.fankaassure.dto.member.BeneficiaryDTO;
import com.fankatech.fankaassure.dto.member.GroupDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Tag(name = "Member Management", description = "APIs for managing customers, groups and beneficiaries")
public class MemberController {

    private final CustomerService customerService;
    private final BeneficiaryService beneficiaryService;
    private final GroupService groupService;

    // Customer management endpoints
    @PostMapping("/customers")
    @Operation(summary = "Register a new customer")
    public ResponseEntity<CustomerDTO> registerCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.registerCustomer(customerDTO));
    }

    @GetMapping("/customers")
    @Operation(summary = "Get all customers")
    public ResponseEntity<Page<CustomerDTO>> getAllCustomers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String idNumber,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone,
            Pageable pageable) {
        return ResponseEntity.ok(customerService.getAllCustomers(name, idNumber, email, phone, pageable));
    }

    @GetMapping("/customers/{id}")
    @Operation(summary = "Get customer by ID")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PutMapping("/customers/{id}")
    @Operation(summary = "Update customer information")
    public ResponseEntity<CustomerDTO> updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerService.updateCustomer(id, customerDTO));
    }

    @GetMapping("/customers/search")
    @Operation(summary = "Search customers")
    public ResponseEntity<Page<CustomerDTO>> searchCustomers(
            @RequestParam String query,
            Pageable pageable) {
        return ResponseEntity.ok(customerService.searchCustomers(query, pageable));
    }

    // Group management endpoints
    @PostMapping("/groups")
    @Operation(summary = "Create a new group")
    public ResponseEntity<GroupDTO> createGroup(@Valid @RequestBody GroupDTO groupDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(groupService.createGroup(groupDTO));
    }

    @GetMapping("/groups")
    @Operation(summary = "Get all groups")
    public ResponseEntity<Page<GroupDTO>> getAllGroups(
            @RequestParam(required = false) String name,
            Pageable pageable) {
        return ResponseEntity.ok(groupService.getAllGroups(name, pageable));
    }

    @GetMapping("/groups/{id}")
    @Operation(summary = "Get group by ID")
    public ResponseEntity<GroupDTO> getGroupById(@PathVariable Long id) {
        return ResponseEntity.ok(groupService.getGroupById(id));
    }

    @PutMapping("/groups/{id}")
    @Operation(summary = "Update group information")
    public ResponseEntity<GroupDTO> updateGroup(
            @PathVariable Long id,
            @Valid @RequestBody GroupDTO groupDTO) {
        return ResponseEntity.ok(groupService.updateGroup(id, groupDTO));
    }

    @PostMapping("/groups/{id}/members")
    @Operation(summary = "Add members to a group")
    public ResponseEntity<GroupDTO> addMembersToGroup(
            @PathVariable Long id,
            @RequestBody List<Long> customerIds) {
        return ResponseEntity.ok(groupService.addMembersToGroup(id, customerIds));
    }

    @DeleteMapping("/groups/{groupId}/members/{customerId}")
    @Operation(summary = "Remove a member from a group")
    public ResponseEntity<Void> removeMemberFromGroup(
            @PathVariable Long groupId,
            @PathVariable Long customerId) {
        groupService.removeMemberFromGroup(groupId, customerId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/groups/import")
    @Operation(summary = "Import group members from Excel")
    public ResponseEntity<GroupDTO> importGroupMembers(
            @RequestParam Long groupId,
            @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(groupService.importGroupMembers(groupId, file));
    }

    // Beneficiary management endpoints
    @PostMapping("/beneficiaries")
    @Operation(summary = "Add a beneficiary")
    public ResponseEntity<BeneficiaryDTO> addBeneficiary(@Valid @RequestBody BeneficiaryDTO beneficiaryDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(beneficiaryService.addBeneficiary(beneficiaryDTO));
    }

    @GetMapping("/beneficiaries/customer/{customerId}")
    @Operation(summary = "Get beneficiaries by customer ID")
    public ResponseEntity<List<BeneficiaryDTO>> getBeneficiariesByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(beneficiaryService.getBeneficiariesByCustomerId(customerId));
    }

    @PutMapping("/beneficiaries/{id}")
    @Operation(summary = "Update beneficiary information")
    public ResponseEntity<BeneficiaryDTO> updateBeneficiary(
            @PathVariable Long id,
            @Valid @RequestBody BeneficiaryDTO beneficiaryDTO) {
        return ResponseEntity.ok(beneficiaryService.updateBeneficiary(id, beneficiaryDTO));
    }

    @DeleteMapping("/beneficiaries/{id}")
    @Operation(summary = "Remove a beneficiary")
    public ResponseEntity<Void> removeBeneficiary(@PathVariable Long id) {
        beneficiaryService.removeBeneficiary(id);
        return ResponseEntity.noContent().build();
    }
}