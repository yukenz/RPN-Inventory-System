package com.awanrpn.invenmanager.model.dto.order;

import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDateTime;

public record CreateOrderResponse(
        String id,
        BigInteger totalPrice,
        String customerName,
        String customerEmail,
        String userid,
        LocalDateTime createdAt
) {
    
}
