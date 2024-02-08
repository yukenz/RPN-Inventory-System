package com.awanrpn.invenmanager.model.response;

import java.time.LocalDateTime;

public record CreateProductResponse(
        String id,
        String name,
        String description,
        Double price,
        Integer quantityInStock,
        LocalDateTime createdAt
) {
}