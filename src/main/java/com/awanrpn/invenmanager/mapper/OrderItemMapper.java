package com.awanrpn.invenmanager.mapper;

import com.awanrpn.invenmanager.model.dto.orderItem.CreateOrderItemResponse;
import com.awanrpn.invenmanager.model.dto.orderItem.GetOrderItemResponse;
import com.awanrpn.invenmanager.model.dto.orderItem.SimpleOrderItem;
import com.awanrpn.invenmanager.model.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public interface OrderItemMapper {


    default List<String> orderItemListUUID(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(OrderItem::getId).toList();
    }

    @Mappings({
            @Mapping(source = "orderItem.order.id", target = "order_uuid"),
            @Mapping(source = "orderItem.product.id", target = "product_uuid")
    })
    SimpleOrderItem simpleOrderItem(OrderItem orderItem);

    @Mappings({
            @Mapping(source = "orderItem.order.id", target = "order_uuid"),
            @Mapping(source = "orderItem.product.id", target = "product_uuid")
    })
    CreateOrderItemResponse createOrderItemResponse(OrderItem orderItem);

    @Mappings({
            @Mapping(source = "orderItem.order.id", target = "order_uuid"),
            @Mapping(source = "orderItem.product.id", target = "product_uuid")
    })
    GetOrderItemResponse getOrderItemResponse(OrderItem orderItem);
}
