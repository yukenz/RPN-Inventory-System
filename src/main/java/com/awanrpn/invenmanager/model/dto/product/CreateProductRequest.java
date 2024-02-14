package com.awanrpn.invenmanager.model.dto.product;

public record CreateProductRequest(
        String name,
        String description,
        Double price,
        Integer quantityInStock
) {
}
