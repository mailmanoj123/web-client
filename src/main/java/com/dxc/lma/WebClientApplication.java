package com.dxc.lma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(servers = { @Server(url = "http://localhost:9090") }, 
info = @Info(title = "CAS Web Client API", version = "v1", 
description = "A project using Spring Boot with Swagger-UI enabled",
license = @License(name = "DXC License", 
url = "https://dxc.com/us/en"), 
contact = @Contact(url = "https://dxc.com/us/en", name = "Manoj")))
public class WebClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebClientApplication.class, args);
	}

}
