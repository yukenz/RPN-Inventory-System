package com.awanrpn.invenmanager.model.dto;

public record LogEntity(
        String remoteAddress,
        String identity,
        String resourcePath,
        String incomingPayload,
        String outgouingPayload
) {

}
