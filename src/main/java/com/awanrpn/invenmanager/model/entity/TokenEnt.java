package com.awanrpn.invenmanager.model.entity;

import jakarta.persistence.*;

import java.sql.Date;

//@Entity
//@Table(name = "failToken")
public class TokenEnt {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String token;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id")
    private User user;

    private String type;
    private Date expires;
    private boolean blacklisted;
    private Date createdAt;
    private Date updatedAt;
}
