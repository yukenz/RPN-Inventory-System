package com.awanrpn.invenmanager.model.entity;

import com.awanrpn.invenmanager.model.entity.listener.EntityOperationAware;
import com.awanrpn.invenmanager.model.entity.listener.EntityOperationAwareListener;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@EntityListeners({EntityOperationAwareListener.class})

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements EntityOperationAware {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    @NotBlank
    private String name;

    @Email
    @NotNull
    @Column(unique = true, nullable = true)
    private String email;

    @NotNull
    @NotBlank
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        ADMIN, USER
    }

    @ElementCollection
    private Set<Token> tokens;

    @OneToMany(mappedBy = "user")
    private List<Product> products;

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

    @PreRemove
    public void preRemove() {
        products.forEach(product -> product.setUser(null));
    }
}
