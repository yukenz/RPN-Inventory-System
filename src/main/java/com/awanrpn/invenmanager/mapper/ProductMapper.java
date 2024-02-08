package com.awanrpn.invenmanager.mapper;

import com.awanrpn.invenmanager.model.entity.Product;
import com.awanrpn.invenmanager.model.response.SimpleProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    SimpleProductResponse simpleProductResponse(Product product);

}
