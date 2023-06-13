package com.example.shopmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ShopManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopManagementApplication.class, args);
    }

}
