package com.awanrpn.invenmanager.model.dto.orderItem;

public record OrderItemRequest(
        String orderUUID,
        String productUUID,
        Integer quantity
) {
}
