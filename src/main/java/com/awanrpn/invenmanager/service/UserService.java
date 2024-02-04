package com.awanrpn.invenmanager.service;

import com.awanrpn.invenmanager.config.ObjAutoMapper;
import com.awanrpn.invenmanager.model.entity.User;
import com.awanrpn.invenmanager.model.request.CreateUserRequest;
import com.awanrpn.invenmanager.model.response.CreateUserResponse;
import com.awanrpn.invenmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private ObjAutoMapper objAutoMapper;

    private final UserRepository userRepository;

    @Transactional(timeout = 2, propagation = Propagation.REQUIRED)
    public CreateUserResponse createUser(CreateUserRequest cur) {

        User user = new User();

        user.setName(cur.name());
        user.setEmail(cur.email());
        user.setPassword(cur.password());
        user.setRole(cur.role());

        userRepository.save(user);

        return objAutoMapper.createUserResponse(user);
    }

    @Transactional(timeout = 2, propagation = Propagation.REQUIRED)
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    /**
     * @throws IllegalArgumentException If ID Not Found
     */
    public User getUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User Id tidak ditemukan"));
    }

    public User updateUser(User user) {

        /* Throwable */
        User userInDb = getUserById(user.getId());

        /* Update Name */
        if (user.getName() != null) {
            userInDb.setName(user.getName());
        }

        /* Update Email */
        if (user.getEmail() != null) {
            userInDb.setEmail(user.getEmail());
        }

        /* Update Password */
        if (user.getPassword() != null) {
            userInDb.setPassword(user.getPassword());
        }

        /* Update Role */
        if (user.getRole() != null) {
            userInDb.setRole(user.getRole());
        }

        userRepository.save(userInDb);

        return userInDb;
    }

    public User deleteUser(User user) {

        try {
            userRepository.delete(user);
            return user;
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }

    }

}
