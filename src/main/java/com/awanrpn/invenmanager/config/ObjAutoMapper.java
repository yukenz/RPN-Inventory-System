package com.awanrpn.invenmanager.config;

import com.awanrpn.invenmanager.model.entity.Product;
import com.awanrpn.invenmanager.model.entity.User;
import com.awanrpn.invenmanager.model.request.CreateProductRequest;
import com.awanrpn.invenmanager.model.request.CreateUserRequest;
import com.awanrpn.invenmanager.model.response.CreateProductResponse;
import com.awanrpn.invenmanager.model.response.CreateUserResponse;
import com.awanrpn.invenmanager.model.response.GetUserByIdResponse;
import com.awanrpn.invenmanager.model.response.UpdateUserResponse;
import org.mapstruct.Mapper;

@Mapper
public interface ObjAutoMapper {

    /* User Module */
    User createUserFromRequest(CreateUserRequest createUserRequest);

    CreateUserResponse createUserResponse(User user);

    GetUserByIdResponse getUserByIdResponse(User user);

    UpdateUserResponse updateUserResponse(User user);

    /* Product Module */

    Product createProductFromRequest(CreateProductRequest createProductRequest);

    CreateProductResponse createProductResponse(Product product);

}
