package com.awanrpn.invenmanager.model.dto.order;

import com.awanrpn.invenmanager.model.dto.orderItem.SimpleOrderItem;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

public record GetOrderByIdResponse(
        String id,
        BigInteger totalPrice,
        String customerName,
        String customerEmail,
        String userid,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<SimpleOrderItem> orderItems
) {
}
