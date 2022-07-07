package com.fcorrionero.inditex.entrypoint.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({"com.fcorrionero.inditex"})
@EntityScan("com.fcorrionero.inditex.domain")
@EnableJpaRepositories("com.fcorrionero.inditex")
@SpringBootApplication
public class InditexApplication {

    public static void main(String[] args) {
        SpringApplication.run(InditexApplication.class, args);
    }

}
