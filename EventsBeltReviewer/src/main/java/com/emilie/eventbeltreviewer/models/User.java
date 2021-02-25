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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Size(min=2, max=50)
    private String firstName;
    @Size(min=2, max=50)
    private String lastName;
    @NotNull
    @Email(message="Email must be valid")
    private String email;
    @Size(min=5, message="Password must be greater than 5 characters")
    private String password;
    @Transient
    private String passwordConfirmation;
    
    @Size(min=2, max=200)
    private String location;
    @NotNull
    @Size(min=2, max=10)
    private String state;
    
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;

    @OneToMany(mappedBy="host", fetch = FetchType.LAZY)
    private List<Event> events;
    
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<Comment> comment;
    
    @ManyToMany(fetch = FetchType.LAZY)
  	@JoinTable(
  		name = "attendees_shows",
  		joinColumns = @JoinColumn(name = "user_id"),
  		inverseJoinColumns = @JoinColumn(name = "event_id")
  	)
    private List<Event> shows;
    
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    
    public User() {
    	
    }


	public User(String firstName, String lastName, String email, String password, String passwordConfirmation, String location, String state, List<Event> events, List<Comment> comment, List<Event> shows) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.passwordConfirmation = passwordConfirmation;
		this.location = location;
		this.state = state;
		this.events = events;
		this.comment = comment;
		this.shows = shows;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
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
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}
	public List<Comment> getComment() {
		return comment;
	}
	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}
	public List<Event> getShows() {
		return shows;
	}
	public void setShows(List<Event> shows) {
		this.shows = shows;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
    
    
    
}
