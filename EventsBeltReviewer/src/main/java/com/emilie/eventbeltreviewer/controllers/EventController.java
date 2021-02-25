package com.emilie.eventbeltreviewer.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.emilie.eventbeltreviewer.models.Comment;
import com.emilie.eventbeltreviewer.models.Event;
import com.emilie.eventbeltreviewer.models.User;
import com.emilie.eventbeltreviewer.services.ApiService;
import com.emilie.eventbeltreviewer.validator.UserValidator;

@Controller
public class EventController {
	private final ApiService aservice;
	private final UserValidator userValidator;
	
	public EventController(ApiService aservice, UserValidator userValidator) {
		this.aservice = aservice;
		this.userValidator = userValidator;
	}
	
	//---------login and registration --------------------------------
	@RequestMapping("/")
	public String loginregister(@ModelAttribute("user") User user) {
		return "loginandregister.jsp";
	}
	@RequestMapping(value="/registration", method=RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session, RedirectAttributes redirectAttributes) {
	    // if result has errors, return the registration page 
		userValidator.validate(user, result);
		userValidator.validatelength(user, result);
		userValidator.validateemail(user, result);
		if (result.hasErrors()) {
			return "loginandregister.jsp";
		} else {
			// check if email is already registered
			User checkuser = aservice.findByEmail(user.getEmail());
			if (checkuser == null) {
				User thisuser = aservice.registerUser(user);
				session.setAttribute("user_id", thisuser.getId());
				return "redirect:/events";	
			} else {
				redirectAttributes.addFlashAttribute("error", "failed to register, please try again");
				return "redirect:/";
			}
		}
	}
	@RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        if(aservice.findByEmail(email) == null) {
        	redirectAttributes.addFlashAttribute("loginerror", "failed, please try again");
    		return "redirect:/";
        }
		// if the user is authenticated, save their user id in session
    	if (aservice.authenticateUser(email, password)) {
    		User user = aservice.findByEmail(email);
    		session.setAttribute("user_id", user.getId());
    		
    		return "redirect:/events";
    	} else {
    		// else, add error messages and return the login page
    		redirectAttributes.addFlashAttribute("loginerror", "failed, please try again");
    		return "redirect:/";
    	}
    	
    }
	@RequestMapping("/logout")
    public String logout(HttpSession session) {
        // invalidate session
    	session.invalidate();
        // redirect to login page
    	return "redirect:/";
    }
	
	
	// all events list page --events listed by user's state and other states,  create new event form below
	@RequestMapping("/events")
	public String allEvents(HttpSession session, Model model, @ModelAttribute("event") Event event) {
        // get user from session, save them in the model and return the home page
    	 Long user_id = (Long) session.getAttribute("user_id");
    	if(user_id == null) {
    		return "redirect:/";
    	} else {
    		User user = aservice.findUserById(user_id);
    		String username = user.getFirstName();
    		String userState = user.getState();
    		List<Event> instateEvents = aservice.findEventInState(userState);
    		List<Event> outstateEvents = aservice.findEventOutofState(userState);
    		String todaydate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date());
    		model.addAttribute("user", user);
    		model.addAttribute("outstateEvents", outstateEvents);
    		model.addAttribute("instateEvents", instateEvents);
    		model.addAttribute("username", username);
    		model.addAttribute("user_id", user_id);
    		model.addAttribute("todaydate", todaydate);
    		return "allevents.jsp";
    	}
	}
	
	// Create a new event
	@RequestMapping(value="/events/create", method=RequestMethod.POST)
	public String createEvent(@Valid @ModelAttribute("event") Event event, BindingResult result, HttpSession session, Model model) {
		Long user_id = (Long) session.getAttribute("user_id");
		User user = aservice.findUserById(user_id);
		if (result.hasErrors()) {
    		String username = user.getFirstName();
    		String userState = user.getState();
    		List<Event> instateEvents = aservice.findEventInState(userState);
    		List<Event> outstateEvents = aservice.findEventOutofState(userState);
    		model.addAttribute("user", user);
    		model.addAttribute("outstateEvents", outstateEvents);
    		model.addAttribute("instateEvents", instateEvents);
    		model.addAttribute("username", username);
    		model.addAttribute("user", user);
			return "allevents.jsp";
		} else {
			aservice.createEvent(event, user_id);
			return "redirect:/events";
		}
	}
	
	// join event
	@RequestMapping("/events/{event_id}/join")
	public String joinEvent(@PathVariable("event_id") Long event_id,  HttpSession session) {
		Long user_id = (Long) session.getAttribute("user_id");
		if(user_id == null) {
    		return "redirect:/";
    	} else {
    		aservice.addAttendeeToEvent(event_id, user_id);
    		return "redirect:/events";
    	}	
	}
	// remove event
	@RequestMapping("/events/{event_id}/cancel")
	public String removeEvent(@PathVariable("event_id") Long event_id,  HttpSession session) {
		Long user_id = (Long) session.getAttribute("user_id");
		if(user_id == null) {
    		return "redirect:/";
    	} else {
    		aservice.removeAttendeefromEvent(event_id, user_id);
    		return "redirect:/events";
    	}	
	}
	
	// edit event (only by host)
	@RequestMapping("/events/{event_id}/edit")
	public String editEvent(@ModelAttribute("event") Event event, HttpSession session, @PathVariable("event_id") Long event_id, Model model) {
		Long user_id = (Long) session.getAttribute("user_id");
		if(user_id == null) {
			return "redirect:/";
    	} else {
    		Event e = aservice.findEventById(event_id);
    		if(e.getHost().getId().equals(user_id)) {
    			String todaydate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date());
    			String thisdate =  new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(e.getEventDate());
    			ArrayList<String> allstates = new ArrayList<String>();
    			allstates.add("CA");
    			allstates.add("BC");
    			allstates.add("WA");
    			
    			model.addAttribute("todaydate", todaydate);
    			model.addAttribute("thisdate", thisdate);
    			model.addAttribute("thisevent", e);
    			model.addAttribute("allstates", allstates);
    			return "editevent.jsp";
    		} else {
    			return "redirect:/events";
    		}

    	}
	}
	// submit the update and make changes
	@RequestMapping(value="/events/{event_id}/update", method=RequestMethod.PUT)
	public String updateEvent(@Valid @ModelAttribute("event") Event event, BindingResult result, HttpSession session, @PathVariable("event_id") Long event_id, Model model) {
		Long user_id = (Long) session.getAttribute("user_id");
		if(user_id == null) {
			return "redirect:/";
    	} else {
    		
    		if (result.hasErrors()) {
    			Event e = aservice.findEventById(event_id);
    			if(e.getHost().getId().equals(user_id)) {
    				String todaydate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date());
    				model.addAttribute("todaydate", todaydate);
    				model.addAttribute("thisevent", e);
    				return "editevent.jsp";
    			} else {
    				return "redirect:/events";
    			}
    			
    		} else {
    			aservice.updateEvent(event, event_id);
    			
    			return "redirect:/events";
    		}
    	}
	}
	// delete a event (only host can delete)
	@RequestMapping("events/{event_id}/delete")
	public String destroyEvent(@PathVariable("event_id") Long event_id, HttpSession session) {
		Long user_id = (Long) session.getAttribute("user_id");
		if(user_id == null) {
			return "redirect:/";
    	} else {
    		Event e = aservice.findEventById(event_id);
    		if(e.getHost().getId().equals(user_id)) {
    			aservice.deleteEvent(e);
    			return "redirect:/events";
    		} else {
    			return "redirect:/events";
    		}
    	}
	}
	//event detail page
	@RequestMapping("events/{event_id}")
	public String eventDetail(@ModelAttribute("newcomment") Comment comment, @PathVariable("event_id") Long event_id, HttpSession session, Model model) {
		Long user_id = (Long) session.getAttribute("user_id");
		if(user_id == null) {
			return "redirect:/";
    	} else {
    		Event e = aservice.findEventById(event_id);
    		List<User> attendees = e.getAttendees();
    		List<Comment> messages = e.getComments();
    		int attendee_num = attendees.size();
    		model.addAttribute("thisevent", e);
    		model.addAttribute("user_id", user_id);
    		model.addAttribute("messages", messages);
    		return "eventdetail.jsp";
    	}
		
	}
	//create a comment
	@RequestMapping(value="events/{event_id}/comment", method = RequestMethod.POST)
	public String createComment(@Valid @ModelAttribute("newcomment") Comment comment, BindingResult result, @PathVariable("event_id") Long event_id, HttpSession session, Model model) {
		Long user_id = (Long) session.getAttribute("user_id");
		if(user_id == null) {
			return "redirect:/";
    	} else {
    		if (result.hasErrors()) {
    			Event e = aservice.findEventById(event_id);
        		List<User> attendees = e.getAttendees();
        		List<Comment> messages = e.getComments();
        		int attendee_num = attendees.size();
        		model.addAttribute("thisevent", e);
        		model.addAttribute("user_id", user_id);
        		model.addAttribute("messages", messages);
        		return "eventdetail.jsp";
    		} else {
    			aservice.createComment(comment, event_id, user_id);
    			return "redirect:/events/" + event_id;
    		}
    	}
	}
}
