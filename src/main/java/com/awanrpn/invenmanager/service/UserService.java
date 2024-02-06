package com.awanrpn.invenmanager.service;

import com.awanrpn.invenmanager.config.ObjAutoMapper;
import com.awanrpn.invenmanager.model.entity.User;
import com.awanrpn.invenmanager.model.request.CreateUserRequest;
import com.awanrpn.invenmanager.model.request.UpdateUserRequest;
import com.awanrpn.invenmanager.model.response.CreateUserResponse;
import com.awanrpn.invenmanager.model.response.GetUserByIdResponse;
import com.awanrpn.invenmanager.model.response.UpdateUserResponse;
import com.awanrpn.invenmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private ObjAutoMapper objAutoMapper;

    private final UserRepository userRepository;

    @Transactional(timeout = 2)
    public CreateUserResponse createUser(CreateUserRequest cur) {

        User user = new User();

        user.setName(cur.name());
        user.setEmail(cur.email());
        user.setPassword(cur.password());
        user.setRole(cur.role());

        userRepository.save(user);
        return objAutoMapper.createUserResponse(user);
    }

    @Transactional(timeout = 2,readOnly = true)
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    /**
     * @throws IllegalArgumentException If ID Not Found
     */
    @Transactional(readOnly = true)
    public GetUserByIdResponse getUserById(String uuid) {

        User user = userRepository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("User Id tidak ditemukan"));

        return objAutoMapper.getUserByIdResponse(user);
    }

    @Transactional(timeout = 2)
    public UpdateUserResponse updateUser(String uuid, UpdateUserRequest updateUserRequest) {

        /* Throwable */
        User userInDb = userRepository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("User Id tidak ditemukan"));

        /* Update Name */
        if (updateUserRequest.name() != null) {
            userInDb.setName(updateUserRequest.name());
        }

        /* Update Email */
        if (updateUserRequest.email() != null) {
            userInDb.setEmail(updateUserRequest.email());
        }

        /* Update Password */
        if (updateUserRequest.password() != null) {
            userInDb.setPassword(updateUserRequest.password());
        }

        /* Update Role */
        if (updateUserRequest.role() != null) {
            userInDb.setRole(updateUserRequest.role());
        }

        User userSaved = userRepository.save(userInDb);

        return objAutoMapper.updateUserResponse(userSaved);
    }

    @Transactional(timeout = 2)
    public Boolean deleteUser(String uuid) {

        /* Throwable */
        User userInDb = userRepository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("User Id tidak ditemukan"));

        userRepository.delete(userInDb);

        return true;
    }

}
