package com.awanrpn.invenmanager.model.response;

import com.awanrpn.invenmanager.model.entity.User;

public record CreateUserResponse(
        String name,
        String email,
        User.Role role
) {

}