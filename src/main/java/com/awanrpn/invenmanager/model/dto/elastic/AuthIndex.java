package com.awanrpn.invenmanager.model.dto.elastic;

public record AuthIndex(
        String principal,
        String pathResource,
        String ip,
        Boolean authenticated,
        String requestPayload,
        String timestamp
) {

}
