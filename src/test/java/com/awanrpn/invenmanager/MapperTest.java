package com.awanrpn.invenmanager;

import com.awanrpn.invenmanager.config.ObjAutoMapper;
import com.awanrpn.invenmanager.model.entity.User;
import com.awanrpn.invenmanager.model.response.CreateUserResponse;
import com.awanrpn.invenmanager.model.response.GetAllUserResponse;
import com.awanrpn.invenmanager.repository.UserRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opentest4j.TestAbortedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@SpringBootTest
public class MapperTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Validator validator;

    @Autowired
    ObjAutoMapper objAutoMapper;

    @Test
    void testMapp() {


        User user = userRepository.findById("3b90cb8d-1151-404c-a7f0-14eaa5d83022")
                .orElseThrow(() -> new TestAbortedException(""));

        CreateUserResponse createUserResponse = objAutoMapper.createUserResponse(user);

        System.out.println(createUserResponse);

        Set<ConstraintViolation<User>> validate = validator.validate(user);

        Assertions.assertTrue(validate.isEmpty());

    }

    @Test
    @Transactional(timeout = 30)
    void testAllUserResponseMap() {

        List<User> allUsers = userRepository.findAll();

        List<GetAllUserResponse> list = allUsers.stream()
                .map(objAutoMapper::getAllUserResponse)
                .toList();

        System.out.println(list);
    }
}
