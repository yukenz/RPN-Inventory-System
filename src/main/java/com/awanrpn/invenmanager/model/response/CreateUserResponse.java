package com.awanrpn.invenmanager.model.response;

import com.awanrpn.invenmanager.model.entity.User;

import java.time.LocalDateTime;

public record CreateUserResponse(
        String id,
        String name,
        String email,
        User.Role role,
        LocalDateTime createdAt

) {

}