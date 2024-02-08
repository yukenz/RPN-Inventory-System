package com.awanrpn.invenmanager.model.request;

import com.awanrpn.invenmanager.model.entity.Category;
import com.awanrpn.invenmanager.model.entity.User;

import java.math.BigInteger;

public record UpdateProductRequest(
        String name,
        String description,
        BigInteger price,
        Integer quantityInStock,
        String category_uuid,
        String user_uuid
) {
}
