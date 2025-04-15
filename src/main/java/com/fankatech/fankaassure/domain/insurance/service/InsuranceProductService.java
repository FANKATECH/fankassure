package com.fankatech.fankaassure.domain.insurance.service;

import com.fankatech.fankaassure.dto.insurance.InsuranceProductDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InsuranceProductService {
    // Product management endpoints
    public InsuranceProductDTO createProduct(@Valid InsuranceProductDTO productDTO) {
        return null;
    }

    public Page<InsuranceProductDTO> getAllProducts(Pageable pageable) {
        return null;
    }

    public InsuranceProductDTO getProductById(Long id) {
        return null;
    }

    public InsuranceProductDTO updateProduct(Long id, @Valid InsuranceProductDTO productDTO) {
        return null;
    }

    public void deleteProduct(Long id) {
    }
}
