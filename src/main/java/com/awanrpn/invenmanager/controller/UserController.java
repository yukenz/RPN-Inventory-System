package com.awanrpn.invenmanager.controller;

import com.awanrpn.invenmanager.model.entity.User;
import com.awanrpn.invenmanager.model.request.CreateUserRequest;
import com.awanrpn.invenmanager.model.request.UpdateUserRequest;
import com.awanrpn.invenmanager.model.response.CreateUserResponse;
import com.awanrpn.invenmanager.model.response.GetUserByIdResponse;
import com.awanrpn.invenmanager.model.response.ResponsePayloadBuilder;
import com.awanrpn.invenmanager.model.response.UpdateUserResponse;
import com.awanrpn.invenmanager.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Create User")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>
    createUser(
            @RequestBody CreateUserRequest createUserRequest
    ) {

        CreateUserResponse response = userService.createUser(createUserRequest);
        return ResponsePayloadBuilder.ok(response);

    }

    @Operation(summary = "Get All User")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>
    getAllUsers() {

        List<User> allUser = userService.getAllUsers();
        return ResponsePayloadBuilder.ok(allUser);
    }

    @GetMapping(path = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>
    getUserById(
            @PathVariable(name = "userId") String uuid
    ) {

        GetUserByIdResponse userById = userService.getUserById(uuid);
        return ResponsePayloadBuilder.ok(userById);
    }

    @PutMapping(path = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>
    updateUser(
            @PathVariable(name = "userId") String uuid,
            @RequestBody UpdateUserRequest updateUserRequest
    ) {

        UpdateUserResponse updateUserResponse = userService.updateUser(uuid, updateUserRequest);
        return ResponsePayloadBuilder.ok(updateUserResponse);
    }

    @DeleteMapping(path = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>
    deleteUser(
            @PathVariable(name = "userId") String uuid
    ) {

        Boolean isSuccess = userService.deleteUser(uuid);
        return ResponsePayloadBuilder.ok(isSuccess);
    }

    /* Module External */
    @Operation(summary = "")
    @GetMapping(path = "/{userId}/{productId}")
    public ResponseEntity<?> getProductByUser(
            @PathVariable(name = "userId") String userUUID,
            @PathVariable(name = "productId") String productUUID
    ) {
        return ResponsePayloadBuilder.ok(null);
    }

}
