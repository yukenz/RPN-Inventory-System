package com.awanrpn.invenmanager.model.entity;

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
    private String token;
    private String type;
    private LocalDateTime expires;
    private boolean blacklisted;
    private LocalDateTime createdAt;

}
