package com.awanrpn.invenmanager.model.dto.orderItem;

import java.math.BigInteger;

public record SimpleOrderItem(
        String id,
        String order_uuid,
        String product_uuid,
        Integer quantity,
        BigInteger unitPrice
) {
}
