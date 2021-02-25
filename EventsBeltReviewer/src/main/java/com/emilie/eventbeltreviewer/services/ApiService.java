package com.emilie.eventbeltreviewer.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.emilie.eventbeltreviewer.models.Comment;
import com.emilie.eventbeltreviewer.models.Event;
import com.emilie.eventbeltreviewer.models.User;
import com.emilie.eventbeltreviewer.repositories.CommentRepository;
import com.emilie.eventbeltreviewer.repositories.EventRepository;
import com.emilie.eventbeltreviewer.repositories.UserRepository;

@Service
public class ApiService {
	private final UserRepository userRepo;
	private final EventRepository eventRepo;
	private final CommentRepository cRepo;
	
	public ApiService(UserRepository userRepo, EventRepository eventRepo, CommentRepository cRepo) {
		this.cRepo = cRepo;
		this.eventRepo = eventRepo;
		this.userRepo = userRepo;
	}
	
	//find all events
	public List<Event> findAllEvents(){
		return eventRepo.findAll();
	}
	//find event in state
	public List<Event> findEventInState(String state) {
		return eventRepo.findByStateContaining(state);
	}
	//find event outside state
	public List<Event> findEventOutofState(String state) {
		return eventRepo.findByStateNot(state);
	}
	//create a event
	public Event createEvent(Event event, Long user_id) {
		Event e = eventRepo.save(event);
		Optional<User> user = userRepo.findById(user_id);
		e.setHost(user.get());
		eventRepo.save(e);
		return e;
	}
	//Add attendee to a event
	public Event addAttendeeToEvent(Long event_id, Long attendee_id) {
		Optional<Event> event = eventRepo.findById(event_id);
		Optional<User> attendee = userRepo.findById(attendee_id);
		List<User> attendeelist = event.get().getAttendees();
		attendeelist.add(attendee.get());
		event.get().setAttendees(attendeelist);
		return eventRepo.save(event.get());
	}
	
	// do not attend the event
	public Event removeAttendeefromEvent(Long event_id, Long attendee_id) {
		Optional<Event> event = eventRepo.findById(event_id);
		Optional<User> attendee = userRepo.findById(attendee_id);
		List<User> attendeelist = event.get().getAttendees();
		attendeelist.remove(attendee.get());
		event.get().setAttendees(attendeelist);
		return eventRepo.save(event.get());
	}
	// find event by Id
	public Event findEventById(Long event_id) {
		Optional<Event> event = eventRepo.findById(event_id);
		if (event.isPresent()) {
			return event.get();
		} else {
			return null;
		}
	}
	
	// update the event
	public Event updateEvent(Event event, Long event_id) {
		Optional<Event> e = eventRepo.findById(event_id);
		e.get().setName(event.getName());
		e.get().setLocation(event.getLocation());
		e.get().setEventDate(event.getEventDate());
		e.get().setState(event.getState());
		return eventRepo.save(e.get());
	}
	
	//delete the event
	public void deleteEvent(Event event) {
		eventRepo.delete(event);
	}
	
	//create comment
	public Comment createComment(Comment comment, Long event_id, Long user_id) {
		Comment c = new Comment();
		c.setComment(comment.getComment());
		Optional<Event> e = eventRepo.findById(event_id);
		Optional<User> user = userRepo.findById(user_id);
		c.setEvent(e.get());
		c.setUser(user.get());
		return cRepo.save(c);
	}
	
	
	//------------login and registration -----------------------------

    // register user and hash their password
    public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepo.save(user);
    }
    
    // find user by email
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
    
    // find user by id
    public User findUserById(Long id) {
    	Optional<User> u = userRepo.findById(id);
    	
    	if(u.isPresent()) {
            return u.get();
    	} else {
    	    return null;
    	}
    }
    
    // authenticate user
    public boolean authenticateUser(String email, String password) {
        // first find the user by email
        User user = userRepo.findByEmail(email);
        // if we can't find it by email, return false
        if(user == null) {
            return false;
        } else {
            // if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }
	
	//--------------end of login Registration------//
}
