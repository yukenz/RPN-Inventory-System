package com.awanrpn.invenmanager.model.response;

import com.awanrpn.invenmanager.model.entity.Category;

import java.math.BigInteger;
import java.time.LocalDateTime;

public record GetProductResponse(
        String id,
        String name,
        String description,
        BigInteger price,
        Integer quantityInStock,
        Category category,
        String createdByUser,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
