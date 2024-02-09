package com.awanrpn.invenmanager;

import com.awanrpn.invenmanager.model.dto.ResponsePayload;
import com.awanrpn.invenmanager.model.dto.user.CreateUserRequest;
import com.awanrpn.invenmanager.model.dto.user.CreateUserResponse;
import com.awanrpn.invenmanager.model.dto.user.GetUserByIdResponse;
import com.awanrpn.invenmanager.model.entity.User;
import com.awanrpn.invenmanager.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.opentest4j.TestAbortedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;*/


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserRepository userRepository;

    CreateUserRequest createUserRequest = new CreateUserRequest(
            "yuyun",
            "yuyun.purniawan@gmail.com",
            "awan123",
            User.Role.ADMIN
    );

    private static String uuid = null;

    @Test
    @Order(0)
    @DisplayName("Create User")
    void createUser() throws Exception {

        /* Setup Request */

        HttpHeaders httpHeaders = new HttpHeaders();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        MediaType contentType = MediaType.APPLICATION_JSON;

        /* Do Request */
        mockMvc.perform(post("/api/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(contentType)
                .headers(httpHeaders)
                .content(objectMapper.writeValueAsString(createUserRequest))
                .params(params)
                .locale(Locale.ENGLISH)
                .characterEncoding(StandardCharsets.UTF_8)
        ).andExpectAll(
                status().isOk(),
                jsonPath("$.message", containsStringIgnoringCase("success"))
        ).andDo(result -> {
            String contentAsString = result.getResponse().getContentAsString();
            CreateUserResponse response = objectMapper.readValue(contentAsString, new TypeReference<ResponsePayload<CreateUserResponse>>() {
            }).getData();

            Assertions.assertNotNull(response.id());
            Assertions.assertEquals(createUserRequest.name(), response.name());
            Assertions.assertEquals(createUserRequest.email(), response.email());
            Assertions.assertEquals(createUserRequest.role(), response.role());
            Assertions.assertNotNull(response.createdAt());

            uuid = response.id();

        });


    }

    @Test
    @Order(1)
    @DisplayName("Get User By ID")
    void getUserById() throws Exception {

        /* Setup Request */
        HttpHeaders httpHeaders = new HttpHeaders();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        MediaType contentType = MediaType.ALL;

        if (uuid == null) {
            throw new TestAbortedException("UUID belum berhasil didapatkan");
        }

        /* Do Request */
        mockMvc.perform(get(String.format("/api/users/%s", uuid))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(contentType)
                .headers(httpHeaders)
                .params(params)
                .locale(Locale.ENGLISH)
                .characterEncoding(StandardCharsets.UTF_8)
        ).andExpectAll(
                status().isOk(),
                jsonPath("$.message", containsStringIgnoringCase("success"))
        ).andDo(result -> {
            String contentAsString = result.getResponse().getContentAsString();

            GetUserByIdResponse response = objectMapper.readValue(contentAsString, new TypeReference<ResponsePayload<GetUserByIdResponse>>() {
            }).getData();

            Assertions.assertNotNull(response.id());
            Assertions.assertEquals(createUserRequest.name(), response.name());
            Assertions.assertEquals(createUserRequest.email(), response.email());
            Assertions.assertEquals(createUserRequest.role(), response.role());
            Assertions.assertNotNull(response.createdAt());

        });


    }
}
