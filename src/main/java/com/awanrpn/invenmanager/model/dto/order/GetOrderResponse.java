package com.awanrpn.invenmanager.model.dto.order;

import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

public record GetOrderResponse(
        String id,
        BigInteger totalPrice,
        String customerName,
        String customerEmail,
        String userid,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<String> orderItems_uuid
) {
}
