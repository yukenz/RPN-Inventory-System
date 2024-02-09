package com.awanrpn.invenmanager.mapper;

import com.awanrpn.invenmanager.model.dto.user.*;
import com.awanrpn.invenmanager.model.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper{


    User createUserFromRequest(CreateUserRequest createUserRequest);

    CreateUserResponse createUserResponse(User user);

    GetUserByIdResponse getUserByIdResponse(User user);

    UpdateUserResponse updateUserResponse(User user);

    GetAllUserResponse getAllUserResponse(User user);
}
