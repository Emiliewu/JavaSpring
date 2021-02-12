package com.emilie.displaydate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class routeController {
	@RequestMapping("/")
	public String index() {
		return "index.jsp";
	}
	@RequestMapping("/date")
	public String date(Model model) throws ParseException {
		String date =  new SimpleDateFormat("EEEE, dd MMMM, YYYY", Locale.ENGLISH).format(new Date());
	
//		System.out.println(date);
		model.addAttribute("date", date);
		return "date.jsp";
	}
	//Thu Feb 11 23:41:18 PST 2021
	//Saturday, the 22 of January, 2028
	@RequestMapping("/time")
	public String time(Model model) throws ParseException {
		String t =  new SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(new Date());
//		System.out.println(t);
		model.addAttribute("time", t);
		return "time.jsp";
	}
}
