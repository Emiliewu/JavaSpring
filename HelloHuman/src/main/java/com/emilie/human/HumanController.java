package com.emilie.human;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HumanController {
	private String name;
	private String lastName;
	private String message;
	@RequestMapping("/") 
	public String hellohuman(@RequestParam(value="name", required=false) String name, @RequestParam(value="lastname", required=false) String lastName) {
		if (name == null) {
			setName("Human");
		} else {
			setName(name);
		}
		if (lastName == null) {
			setLastName("");
		} else {
			setLastName(lastName);
		}
		setMessage("Welcome to Spring Boot!");
		return "Hello " + getName() + " " + getLastName() + " " + getMessage();
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLastName() {
		return this.lastName;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage() {
		return this.message;
	}
	
}
