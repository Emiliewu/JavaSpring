package com.emilie.studentroster.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.emilie.studentroster.models.Classmodel;
import com.emilie.studentroster.models.Contact;
import com.emilie.studentroster.models.Dorm;
import com.emilie.studentroster.models.Student;
import com.emilie.studentroster.repositories.ClassRepository;
import com.emilie.studentroster.repositories.ContactRepository;
import com.emilie.studentroster.repositories.DormRepository;
import com.emilie.studentroster.repositories.StudentRepository;

@Service
public class ApiService {
	private final StudentRepository sRepo;
	private final ContactRepository cRepo;
	private final DormRepository dRepo;
	private final ClassRepository classRepo;
	
	//constructor
	public ApiService(StudentRepository sRepo, ContactRepository cRepo, DormRepository dRepo, ClassRepository classRepo) {
		this.sRepo = sRepo;
		this.cRepo = cRepo;
		this.dRepo = dRepo;
		this.classRepo = classRepo;
		
	}

	
	// find all students
	public List<Student> findAllStudent() {
		return sRepo.findAll();
	}
	// find all contacts
	public List<Contact> findAllContact() {
		return cRepo.findAll();
	}
	// find student by id
	public Student StudentfindById(Long id) {
		Optional<Student> optionalS = sRepo.findById(id);
		if (optionalS.isPresent()) {
			return optionalS.get();
		} else {
			return null;
		}
	}
	// find contact by id
	public Contact ContactfindById(Long id) {
		Optional<Contact> optionalC = cRepo.findById(id);
		if (optionalC.isPresent()) {
			return optionalC.get();
		} else {
			return null;
		}
	}
	// create student
	public Student CreateStudent(Student s) {
		return sRepo.save(s);
	}
	// create contact
	public Contact CreateContact(Contact c) {
		return cRepo.save(c);
	}
	// update contact

	public Contact UpdateContact(Contact c) {
		Long student_id = c.getStudent().getId();
		Optional<Contact> optionalContact = cRepo.findByStudent_id(student_id);
		if(optionalContact.isPresent()) {
			Contact thisContact = optionalContact.get();
			thisContact.setAddress(c.getAddress());
			thisContact.setCity(c.getCity());
			thisContact.setState(c.getState());
			return cRepo.save(thisContact);
		} else {
			return null;
		}
	}
	
	// find all dorm
	public List<Dorm> findAllDorm() {
		return dRepo.findAll();
	}
	// find dorm by ID
	public Dorm findDormById(Long id) {
		Optional<Dorm> dorm = dRepo.findById(id);
		if(dorm.isPresent()) {
			return dorm.get();
		} else {
			return null;
		}
	}
	//create a dorm
	public Dorm createDorm(Dorm d) {
		return dRepo.save(d);
	}

	
	// add a dorm to student
	public Student addDormtoStudent(Long dorm_id, Long student_id) {
		Optional<Student> student = sRepo.findById(student_id);
		Optional<Dorm> dorm = dRepo.findById(dorm_id);
		if(student.isPresent()&dorm.isPresent()) {
			student.get().setDorm(dorm.get());
			return sRepo.save(student.get());
		} else {
			return null;
		}
	}
	public Student removeDorm(Long student_id) {
		Optional<Student> student = sRepo.findById(student_id);
		if(student.isPresent()) {
			student.get().setDorm(null);
			return sRepo.save(student.get());
		} else {
			return null;
		}
	}
	// find all class
	public List<Classmodel> findAllClass() {
		return classRepo.findAll();
	}
	
	// find class by ID
	public Classmodel findClassById(Long id) {
		Optional<Classmodel> theclass = classRepo.findById(id);
		if(theclass.isPresent()) {
			return theclass.get();
		} else {
			return null;
		}
	}
	// create a new class
	public Classmodel createClass(Classmodel theclass) {
		return classRepo.save(theclass);
	}
	
	// add student to class
	public Classmodel addStudentToClass(Long class_id, Long student_id) {
		Optional<Classmodel> theclass = classRepo.findById(class_id);
		Optional<Student> student = sRepo.findById(student_id);
		List<Student> slist = theclass.get().getStudents();
		slist.add(student.get());
		theclass.get().setStudents(slist);
		classRepo.save(theclass.get());
		return theclass.get();
	}
	// add class to student --->this is the same as above
	public Student addClassToStudent(Long student_id, Long class_id) {
		Optional<Classmodel> theclass = classRepo.findById(class_id);
		Optional<Student> student = sRepo.findById(student_id);
		List<Classmodel> clist = student.get().getClasses();
		clist.add(theclass.get());
		student.get().setClasses(clist);
		sRepo.save(student.get());
		return student.get();
	}
	
	// remove student from class
	public Classmodel removeStudentFromClass(Long class_id, Long student_id) {
		Optional<Classmodel> theclass = classRepo.findById(class_id);
		Optional<Student> student = sRepo.findById(student_id);
		List<Student> slist = theclass.get().getStudents();
		slist.remove(student.get());
		theclass.get().setStudents(slist);
		classRepo.save(theclass.get());
		return theclass.get();
	}
	
	public List<Classmodel> otherClass(Long student_id) {
		Optional<Student> student = sRepo.findById(student_id);
		List<Classmodel> allclasses = classRepo.findAll();
		List<Classmodel> retclasses = classRepo.findAll();
		List<Classmodel> theclasses = student.get().getClasses();
		for (Classmodel c: allclasses) {
			if(theclasses.contains(c)) {
				retclasses.remove(c);
			}
		}
		return retclasses;
	}

}
