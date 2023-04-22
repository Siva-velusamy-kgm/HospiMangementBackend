package com.example.graphql1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.example.controller","com.example.service"})
@EnableJpaRepositories(basePackages={"com.example.repository"})
@EntityScan(basePackages={"com.example.entity"})
public class GraphQl1Application {

    public static void main(String[] args) {
        SpringApplication.run(GraphQl1Application.class, args);
        System.out.println("Server is Running in Port : 6060");
    }

}
