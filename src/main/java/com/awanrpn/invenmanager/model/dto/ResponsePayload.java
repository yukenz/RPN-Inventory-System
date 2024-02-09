package com.awanrpn.invenmanager.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponsePayload<T> {
    private String message;
    private Integer statusCode;
    private T data;
}