package com.awanrpn.invenmanager.model.request;

import com.awanrpn.invenmanager.model.entity.User;

public record CreateProductRequest(
        String name,
        String description,
        Double price,
        Integer quantityInStock,
        User user

) {
}
