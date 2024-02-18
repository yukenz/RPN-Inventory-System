package com.awanrpn.invenmanager.service;

import com.awanrpn.invenmanager.mapper.DTOMapper;
import com.awanrpn.invenmanager.mapper.OrderMapper;
import com.awanrpn.invenmanager.mapper.ProductMapper;
import com.awanrpn.invenmanager.mapper.UserMapper;
import com.awanrpn.invenmanager.model.dto.order.GetOrderResponse;
import com.awanrpn.invenmanager.model.dto.product.GetProductResponse;
import com.awanrpn.invenmanager.model.dto.user.*;
import com.awanrpn.invenmanager.model.entity.Order;
import com.awanrpn.invenmanager.model.entity.Product;
import com.awanrpn.invenmanager.model.entity.User;
import com.awanrpn.invenmanager.model.exception.UserModelExceptionBuilder;
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

    /* Mapper */
    private final UserMapper userMapper = DTOMapper.USER_MAPPER;
    private final ProductMapper productMapper = DTOMapper.PRODUCT_MAPPER;
    private final OrderMapper orderMapper = DTOMapper.ORDER_MAPPER;

    /* Repository */
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    /* Exception Builder */
    private final UserModelExceptionBuilder userModelExceptionBuilder;

    @Transactional
    public CreateUserResponse createUser(CreateUserRequest createUserRequest) {

        User user = userMapper.createUserFromRequest(createUserRequest);

        userRepository.save(user);
        return userMapper.createUserResponse(user);
    }

    @Transactional(readOnly = true)
    public List<GetAllUserResponse> getAllUsers() {

        List<User> allUsers = userRepository.findAll();

        return allUsers.stream()
                .map(userMapper::getAllUserResponse)
                .toList();

    }

    @Transactional(readOnly = true)
    public GetUserByIdResponse getUserById(String uuid) {

        User user = userRepository.findById(uuid)
                .orElseThrow(() -> userModelExceptionBuilder.userNotFound(uuid));

        return userMapper.getUserByIdResponse(user);
    }

    @Transactional
    public UpdateUserResponse updateUser(String uuid, UpdateUserRequest updateUserRequest) {

        /* Throwable */
        User userInDb = userRepository.findById(uuid)
                .orElseThrow(() -> userModelExceptionBuilder.userNotFound(uuid));

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

    @Transactional
    public Boolean deleteUser(String uuid) {


        User user = userRepository.findById(uuid)
                .orElseThrow(() -> userModelExceptionBuilder.userNotFound(uuid));

        userRepository.delete(user);

        return true;
    }

    @Transactional(readOnly = true)
    public List<GetProductResponse>
    getProductsByUser(
            String uuid
    ) {

        User user = userRepository.findById(uuid)
                .orElseThrow(() -> userModelExceptionBuilder.userNotFound(uuid));

        List<Product> productsByUser = productRepository.findAllByUser(user);

        return productsByUser.stream()
                .map(productMapper::getProductResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<GetOrderResponse>
    getOrdersByUser(String user_uuid) {

        List<Order> allByUserid = orderRepository.findAllByUserid(user_uuid);

        return allByUserid.stream()
                .map(orderMapper::getOrderResponse).toList();

    }

}
