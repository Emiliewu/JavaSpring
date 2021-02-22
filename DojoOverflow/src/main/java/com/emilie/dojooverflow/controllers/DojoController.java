package com.emilie.dojooverflow.controllers;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.emilie.dojooverflow.models.Answer;
import com.emilie.dojooverflow.models.Question;
import com.emilie.dojooverflow.models.Tag;
import com.emilie.dojooverflow.services.DojoService;

@Controller
public class DojoController {
	@Autowired
	private final DojoService djservice;
	
	public DojoController(DojoService djservice) {
		this.djservice = djservice;
	}
	
	//direct root to questions
	@RequestMapping("/")
	public String root() {
		return "redirect:/questions";
	}
	
	//all questions page
	@RequestMapping("/questions")
	public String allquestion(Model model) {
		List<Question> questions = djservice.findAllQuestion();
		model.addAttribute("questions", questions);
		return "allquestion.jsp";
	}
	
	//new question page
	@RequestMapping("/questions/new")
	public String newQuestion() {
		return "newquestion.jsp";
	}
	
	//create a question with tag
	@RequestMapping(value="/questions/create", method=RequestMethod.POST)
	public String createQuestion(@RequestParam("question") String question, @RequestParam("tag") String tagstr, RedirectAttributes redirectAttributes) {
		//validation for empty input
		if (question.trim().isEmpty()) {
			redirectAttributes.addFlashAttribute("error", "Question cannot be empty");
			return "redirect:/questions/new";
		} else if (tagstr.isEmpty()) {
			redirectAttributes.addFlashAttribute("error", "Tags cannot be empty");
			return "redirect:/questions/new";
		} else {
			String[] list = tagstr.split(",");
			System.out.println(Arrays.toString(list));
			//validation for tag length
			if(list.length > 3) {
				redirectAttributes.addFlashAttribute("error", "Add no more than 3 tags");
				return "redirect:/questions/new";
			} else {

				boolean isValid = true;
				for(String t: list) {
					//validation for UPPERCASE of each tag
					if(!t.trim().isEmpty()) {
						char[] charArray = t.toCharArray();
						for(int i=0; i < charArray.length; i++) {
							//if any character is in upper case, return error
							if( Character.isUpperCase( charArray[i] )) {
								redirectAttributes.addFlashAttribute("error", "Tags must be lower case");
								return "redirect:/questions/new";
							}
						}
						isValid = true;
					} else {
						isValid = false;
					}
				}
				// validate if all the tags are spaces
				if(isValid == false) {
					redirectAttributes.addFlashAttribute("error", "Tags can not be spaces");
					return "redirect:/questions/new";
				} else {
					// add to database if pass all validations above
					Question thequestion = new Question(question.trim());
					djservice.createQuestion(thequestion);						
					for(String t: list) {
						//if the tag exists in the database, add it directly, otherwise, create it and add it
						if(djservice.isTagExists(t.trim())==false) {
							djservice.createTagbyString(t.trim());
							Long thetagid = djservice.findTagByTagName(t.trim()).getId();
							djservice.addTagToQuestion(thetagid, thequestion.getId());
						} else {	
							Long thetagid = djservice.findTagByTagName(t.trim()).getId();

							djservice.addTagToQuestion(thetagid, thequestion.getId());
						}
					}
				
				}
			}
		}
		return "redirect:/questions/new";
	}
	
	
	//question detail page
	@RequestMapping("/questions/{question_id}")
	public String questionDetail(Model model, @PathVariable("question_id") Long question_id) {
		Question question = djservice.findQuestionById(question_id);
		model.addAttribute("question", question);		
		return "questiondetail.jsp";
	}
	
	//create answer and add to question
	@RequestMapping("/questions/{question_id}/answer")
	public String addAnswer(Model model, @PathVariable("question_id") Long question_id, @RequestParam("answer") String answer, RedirectAttributes redirectAttributes) {
		if (answer.trim().isEmpty()) {
			redirectAttributes.addFlashAttribute("error", "Answer cannot be empty!");
			return "redirect:/questions/" + question_id;
		} else {
			Answer theanswer = new Answer();
			theanswer.setAnswer(answer);
			theanswer.setQuestion(djservice.findQuestionById(question_id));
			djservice.createAnswer(theanswer);
			return "redirect:/questions/" + question_id;
		}
	}
}
