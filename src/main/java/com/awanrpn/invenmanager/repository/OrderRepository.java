package com.awanrpn.invenmanager.repository;

import com.awanrpn.invenmanager.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findAllByUserid(String userid);

}
