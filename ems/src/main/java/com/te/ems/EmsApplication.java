package com.te.ems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
//@EnableMongoRepositories(basePackages = "com.te.ems")
@OpenAPIDefinition /* localhost:9090/swagger-ui/index.html */
public class EmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmsApplication.class, args);
	}

}
