package com.awanrpn.invenmanager.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        servers = {@Server(url = "http://localhost:8080", description = "localhost")},
        info = @Info(
                title = "RPN IS",
                summary = "RPN IS",
                description = "Inventory System App",
                contact = @Contact(name = "awan", email = "yuyun.purniawan@gmail.com", url = "https://aone.my.id"),
                termsOfService = "Nothing",
                license = @License(name = "FLOSS"),
                version = "v1")
//        tags = {@Tag(name = "RPN", description = "Random Para Nolep"), @Tag(name = "IS", description = "Inventory System")}
)

public class OpenApiDefinitionConfig {
}
