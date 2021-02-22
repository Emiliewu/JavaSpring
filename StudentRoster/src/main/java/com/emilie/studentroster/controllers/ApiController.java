package com.emilie.studentroster.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.emilie.studentroster.models.Classmodel;
import com.emilie.studentroster.models.Contact;
import com.emilie.studentroster.models.Dorm;
import com.emilie.studentroster.models.Student;
import com.emilie.studentroster.services.ApiService;

@Controller
public class ApiController {
	private final ApiService apiservice;
	
	public ApiController(ApiService apiservice) {
		this.apiservice = apiservice;
	}
	//new student page
	@RequestMapping("/students/new")
	public String newStudent(@ModelAttribute("student") Student student) {
		return "newstudent.jsp";
	}
//	// create student

	@RequestMapping("/students/create")
	public String createStudent(@Valid @ModelAttribute("student") Student student, BindingResult result) {
		if(result.hasErrors()) {
			return "newstudent.jsp";
		} else {
//			System.out.println(student.toString());
			apiservice.CreateStudent(student);
			return "redirect:/students/new";
		}
	}

	//root to new student page
	@RequestMapping("/")
	public String root() {
		return "redirect:/students/new";
	}
	//new contact info page
	@RequestMapping("/contact/new")
	public String newContact(@ModelAttribute("contact") Contact contact, Model model) {
		List<Student> students = apiservice.findAllStudent();
		model.addAttribute("students", students);
		return "newcontact.jsp";
	}
	
	@RequestMapping("/contacts/create")
	public String createStudent(@Valid @ModelAttribute("contact") Contact contact, BindingResult result, Model model, @RequestParam("student") Long student_id) {
		if(result.hasErrors()) {
			List<Student> students = apiservice.findAllStudent();
			model.addAttribute("students", students);
			return "newcontact.jsp";
		} else {
			Student student = apiservice.StudentfindById(student_id);
			System.out.println(student.getContact());	
			if(student.getContact()==null) {
				apiservice.CreateContact(contact);
			} else {
				apiservice.UpdateContact(contact);
			}
//			System.out.println(contact.toString());		
			return "redirect:/contact/new";
		}
	}
	// student dashboard
	@RequestMapping("/students")
	public String allStudents(Model model) {
		List<Student> students = apiservice.findAllStudent();
		model.addAttribute("students", students);
		return "allstudent.jsp";
	}
	// new dorm page
	@RequestMapping("/dorms/new")
	public String newDorm(@ModelAttribute("dorm") Dorm dorm) {
		return "newdorm.jsp";
	}
	// create dorm
	@RequestMapping("/dorms/create")
	public String createDorm(@Valid @ModelAttribute("dorm") Dorm dorm, BindingResult result) {
		if(result.hasErrors()) {
			return "newdorm.jsp";
		} else {
			apiservice.createDorm(dorm);
			return "redirect:/dorms/new";
		}
	}
	// dorm detail page
	@RequestMapping("/dorms/{dorm_id}")
	public String dormDetail(Model model, @PathVariable("dorm_id") Long dorm_id) {
		List<Student> students = apiservice.findAllStudent();
		Dorm thisdorm = apiservice.findDormById(dorm_id);
		model.addAttribute("thisdorm", thisdorm);
		model.addAttribute("students", students);
		return "dormdetail.jsp";
	}
	// add a student to dorm
	@RequestMapping("/dorms/{dorm_id}/add")
	public String addStudentToDorm(@PathVariable("dorm_id") Long dorm_id, @RequestParam("student") String id) {
		Long student_id = Long.parseLong(id);
		apiservice.addDormtoStudent(dorm_id, student_id);
		return "redirect:/dorms/" + dorm_id;
	}
	// remove a student from dorm
	@RequestMapping("/dorms/{dorm_id}/remove")
	public String removeStudentFromDorm(@PathVariable("dorm_id") Long dorm_id, @RequestParam("student") String id) {
		Long student_id = Long.parseLong(id);
		apiservice.removeDorm(student_id);
		return "redirect:/dorms/" + dorm_id;
	}
	// new class page
	@RequestMapping("/classes/new")
	public String newClass(@ModelAttribute("theclass") Classmodel theclass) {
		return "newclass.jsp";
	}
	// create a new class
	@RequestMapping(value="/classes/create", method=RequestMethod.POST)
	public String createClass(@Valid @ModelAttribute("theclass") Classmodel theclass, BindingResult result) {
		if (result.hasErrors()) {
			return "newclass.jsp";
		} else {
			apiservice.createClass(theclass);
			return "redirect:/classes/new";
		}
	}
	
	// class detail page with all students names in the class
	@RequestMapping("/classes/{class_id}")
	public String classDetail(@PathVariable("class_id") Long class_id, Model model) {
		Classmodel theclass = apiservice.findClassById(class_id);
		List<Student> students = theclass.getStudents();
		model.addAttribute("students", students);
		model.addAttribute("theclass", theclass);
		return "classdetail.jsp";
	}
	
	// Student detail page with all the classes registered which can be dropped and other classes that can be added
	@RequestMapping("/students/{student_id}")
	public String studentDetail(@PathVariable("student_id") Long student_id, Model model) {
		Student student = apiservice.StudentfindById(student_id);
		model.addAttribute("student", student);
		List<Classmodel> otherclasses = apiservice.otherClass(student_id);
		model.addAttribute("otherclasses", otherclasses);
		return "studentdetail.jsp";
	}
	// add a student to class
	@RequestMapping("/students/{student_id}/add")
	public String addStudentToClass(@RequestParam("class") String theid, @PathVariable("student_id") Long student_id) {
		Long class_id = Long.parseLong(theid);
		apiservice.addStudentToClass(class_id, student_id);
		return "redirect:/students/" + student_id;
	}
	
	// remove a student from the class
	@RequestMapping("/students/{student_id}/drop")
	public String removeStudentFromClass(@PathVariable("student_id") Long student_id, @RequestParam("theclass") String theid) {
		Long class_id = Long.parseLong(theid);
		apiservice.removeStudentFromClass(class_id, student_id);
		return "redirect:/students/" + student_id;
	}
	
}
