package com.awanrpn.invenmanager.model.entity;

import jakarta.persistence.Embeddable;

import java.sql.Date;

@Embeddable
public class Token {
    private String token;
    private String type;
    private Date expires;
    private boolean blacklisted;
    private Date createdAt;
    private Date updatedAt;
}
