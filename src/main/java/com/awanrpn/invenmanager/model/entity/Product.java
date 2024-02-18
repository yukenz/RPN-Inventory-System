package com.awanrpn.invenmanager.model.entity;

import com.awanrpn.invenmanager.model.entity.listener.EntityOperationAware;
import com.awanrpn.invenmanager.model.entity.listener.EntityOperationAwareListener;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@EntityListeners({EntityOperationAwareListener.class})

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product implements EntityOperationAware {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @Positive
    private BigInteger price;

    @NotNull
    @PositiveOrZero
    private Integer quantityInStock;


    @ManyToOne
    @JoinColumn(
            name = "category_id",
            referencedColumnName = "id"
    )
//    @NotNull
    private Category category;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id"
    )
    private User user;

//    @OneToMany(mappedBy = "product")
//    private List<OrderItem> orderItems;

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
