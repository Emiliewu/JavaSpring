package com.emilie.relationship.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.emilie.relationship.models.License;
import com.emilie.relationship.models.Person;
import com.emilie.relationship.services.LicenseService;
import com.emilie.relationship.services.PersonService;

@Controller
public class DriverController {
	private final LicenseService lservice;
	private final PersonService pservice;
	
	public DriverController(LicenseService lservice, PersonService pservice) {
		this.lservice = lservice;
		this.pservice = pservice;
	}
	//new person page
	@RequestMapping("/persons/new")
	public String newPerson(@ModelAttribute("person") Person person) {
		return "newperson.jsp";
	}
	@RequestMapping("/")
	public String root() {
		return "redirect:/persons/new";
	}
	// add new person
	@RequestMapping(value="/persons/new", method=RequestMethod.POST)
	public String createPerson(@Valid @ModelAttribute("person") Person person, BindingResult result) {
		if(result.hasErrors()) {
			return "newperson.jsp";
		} else {
			System.out.println(person);
//			pservice.createPerson(person);
			return "redirect:/persons/new";
			}
		}
	//new license page
	@RequestMapping("/licenses/new")
	public String newLicense(@ModelAttribute("license") License license, Model model) {
		List<Person> persons = pservice.allPerson();
		model.addAttribute("persons", persons);
		return "newlicense.jsp";
	}
	// add new license and redirect to the detail page
	@RequestMapping(value="/licenses/new", method=RequestMethod.POST)
	public String createLicense(@Valid @ModelAttribute("license") License license, BindingResult result, @RequestParam("person") Long person_id) {
		if(result.hasErrors()) {
			return "newlicense.jsp";
		} else {
			Person person = pservice.findById(person_id);
			if(person.getLicense() == null) {
				lservice.createLicense(license);
			} else {
				lservice.updateLicense(license);
			}
//			System.out.println(license.getPerson().getId());
//			return "redirect:/persons/1";
			return "redirect:/persons/" + license.getPerson().getId();
		}
	}
	// detail page
	@RequestMapping("/persons/{id}")
	public String details(@PathVariable("id") Long id, Model model) {
		Person p = pservice.findById(id);
//		License l = lservice.findByPerson(id);
//		System.out.println(p);
//		System.out.println(l);
		model.addAttribute("person", p);
//		model.addAttribute("license", l);
		return "detail.jsp";
	}
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
}