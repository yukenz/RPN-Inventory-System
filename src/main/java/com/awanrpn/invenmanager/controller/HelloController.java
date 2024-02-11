package com.awanrpn.invenmanager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(path = "/hello")
public class HelloController {

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<?> getHello(Principal authentication) {

        return ResponseEntity.ok("OK");
    }
}
