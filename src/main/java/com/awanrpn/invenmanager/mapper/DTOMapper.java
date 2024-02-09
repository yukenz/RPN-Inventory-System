package com.awanrpn.invenmanager.mapper;

import org.mapstruct.factory.Mappers;

public final class DTOMapper {

    public final static UserMapper USER_MAPPER;
    public final static CategoryMapper CATEGORY_MAPPER;
    public final static OrderMapper ORDER_MAPPER;
    public final static OrderItemMapper ORDER_ITEM_MAPPER;
    public final static ProductMapper PRODUCT_MAPPER;

    static {
        USER_MAPPER = Mappers.getMapper(UserMapper.class);
        CATEGORY_MAPPER = Mappers.getMapper(CategoryMapper.class);
        ORDER_MAPPER = Mappers.getMapper(OrderMapper.class);
        ORDER_ITEM_MAPPER = Mappers.getMapper(OrderItemMapper.class);
        PRODUCT_MAPPER = Mappers.getMapper(ProductMapper.class);
    }

}
