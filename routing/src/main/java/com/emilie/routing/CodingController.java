package com.emilie.routing;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coding")
public class CodingController {
//	Create a controller named 'CodingController'. For the first 3 routes, use the @RequestMapping annotation.
//	Have an http GET request to 'http://localhost:8080/coding' display a text that says 'Hello Coding Dojo!'.
	@RequestMapping("/")
	public String hello() {
		return "Hello Coding Dojo!";
	}
//	Have an http GET request to 'http://localhost:8080/coding/python' display a text that says 'Python/Django was awesome!'.
	@RequestMapping("/python")
	public String python() {
		return "Python/Django was awesome!";
	}
//	Have an http GET request to 'http://localhost:8080/coding/java' display a text that says 'Java/Spring is better!'.
	@RequestMapping("/java")
	public String java() {
		return "Java/Spring is better!";
	}

}
