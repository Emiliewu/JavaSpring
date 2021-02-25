package com.emilie.eventbeltreviewer.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="events")
public class Event {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Size(min=2, max=200)
    private String name;
    @Size(min=2, max=200)
    private String location;
    private String state;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date eventDate;
    
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User host;
    
    @ManyToMany(fetch = FetchType.LAZY)
  	@JoinTable(
  		name = "attendees_shows",
  		joinColumns = @JoinColumn(name = "event_id"),
  		inverseJoinColumns = @JoinColumn(name = "user_id")
  	)
    private List<User> attendees;
    
    @OneToMany(mappedBy="event", fetch = FetchType.LAZY)
    private List<Comment> comments;
    
    public Event() {
    	
    }

	public Event(String name, String location, String state, Date eventDate, User host, List<User> attendees, List<Comment> comments) {
		this.name = name;
		this.location = location;
		this.state = state;
		this.eventDate = eventDate;
		this.host = host;
		this.attendees = attendees;
		this.comments = comments;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getHost() {
		return host;
	}

	public void setHost(User host) {
		this.host = host;
	}

	public List<User> getAttendees() {
		return attendees;
	}

	public void setAttendees(List<User> attendees) {
		this.attendees = attendees;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
    
		
	    
	    
}
