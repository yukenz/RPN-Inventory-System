package com.awanrpn.invenmanager.controller;

import com.awanrpn.invenmanager.model.dto.auth.AuthRequest;
import com.awanrpn.invenmanager.model.entity.Token;
import com.awanrpn.invenmanager.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(
            @RequestBody AuthRequest authRequest
    ) {

        Set<Token> tokens = authService.login(authRequest);
        return ResponseEntity.ok(tokens);
    }

    @GetMapping(path = "/logout")
    public ResponseEntity<?> logout(
            @RequestParam(name = "uuid") String userUUID,
            @RequestParam(name = "token") String tokenUUID
    ) {

        Set<Token> tokens = authService.logout(userUUID, tokenUUID);
        return ResponseEntity.ok(tokens);
    }

}
