package com.awanrpn.invenmanager.model.entity.listener;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;


public class EntityOperationAwareListener {

    @PrePersist
    void preSave(EntityOperationAware object) {
        object.onCreate(LocalDateTime.now());
    }

    @PreUpdate
    void preUpdate(EntityOperationAware object) {
        object.onUpdate(LocalDateTime.now());
    }

}
