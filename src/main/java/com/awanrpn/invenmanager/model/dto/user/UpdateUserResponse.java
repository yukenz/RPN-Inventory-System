package com.awanrpn.invenmanager.model.dto.user;

import com.awanrpn.invenmanager.model.entity.User;

import java.time.LocalDateTime;

public record UpdateUserResponse(
        String id,
        String name,
        String email,
        User.Role role,
        LocalDateTime updatedAt
) {

}