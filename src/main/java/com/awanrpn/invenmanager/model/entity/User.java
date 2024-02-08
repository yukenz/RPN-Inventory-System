package com.awanrpn.invenmanager.model.entity;

import com.awanrpn.invenmanager.model.entity.listener.EntityTimeAware;
import com.awanrpn.invenmanager.model.entity.listener.EntityTimeAwareListener;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

@Entity
@Table(name = "users")
@EntityListeners({EntityTimeAwareListener.class})

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements EntityTimeAware {

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

    @ElementCollection(targetClass = Token.class)
    private List<Token> tokens;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference // User memanage Product
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
}
