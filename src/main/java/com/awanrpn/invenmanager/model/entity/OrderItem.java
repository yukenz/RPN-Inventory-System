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

@Entity
@Table(name = "order_item")
@EntityListeners({EntityOperationAwareListener.class})


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItem implements EntityOperationAware {

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

    /* Primitive */
    private Integer quantity;
    private BigInteger unitPrice;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Override
    public void onCreate(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        calculateUnitPrice();
    }

    @Override
    public void onUpdate(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        calculateUnitPrice();
    }

    private void calculateUnitPrice() {
        BigInteger qty = BigInteger.valueOf(quantity);
        BigInteger unitPrice = product.getPrice().multiply(qty);
        setUnitPrice(unitPrice);
    }

}
