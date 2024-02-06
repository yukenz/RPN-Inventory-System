package com.awanrpn.invenmanager.model.request;

import com.awanrpn.invenmanager.model.entity.User;

public record UpdateUserRequest(
        String name,
        String email,
        String password,
        User.Role role
) {
}
