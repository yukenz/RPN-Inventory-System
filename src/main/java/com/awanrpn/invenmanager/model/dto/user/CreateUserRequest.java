package com.awanrpn.invenmanager.model.dto.user;

import com.awanrpn.invenmanager.model.entity.User;

public record CreateUserRequest(
        String name,
        String email,
        String password,
        User.Role role
) {

}