package com.awanrpn.invenmanager.config;

import com.awanrpn.invenmanager.model.entity.User;
import com.awanrpn.invenmanager.model.response.CreateUserResponse;
import com.awanrpn.invenmanager.model.response.GetUserByIdResponse;
import com.awanrpn.invenmanager.model.response.UpdateUserResponse;
import org.mapstruct.Mapper;

@Mapper
public interface ObjAutoMapper {
    CreateUserResponse createUserResponse(User user);
    GetUserByIdResponse getUserByIdResponse(User user);
    UpdateUserResponse updateUserResponse(User user);
}
