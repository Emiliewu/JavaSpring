package com.emilie.human;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HumanController {
	private String name;
	private String message;
	@RequestMapping("/") 
	public String hellohuman(@RequestParam(value="name", required=false) String name) {
		if (name == null) {
			setName("Human");
		} else {
			setName(name);
		}
		setMessage("Welcome to Spring Boot!");
		return "Hello " + getName() + System.lineSeparator() + getMessage();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage() {
		return this.message;
	}
	
}
