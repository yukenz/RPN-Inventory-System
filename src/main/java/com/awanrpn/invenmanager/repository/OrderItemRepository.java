package com.awanrpn.invenmanager.repository;

import com.awanrpn.invenmanager.model.entity.Order;
import com.awanrpn.invenmanager.model.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, String> {

    List<OrderItem> findAllByOrder(Order order);
}
