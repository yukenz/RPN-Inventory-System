package com.awanrpn.invenmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class ManajemenInventoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManajemenInventoryApplication.class, args);
    }

}
