package com.awanrpn.invenmanager.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Token {

    @Column(unique = true)
    private String token;
    private String type;
    private LocalDateTime expires;
    private boolean blacklisted;
    private LocalDateTime createdAt;

}
