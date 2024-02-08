package com.awanrpn.invenmanager.model.response;

import java.time.LocalDateTime;

public record UpdateProductResponse(
        String id,
        String name,
        String description,
        Double price,
        Integer quantityInStock,
        LocalDateTime updatedAt
) {
}
