package com.awanrpn.invenmanager.mapper;

import com.awanrpn.invenmanager.model.entity.Category;
import com.awanrpn.invenmanager.model.entity.Product;
import com.awanrpn.invenmanager.model.entity.User;
import com.awanrpn.invenmanager.model.request.CategoryRequest;
import com.awanrpn.invenmanager.model.request.CategoryResponse;
import com.awanrpn.invenmanager.model.request.CreateProductRequest;
import com.awanrpn.invenmanager.model.request.CreateUserRequest;
import com.awanrpn.invenmanager.model.response.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {ProductMapper.class})
public interface ObjAutoMapper {

    /* User Module */
    User createUserFromRequest(CreateUserRequest createUserRequest);

    CreateUserResponse createUserResponse(User user);

    GetUserByIdResponse getUserByIdResponse(User user);

    UpdateUserResponse updateUserResponse(User user);

    GetAllUserResponse getAllUserResponse(User user);

    /* Product Module */
    Product createProductFromRequest(CreateProductRequest createProductRequest);

    CreateProductResponse createProductResponse(Product product);

    UpdateProductResponse updateProductResponse(Product product);

    @Mapping(source = "product.user.id", target = "createdByUser")
    GetProductResponse getProductResponse(Product product);

    /* Category Module */

    Category createCategoryFromRequest(CategoryRequest categoryRequest);

    CategoryResponse categoryResponse(Category category);

}
