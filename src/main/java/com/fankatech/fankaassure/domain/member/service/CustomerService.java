package com.fankatech.fankaassure.domain.member.service;

import com.fankatech.fankaassure.dto.member.CustomerDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    public CustomerDTO registerCustomer(@Valid CustomerDTO customerDTO) {
        return null;
    }

    public Page<CustomerDTO> getAllCustomers(String name, String idNumber, String email, String phone, Pageable pageable) {
        return null;
    }

    public CustomerDTO getCustomerById(Long id) {
        return null;
    }

    public CustomerDTO updateCustomer(Long id, @Valid CustomerDTO customerDTO) {
        return null;
    }

    public Page<CustomerDTO> searchCustomers(String query, Pageable pageable) {
        return null;
    }
}
