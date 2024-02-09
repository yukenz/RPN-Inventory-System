package com.awanrpn.invenmanager.mapper;

import com.awanrpn.invenmanager.model.dto.order.CreateOrderResponse;
import com.awanrpn.invenmanager.model.dto.order.GetAllOrderResponse;
import com.awanrpn.invenmanager.model.dto.order.GetOrderByIdResponse;
import com.awanrpn.invenmanager.model.dto.order.GetOrderResponse;
import com.awanrpn.invenmanager.model.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {OrderItemMapper.class})
public interface OrderMapper {

    CreateOrderResponse createOrderResponse(Order order);

    @Mapping(source = "order.orderItems", target = "orderItems")
    GetOrderByIdResponse getOrderByIdResponse(Order order);

    @Mapping(source = "order.orderItems", target = "orderItems")
    GetAllOrderResponse getAllOrderResponse(Order order);

    @Mapping(source = "order.orderItems", target = "orderItems_uuid")
    GetOrderResponse getOrderResponse /*Using EntityIdToStringMapper*/(Order order);
}
