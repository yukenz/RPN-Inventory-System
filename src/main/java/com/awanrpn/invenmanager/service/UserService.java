package com.awanrpn.invenmanager.service;

import com.awanrpn.invenmanager.mapper.DTOMapper;
import com.awanrpn.invenmanager.mapper.ProductMapper;
import com.awanrpn.invenmanager.mapper.UserMapper;
import com.awanrpn.invenmanager.model.dto.product.GetProductResponse;
import com.awanrpn.invenmanager.model.dto.user.*;
import com.awanrpn.invenmanager.model.entity.Order;
import com.awanrpn.invenmanager.model.entity.Product;
import com.awanrpn.invenmanager.model.entity.User;
import com.awanrpn.invenmanager.repository.OrderRepository;
import com.awanrpn.invenmanager.repository.ProductRepository;
import com.awanrpn.invenmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper = DTOMapper.USER_MAPPER;
    private final ProductMapper productMapper = DTOMapper.PRODUCT_MAPPER;

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Transactional(timeout = 2)
    public CreateUserResponse createUser(CreateUserRequest createUserRequest) {

//        User user = new User();
//
//        user.setName(createUserRequest.name());
//        user.setEmail(createUserRequest.email());
//        user.setPassword(createUserRequest.password());
//        user.setRole(createUserRequest.role());

        User user = userMapper.createUserFromRequest(createUserRequest);
        userRepository.save(user);
        return userMapper.createUserResponse(user);
    }

    @Transactional(timeout = 2, readOnly = true)
    public List<GetAllUserResponse> getAllUsers() {

        List<User> allUsers = userRepository.findAll();

        return allUsers.stream()
                .map(userMapper::getAllUserResponse)
                .toList();

    }

    /**
     * @throws IllegalArgumentException If ID Not Found
     */
    @Transactional(readOnly = true)
    public GetUserByIdResponse getUserById(String uuid) {

        User user = userRepository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("User Id tidak ditemukan"));

        return userMapper.getUserByIdResponse(user);
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

        return userMapper.updateUserResponse(userSaved);
    }

    @Transactional(timeout = 2)
    public Boolean deleteUser(String uuid) {

        try {
            if (!userRepository.existsById(uuid)) {
                throw new IllegalArgumentException("User Id tidak ditemukan");
            }
            userRepository.deleteById(uuid);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    @Transactional(timeout = 2, readOnly = true)
    public List<GetProductResponse>
    getProductsByUser(
            String user_uuid
    ) {

        User user = userRepository.findById(user_uuid)
                .orElseThrow(() -> new IllegalArgumentException("User Id tidak ditemukan"));

        List<Product> productsByUser = productRepository.findAllByUser(user);

        return productsByUser.stream()
                .map(productMapper::getProductResponse).toList();
    }

    @Transactional(timeout = 2, readOnly = true)
    public List<Order>
    getOrdersByUser(String user_uuid) {

        return orderRepository.findAllByUserid(user_uuid);
    }

}
