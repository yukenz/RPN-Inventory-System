package com.awanrpn.invenmanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OrderController {

    @GetMapping(path = "/orders")
    String orders() {
        return "orders";
    }
}
