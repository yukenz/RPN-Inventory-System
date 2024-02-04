package com.awanrpn.invenmanager.model.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String description;
    private Double price;
    private Integer quantityInStock;
    private Date createdAt;
    private Date updatedAt;


    @ManyToOne
    @JoinColumn(
            name = "category_id",
            referencedColumnName = "id"
    )
    private Category category;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id"
    )
    private User user;

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;


}
