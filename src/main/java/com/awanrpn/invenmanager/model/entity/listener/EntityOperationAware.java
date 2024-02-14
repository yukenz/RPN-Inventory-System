package com.awanrpn.invenmanager.model.entity.listener;

import java.time.LocalDateTime;

public interface EntityOperationAware {
    void onCreate(LocalDateTime createdAt);

    void onUpdate(LocalDateTime updatedAt);
}
