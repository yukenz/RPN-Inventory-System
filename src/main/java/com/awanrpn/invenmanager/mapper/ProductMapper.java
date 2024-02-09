package com.awanrpn.invenmanager.mapper;

import com.awanrpn.invenmanager.model.dto.product.CreateProductRequest;
import com.awanrpn.invenmanager.model.dto.product.CreateProductResponse;
import com.awanrpn.invenmanager.model.dto.product.GetProductResponse;
import com.awanrpn.invenmanager.model.dto.product.UpdateProductResponse;
import com.awanrpn.invenmanager.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface ProductMapper {

    /* Product Module */
    Product createProductFromRequest(CreateProductRequest createProductRequest);

    CreateProductResponse createProductResponse(Product product);

    @Mapping(source = "product.category.name", target = "category")
    UpdateProductResponse updateProductResponse(Product product);

    @Mappings({
            @Mapping(source = "product.user.id", target = "createdByUser"),
            @Mapping(source = "product.category.name", target = "category")
    })
    GetProductResponse getProductResponse(Product product);


}
