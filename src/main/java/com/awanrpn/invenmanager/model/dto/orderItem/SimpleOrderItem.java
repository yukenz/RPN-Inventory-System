package com.awanrpn.invenmanager.model.dto.orderItem;

import java.math.BigInteger;

public record SimpleOrderItem(
        String id,
        String product_uuid,
        Integer quantity,
        BigInteger unitPrice
) {
}
