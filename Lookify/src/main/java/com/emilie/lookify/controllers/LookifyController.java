package com.emilie.lookify.controllers;

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

import com.emilie.lookify.models.Lookify;
import com.emilie.lookify.services.LookifyService;

@Controller
public class LookifyController {
	private LookifyService lservice;
	
	public LookifyController(LookifyService lservice) {
		this.lservice = lservice;
	}
	
	//homepage
	@RequestMapping("/")
	public String index() {
		return "index.jsp";
	}
	//all songs for dashboard
	@RequestMapping("/dashboard")
	public String dashboard(Model model) {
		List<Lookify> songs = lservice.allLookify();
		model.addAttribute("songs", songs);
		return "/dashboard.jsp";
	}
	//add new page
	@RequestMapping("/songs/new")
	public String newSong(@ModelAttribute("song") Lookify song) {
		return "new.jsp";
	}
	//add new song
	@RequestMapping(value="/songs/new", method=RequestMethod.POST)
	public String createSong(@Valid @ModelAttribute("song") Lookify song, BindingResult result) {
		if(result.hasErrors()) {
			return "new.jsp";
		} else {
			lservice.createLookify(song);
			return "redirect:/dashboard";
		}
	}
	
	// show detail of a song
	@RequestMapping("/songs/{id}")
	public String showSong(@PathVariable("id") Long id, Model model) {
		Lookify song = lservice.findLookify(id);
		if(song != null) {
			model.addAttribute("song", song);
		}
		return "detail.jsp";
	}
	// delete
	@RequestMapping(value="/songs/{id}", method=RequestMethod.DELETE)
	public String destroy(@PathVariable("id") Long id) {
		lservice.deleteLookify(id);
		return "redirect:/dashboard";
	}
	// Search by artist

	@RequestMapping(value="/search", method=RequestMethod.POST)
	public String searchSong(@RequestParam(value="searchString") String searchString, Model model) {
		List<Lookify> songs = lservice.findArtist(searchString);
		model.addAttribute("songs", songs);
		model.addAttribute("searchString", searchString);
		return "search.jsp";
	}
	
	//top ten
	@RequestMapping("/search/topTen")
	public String topten(Model model) {
		List<Lookify> songs = lservice.findAllByRating();
		model.addAttribute("songs", songs);
		return "topten.jsp";
	}
}
