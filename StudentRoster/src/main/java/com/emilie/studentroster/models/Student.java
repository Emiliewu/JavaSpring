package com.emilie.studentroster.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name="students")
public class Student {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 1, max = 200)
    private String firstName;
    @NotNull
    @Size(min = 1, max = 200)
    private String lastName;
    @NotNull
    @Min(1)
	private Integer age;
    @OneToOne(mappedBy="student", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Contact contact;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="dorm_id")
    private Dorm dorm;
    @ManyToMany(fetch = FetchType.LAZY)
  	@JoinTable(
  		name = "students_classes",
  		joinColumns = @JoinColumn(name = "student_id"),
  		inverseJoinColumns = @JoinColumn(name = "class_id")
  	)
      private List<Classmodel> classes;
    
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	
	@PrePersist
	protected void onCreate(){
	    this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate(){
	    this.updatedAt = new Date();
	}
	
	public Student() {
		
	}
//	public Student(String firstName, String lastName, Integer age) {
//
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.age = age;
//
//	}
	
	public Student(String firstName, String lastName, Integer age, Contact contact,Dorm dorm, List<Classmodel> classes) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.contact = contact;
		this.dorm = dorm;
		this.classes = classes;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
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
	public Dorm getDorm() {
		return dorm;
	}
	public void setDorm(Dorm dorm) {
		this.dorm = dorm;
	}
	public List<Classmodel> getClasses() {
		return classes;
	}
	public void setClasses(List<Classmodel> classes) {
		this.classes = classes;
	}
	
	
	
}
