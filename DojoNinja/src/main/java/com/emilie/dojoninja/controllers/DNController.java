package com.emilie.dojoninja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.emilie.dojoninja.models.Dojo;
import com.emilie.dojoninja.models.Ninja;
import com.emilie.dojoninja.services.DNService;

@Controller
public class DNController {
	private final DNService dnservice;
	public DNController(DNService dnservice) {
		this.dnservice = dnservice;
	}
	// direct root page
	@RequestMapping("/")
	public String root() {
		return "redirect:/dojos/new/";
	}
	//create dojo page
	@RequestMapping("/dojos/new")
	public String newDojo(@ModelAttribute("dojo") Dojo dojo) {
		return "newdojo.jsp";
	}
	// add new dojo
	@RequestMapping(value="/dojos/create", method=RequestMethod.POST)
	public String createDojo(@Valid @ModelAttribute("dojo") Dojo dojo, BindingResult result) {
		if(result.hasErrors()) {
			return "newdojo.jsp";
		} else {
			dnservice.createDojo(dojo);
			return "redirect:/dojos/new";
		}
	}
	
	//create ninja page
	@RequestMapping("/ninjas/new")
	public String newNinja(@ModelAttribute("ninja") Ninja ninja, Model model) {
		List<Dojo> dojos = dnservice.findAllDojo();
		model.addAttribute("dojos", dojos);
		return "newninja.jsp";
	}
	// add new ninja
	@RequestMapping(value="/ninjas/create", method=RequestMethod.POST)
	public String createNinja(@Valid @ModelAttribute("ninja") Ninja ninja, BindingResult result) {
		if(result.hasErrors()) {
			return "newninja.jsp";
		} else {
			dnservice.createNinja(ninja);
			return "redirect:/ninjas/new";
		}
	}
	// display all ninjas of a dojo
	@RequestMapping("/dojos/{dojo_id}")
	public String dojoDetail(Model model, @PathVariable("dojo_id") Long dojo_id) {
		Dojo dojo = dnservice.findDojoById(dojo_id);
		model.addAttribute("dojo", dojo);
		return "/dojodetail.jsp";
	}
}
