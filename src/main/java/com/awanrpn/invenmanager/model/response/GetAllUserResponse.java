package com.awanrpn.invenmanager.model.response;

import com.awanrpn.invenmanager.model.entity.Product;
import com.awanrpn.invenmanager.model.entity.User;

import java.util.List;

public record GetAllUserResponse(
        String id,
        String name,
        String email,
        User.Role role,
        List<SimpleProductResponse> products
) {
}
