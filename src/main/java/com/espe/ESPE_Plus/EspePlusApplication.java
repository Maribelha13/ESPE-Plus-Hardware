package com.espe.ESPE_Plus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

@ComponentScan(basePackages = "com.espe")
public class EspePlusApplication {

	public static void main(String[] args) {
		SpringApplication.run(EspePlusApplication.class, args);
	}
}