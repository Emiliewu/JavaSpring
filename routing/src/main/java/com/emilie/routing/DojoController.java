package com.emilie.routing;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//	Create another controller named 'DojoController'. For the routes below, use the @PathVariable annotation.
@RestController
public class DojoController {
//	Have an http GET request to 'http://localhost:8080/dojo' display a text that says 'The dojo is awesome!'.
	@RequestMapping("/{name}")
	public String name(@PathVariable("name") String name) {
		if(name.equals("dojo")) {
			return "The " + name + " is awesome!";
		} 
		
		String[] str = name.split("-");
		String s1 = str[0].substring(0, 1).toUpperCase();
		String s2 = str[1].substring(0, 1).toUpperCase();
//	Have an http GET request to 'http://localhost:8080/burbank-dojo/' display a text that says 'Burbank Dojo is located in Southern California'.
		if (name.contains("-dojo")) {
			 String n = s1+ str[0].substring(1);
			 String d = s2+ str[1].substring(1);
			return n + " " + d + " is located in Southern California!";
		}
//	Have an http GET request to 'http://localhost:8080/san-jose/' display a text that says 'SJ dojo is the headquarter
		return s1 + s2 + " dojo is the headquarter!";
		
	}
	
	
}
