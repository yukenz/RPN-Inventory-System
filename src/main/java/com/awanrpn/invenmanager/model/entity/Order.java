package com.awanrpn.invenmanager.model.entity;

import com.awanrpn.invenmanager.model.entity.listener.EntityOperationAware;
import com.awanrpn.invenmanager.model.entity.listener.EntityOperationAwareListener;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
@EntityListeners({EntityOperationAwareListener.class})

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order implements EntityOperationAware {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private BigInteger totalPrice;
    private String customerName;
    private String customerEmail;
    private String userid;

    @OneToMany(mappedBy = "order", orphanRemoval = true, cascade = CascadeType.ALL)
    //Order di remove, orderItems di clear
    private Set<OrderItem> orderItems;

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

    public void refreshTotalPrice() {
        BigInteger totalPrice = getOrderItems().parallelStream()
                .reduce(
                        BigInteger.ZERO,
                        (prevValue, orderItem) -> prevValue.add(orderItem.getUnitPrice()),
                        BigInteger::add);

        setTotalPrice(totalPrice);
    }
}
