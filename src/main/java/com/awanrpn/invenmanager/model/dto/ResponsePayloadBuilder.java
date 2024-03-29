package com.awanrpn.invenmanager.model.dto;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class ResponsePayloadBuilder {

    public static <T> ResponseEntity<ResponsePayload<T>> ok(T data) {
        ResponsePayload<T> responsePayload
                = new ResponsePayload<>("Success", 0, data);
        return ResponseEntity.ok(responsePayload);
    }

    public static ResponseEntity<ResponsePayload<?>> err(String message, HttpStatusCode httpStatus, Integer statusCode) {
        ResponsePayload<Object> responsePayload
                = new ResponsePayload<>(message, statusCode, null);
        return ResponseEntity.status(httpStatus).body(responsePayload);
    }

}
