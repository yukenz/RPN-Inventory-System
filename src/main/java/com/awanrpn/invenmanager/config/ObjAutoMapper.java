package com.awanrpn.invenmanager.config;

import com.awanrpn.invenmanager.model.entity.User;
import com.awanrpn.invenmanager.model.response.CreateUserResponse;
import org.mapstruct.Mapper;

@Mapper()
public interface ObjAutoMapper {
    CreateUserResponse createUserResponse(User user);
}
