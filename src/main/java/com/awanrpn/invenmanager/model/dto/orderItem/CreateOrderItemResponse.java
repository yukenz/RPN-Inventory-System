package com.awanrpn.invenmanager.model.dto.orderItem;

import java.math.BigInteger;
import java.time.LocalDateTime;

public record CreateOrderItemResponse(
        String id,
        String order_uuid,
        String product_uuid,
        Integer quantity,
        BigInteger unitPrice,
        LocalDateTime createdAt
) {
}
