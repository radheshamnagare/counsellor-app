package com.radhesham.counsellor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@ComponentScan(basePackages = {"com.radhesham"})
@PropertySources(value = {@PropertySource("classpath:application.properties") ,
		@PropertySource("classpath:config.properties")})
public class CounsellorApplication {

	public static void main(String[] args) {

		SpringApplication.run(CounsellorApplication.class, args);
	}

}
