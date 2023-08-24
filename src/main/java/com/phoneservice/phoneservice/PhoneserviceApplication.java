package com.phoneservice.phoneservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class PhoneserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhoneserviceApplication.class, args);
	}

}
