package com.prueba.ingeneo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.prueba.ingeneo")
public class IngeneoApplication {

	public static void main(String[] args) {
		SpringApplication.run(IngeneoApplication.class, args);
	}

}
