package com.awanrpn.invenmanager.controller;

import com.awanrpn.invenmanager.model.entity.User;
import com.awanrpn.invenmanager.model.request.CreateUserRequest;
import com.awanrpn.invenmanager.model.response.CreateUserResponse;
import com.awanrpn.invenmanager.model.response.ResponsePayloadBuilder;
import com.awanrpn.invenmanager.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "Create User"
    )
    @PostMapping
    public ResponseEntity<?> createUser(
            @RequestBody CreateUserRequest createUserRequest
    ) {

        CreateUserResponse response = userService.createUser(createUserRequest);
        return ResponsePayloadBuilder.ok(response);

    }

    @GetMapping
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    //    @GetMapping
    public User getUser() {
        return null;
    }

    //    @PutMapping
    public User updateUser() {
        return null;
    }

    //    @DeleteMapping
    public User deleteUser() {
        return null;
    }


}
