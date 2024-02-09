package com.awanrpn.invenmanager.model.dto.product;

import java.time.LocalDateTime;

public record UpdateProductResponse(
        String id,
        String name,
        String description,
        String category,
        Double price,
        Integer quantityInStock,
        LocalDateTime updatedAt
) {
}
