package com.awanrpn.invenmanager.mapper;

import com.awanrpn.invenmanager.model.dto.orderItem.CreateOrderItemResponse;
import com.awanrpn.invenmanager.model.dto.orderItem.GetOrderItemResponse;
import com.awanrpn.invenmanager.model.dto.orderItem.SimpleOrderItem;
import com.awanrpn.invenmanager.model.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Mapper
public interface OrderItemMapper {


    default List<String> orderItemListUUID(Collection<OrderItem> orderItems) {
        return orderItems.stream()
                .map(OrderItem::getId).toList();
    }

    @Mapping(source = "orderItem.product.id", target = "product_uuid")
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
