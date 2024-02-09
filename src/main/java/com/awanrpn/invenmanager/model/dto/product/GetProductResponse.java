package com.awanrpn.invenmanager.model.dto.product;

import com.awanrpn.invenmanager.model.dto.category.SimpleCategory;

import java.math.BigInteger;
import java.time.LocalDateTime;

public record GetProductResponse(
        String id,
        String name,
        String description,
        BigInteger price,
        Integer quantityInStock,
        String category,
        String createdByUser,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
