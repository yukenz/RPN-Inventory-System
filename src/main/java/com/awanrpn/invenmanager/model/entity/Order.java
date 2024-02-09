package com.awanrpn.invenmanager.model.entity;

import com.awanrpn.invenmanager.model.entity.listener.EntityTimeAware;
import com.awanrpn.invenmanager.model.entity.listener.EntityTimeAwareListener;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@EntityListeners({EntityTimeAwareListener.class})

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order implements EntityTimeAware {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private BigInteger totalPrice;
    private String customerName;
    private String customerEmail;
    private String userid;

    @OneToMany(mappedBy = "order", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonManagedReference // Order meManage OrderItem
    private List<OrderItem> orderItems;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Override
    public void onCreate(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public void onUpdate(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
