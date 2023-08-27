package ru.k2.ibank;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Ibank", version = "0.1", description = "JavaBegin K-2 group project"))
public class iBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(iBankApplication.class, args);
    }

}
