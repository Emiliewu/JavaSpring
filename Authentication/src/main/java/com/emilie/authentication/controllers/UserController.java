package com.emilie.authentication.controllers;

import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.emilie.authentication.models.User;
import com.emilie.authentication.services.UserService;
import com.emilie.authentication.validator.UserValidator;

@Controller
public class UserController {
	
	private final UserService userService;
	private final UserValidator userValidator;
    
    public UserController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }
    
    @RequestMapping("/registration")
    public String registerForm(@ModelAttribute("user") User user) {
        return "registrationPage.jsp";
    }
    @RequestMapping("/login")
    public String login() {
        return "loginPage.jsp";
    }
    
    @RequestMapping(value="/registration", method=RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session, RedirectAttributes redirectAttributes) {
        // if result has errors, return the registration page (don't worry about validations just now)
    	userValidator.validate(user, result);
    	userValidator.validatelength(user, result);
    	userValidator.validateemail(user, result);
    	if (result.hasErrors()) {
    		return "registrationPage.jsp";
    	} else {
    		// check if email is already registered
			User checkuser = userService.findByEmail(user.getEmail());
			if (checkuser == null) {
				User thisuser = userService.registerUser(user);
				session.setAttribute("user_id", thisuser.getId());
				return "redirect:/home";	
			} else {
				redirectAttributes.addFlashAttribute("error", "failed to register, please try again");
				return "redirect:/";
			}
    		// else, save the user in the database, save the user id in session, and redirect them to the /home route
//    		System.out.println(user.getEmail());
    		// validation for email
//    		if(Pattern.matches("[a-zA-Z0-9]{1,}[@]{1}[a-z]{2,}[.]{1}+[a-z]{2,}", user.getEmail())) {	
//    			User thisuser = userService.registerUser(user);
//    			session.setAttribute("user_id", thisuser.getId());
//    			return "redirect:/home";
//    		} else {
//    			redirectAttributes.addFlashAttribute("error", "Please use valid email");
//    			return "redirect:/registration";
//    		}
//			User thisuser = userService.registerUser(user);
//			session.setAttribute("user_id", thisuser.getId());
//			return "redirect:/home";
    	}
    }
    
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        if(userService.findByEmail(email) == null) {
        	redirectAttributes.addFlashAttribute("loginerror", "failed, please try again");
    		return "redirect:/";
        }
    	// if the user is authenticated, save their user id in session
    	if (userService.authenticateUser(email, password)) {
    		User user = userService.findByEmail(email);
    		session.setAttribute("user_id", user.getId());
    		
    		return "redirect:/home";
    	} else {
    		// else, add error messages and return the login page
    		redirectAttributes.addFlashAttribute("error", "failed, please try again");
    		return "redirect:/login";
    	}
    	
    }
    
    @RequestMapping("/home")
    public String home(HttpSession session, Model model) {
        // get user from session, save them in the model and return the home page
    	 Long user_id = (Long) session.getAttribute("user_id");
    	if(user_id == null) {
    		return "redirect:/login";
    	} else {
    		User user = userService.findUserById(user_id);
    		model.addAttribute("user", user);
    		
    		return "homePage.jsp";
    	}
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        // invalidate session
//    	session.removeAttribute("user_id");
    	session.invalidate();
        // redirect to login page
    	return "redirect:/login";
    }
	
}
