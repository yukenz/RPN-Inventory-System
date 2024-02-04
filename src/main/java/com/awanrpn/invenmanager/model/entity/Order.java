package com.awanrpn.invenmanager.model.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Double totalPrice;
    private String customerName;
    private String customerEmail;
    private String userid;
    private Date createdAt;
    private Date updatedAt;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;
}
