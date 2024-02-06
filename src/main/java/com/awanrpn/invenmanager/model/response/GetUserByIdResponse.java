package com.awanrpn.invenmanager.model.response;

import com.awanrpn.invenmanager.model.entity.Token;
import com.awanrpn.invenmanager.model.entity.User;

import java.util.List;

public record GetUserByIdResponse(
        String id,
        String name,
        String email,
        String password,
        User.Role role,
        List<Token> tokens

) {
}
