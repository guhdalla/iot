package com.fiap.iot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class IotApplication {
	public static void main(String[] args) {
		SpringApplication.run(IotApplication.class, args);
	}

}
