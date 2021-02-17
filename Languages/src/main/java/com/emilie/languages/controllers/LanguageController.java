package com.emilie.languages.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.emilie.languages.models.Language;
import com.emilie.languages.services.LanguageService;

@Controller
public class LanguageController {
	
	private LanguageService languageservice;
	
	public LanguageController(LanguageService languageservice) {
		this.languageservice = languageservice;
	}
	//show all and the add new form
	@RequestMapping("/languages")
	public String index(Model model, @ModelAttribute("lang") Language lang) {
		List<Language> languages = languageservice.allLanguages();
		System.out.println(languages);
		model.addAttribute("languages", languages);
		return "/languages/index.jsp";
	}
	//redirect
	@RequestMapping("/")
	public String root() {
		return "redirect:/languages";
	}
	// add new
	@RequestMapping(value="/languages/add", method=RequestMethod.POST)
	public String createLanguage(@Valid @ModelAttribute("lang") Language lang, BindingResult result) {
		if (result.hasErrors()) {
            return "/languages/index.jsp";
        } else {
            languageservice.createLanguage(lang);
            return "redirect:/languages";
        }
	}
	// show detail
	@RequestMapping("/languages/{id}")
	public String showLanguage(@PathVariable("id") Long id, Model model) {
		Language language = languageservice.findLanguage(id);
		if(language != null) {
			model.addAttribute("language", language);
		}
		return "/languages/show.jsp";
	}
	// edit page
	@RequestMapping("/languages/{id}/edit")
	public String edit(@PathVariable("id") Long id, Model model, @ModelAttribute("lang") Language lang) {
		Language language = languageservice.findLanguage(id);
		model.addAttribute("language", language);
		return "/languages/edit.jsp";
	}
	@RequestMapping(value="/languages/edit/{id}", method=RequestMethod.PUT)
	public String update(@Valid @ModelAttribute("lang") Language lang, BindingResult result,  RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", "Input not valid");
			return "/languages/edit.jsp";
		} else {
			languageservice.updateLang(lang);
			return "redirect:/languages";
		}
	}
	//delete
	@RequestMapping("/languages/delete/{id}")
	public String destroy(@PathVariable("id") Long id) {
		languageservice.deleteLang(id);
		return "redirect:/languages";
	}
	
}
