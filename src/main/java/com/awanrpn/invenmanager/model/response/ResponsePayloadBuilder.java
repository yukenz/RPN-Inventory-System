package com.awanrpn.invenmanager.model.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponsePayloadBuilder {

    public static <T> ResponseEntity<ResponsePayload<T>> ok(T data) {
        ResponsePayload<T> responsePayload
                = new ResponsePayload<>("Success", 0, data);
        return ResponseEntity.ok(responsePayload);
    }

    public static ResponseEntity<ResponsePayload<?>> err(Throwable throwable, HttpStatus httpStatus, Integer statusCode) {
        ResponsePayload<?> responsePayload
                = new ResponsePayload<>(throwable.toString(), statusCode, null);
        return ResponseEntity.status(httpStatus).body(responsePayload);
    }

}
