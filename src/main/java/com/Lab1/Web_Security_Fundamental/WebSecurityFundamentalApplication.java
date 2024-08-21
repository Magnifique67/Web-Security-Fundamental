package com.Lab1.Web_Security_Fundamental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class WebSecurityFundamentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebSecurityFundamentalApplication.class, args);
	}

}
