package com.emilie.counter;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CounterController {
	@RequestMapping("/")
	public String index(HttpSession session) {
		Integer count = (Integer) session.getAttribute("count");
		
		if (count == null || count == 0) {
			count = 1;
		} else {
			count++;
		}
		session.setAttribute("count", count);
		return "index.jsp";
	}
	@RequestMapping("/counter")
	public String counter(HttpSession session, Model model) {
		Integer count = (Integer) session.getAttribute("count");
		if(count == null ) {
			count = 0;
		}
		String message = "You have visited ";
		model.addAttribute("counter", count);
		model.addAttribute("message", message);
		return "count.jsp";
	}
	@RequestMapping("/reset")
	public String reset(HttpSession session, Model model) {
		Integer count = (Integer) session.getAttribute("count");
		count = 0;
		session.setAttribute("count", 0);
		String message = "Counter reset, You have visited ";
		model.addAttribute("counter", count);
		model.addAttribute("message", message);
		return "count.jsp";
	}
	@RequestMapping("/counter2")
	public String counter2(HttpSession session, Model model) {
		Integer count = (Integer) session.getAttribute("count");
		if(count == null) {
			count = 0;
		} else {
			count += 1;
		}
		session.setAttribute("count", count);
		String message = "You have visited ";
		model.addAttribute("counter", count);
		model.addAttribute("message", message);
		return "counter2.jsp";
	}
	@RequestMapping("/reset2")
	public String reset2(HttpSession session, Model model) {
		Integer count = (Integer) session.getAttribute("count");
		count = 0;
		session.setAttribute("count", 0);
		String message = "Counter reset, You have visited ";
		model.addAttribute("counter", count);
		model.addAttribute("message", message);
		return "counter2.jsp";
	}
}
