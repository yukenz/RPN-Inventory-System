package com.awanrpn.invenmanager.model.dto.product;

import com.awanrpn.invenmanager.model.entity.User;

public record CreateProductRequest(
        String name,
        String description,
        Double price,
        Integer quantityInStock,
        String user_uuid

) {
}
