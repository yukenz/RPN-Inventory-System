package com.awanrpn.invenmanager.model.dto.user;

import com.awanrpn.invenmanager.model.entity.User;

public record GetAllUserResponse(
        String id,
        String name,
        String email,
        User.Role role
) {
}
