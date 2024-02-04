package com.awanrpn.invenmanager.model.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;


    @ManyToOne
    @JoinColumn(
            name = "order_id",
            referencedColumnName = "id"
    )
    private Order order;

    @ManyToOne
    @JoinColumn(
            name = "product_id",
            referencedColumnName = "id"
    )
    private Product product;
    private Integer quantity;
    private Double unitPrice;
    private Date createdAt;
    private Date updatedAt;
}
