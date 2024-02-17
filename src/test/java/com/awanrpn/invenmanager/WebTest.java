package com.awanrpn.invenmanager;

import com.awanrpn.invenmanager.config.WebClientConfig;
import com.awanrpn.invenmanager.model.dto.auth.AuthRequest;
import com.awanrpn.invenmanager.model.dto.elastic.AuthIndex;
import com.awanrpn.invenmanager.webclient.ElasticSearchClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;

@SpringBootTest(classes = {
        WebClientConfig.class
})
public class WebTest {

    @Autowired
    ElasticSearchClient elasticSearchClient;


    @Test
    void testStrReq() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        String epochMillis = String.valueOf(Instant.now().toEpochMilli());

        AuthRequest authRequest = new AuthRequest("yukenz", "awan");

        System.out.println(elasticSearchClient.createAuthRecord(
                new AuthIndex("awan", "/lol", "192.168.0.12", true, objectMapper.writeValueAsString(authRequest), epochMillis)
        ));

    }

    @Test
    void timestampGenerate() {

        long epochMilli = Instant.now().toEpochMilli();
        System.out.println(String.valueOf(epochMilli));
    }
}
