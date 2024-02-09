package com.awanrpn.invenmanager.model.entity.listener;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;


public class EntityTimeAwareListener {

    @PrePersist
    void preSave(EntityTimeAware object) {
        object.onCreate(LocalDateTime.now());
    }

    @PreUpdate
    void preUpdate(EntityTimeAware object) {
        object.onUpdate(LocalDateTime.now());
    }

}
