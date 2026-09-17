package com.lacouf.rsbjwt.service.dto;

import com.lacouf.rsbjwt.model.Company;

public record CompanyDTO(Long id, String compagniName, String city) {
    public static CompanyDTO of(Company company) {
        return new CompanyDTO(company.getId(), company.getCompagniName(), company.getCity());
    }
}