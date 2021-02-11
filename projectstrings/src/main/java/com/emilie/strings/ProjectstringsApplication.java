package com.emilie.strings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ProjectstringsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectstringsApplication.class, args);
	}
	@RequestMapping("/")
	public String hello() {
		return "Hello client, how are you doing";
	}
	@RequestMapping("/random")
	public String random() {
		return "Spring Boot is great! So easy to just response with strings";
	}

}
