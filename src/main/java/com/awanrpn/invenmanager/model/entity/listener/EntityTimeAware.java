package com.awanrpn.invenmanager.model.entity.listener;

import java.time.LocalDateTime;

public interface EntityTimeAware {
    void onCreate(LocalDateTime createdAt);

    void onUpdate(LocalDateTime updatedAt);
}
