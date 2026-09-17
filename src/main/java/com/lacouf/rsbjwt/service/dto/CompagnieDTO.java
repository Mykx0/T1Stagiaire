package com.lacouf.rsbjwt.service.dto;

import com.lacouf.rsbjwt.model.Compagnie;

public record CompagnieDTO(Long id, String compagniName, String city) {
    public static CompagnieDTO of(Compagnie compagnie) {
        return new CompagnieDTO(compagnie.getId(), compagnie.getCompagniName(), compagnie.getCity());
    }
}