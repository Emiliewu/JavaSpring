package com.emilie.dojooverflow.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.emilie.dojooverflow.models.Answer;
import com.emilie.dojooverflow.models.Question;
import com.emilie.dojooverflow.models.Tag;
import com.emilie.dojooverflow.repositories.AnswerRepository;
import com.emilie.dojooverflow.repositories.QuestionRepository;
import com.emilie.dojooverflow.repositories.TagRepository;

@Service
public class DojoService {
	private final QuestionRepository qRepo;
	private final AnswerRepository aRepo;
	private final TagRepository tRepo;
	
	public DojoService(QuestionRepository qRepo, AnswerRepository aRepo, TagRepository tRepo) {
		this.aRepo = aRepo;
		this.qRepo = qRepo;
		this.tRepo = tRepo;
	}
	//find All Questions
	public List<Question> findAllQuestion(){
		return qRepo.findAll();
	}
	
	//find Question by Id
	public Question findQuestionById(Long id) {
		Optional<Question> question = qRepo.findById(id);
		if(question.isPresent()) {
			return question.get();
		} else {
			return null;
		}
	}
	//find all tags
	public List<Tag> findAllTag(){
		return tRepo.findAll();
	}
	// if tag exists
	public boolean isTagExists(String searchString) {
		return tRepo.existsByTag(searchString);
	}
	//find tag by ID
	public Tag findTagById(Long id) {
		Optional<Tag> tag = tRepo.findById(id);
		if(tag.isPresent()) {
			return tag.get();
		} else {
			
			return null;
		}
	}
	//find tag by Tag name
	public Tag findTagByTagName(String searchString) {
//		List<Tag> tags = tRepo.findByTagContainingIgnoreCase(searchString);
//		if(tags != null) {
//			return tags.get(0);
//		} else {
//			return null;
//		}
		Optional<Tag> tag = tRepo.findByTagContaining(searchString);
		if(tag.isPresent()) {
			return tag.get();
		} else {
			return null;
		}
	}
	
	//Create a Tag by tag name
	public Tag createTagbyString(String t) {
		Tag tag = new Tag();
		tag.setTag(t);
		return tRepo.save(tag);
	}
	
	//Create a Question
	public Question createQuestion(Question q) {
		return qRepo.save(q);
	}
	
	//Add tag to Question
	public Question addTagToQuestion(Long tag_id, Long question_id) {
		Optional<Question> question = qRepo.findById(question_id);
		Optional<Tag> tag = tRepo.findById(tag_id);
		List<Tag> tlist;
		if(question.get().getTags() != null) {
			 tlist= question.get().getTags();
		} else {
			tlist = new ArrayList<Tag>();
		}
		tlist.add(tag.get());
		question.get().setTags(tlist);
		qRepo.save(question.get());
		return question.get();
	}
	
	//create a answer
	public Answer createAnswer(Answer a) {
		return aRepo.save(a);
	}
	//find all answers
	public List<Answer> findAllAnswer() {
		return aRepo.findAll();
	}
	
	//add answer to question
	public Question addAnswerToQuestion(Long answer_id, Long question_id) {
		Optional<Question> question = qRepo.findById(question_id);
		Optional<Answer> answer = aRepo.findById(answer_id);
		List<Answer> alist = question.get().getAnswers();
		alist.add(answer.get());
		question.get().setAnswers(alist);
		qRepo.save(question.get());
		return question.get();
	}
	//add question to answer
	public Answer addQuestionToAnswer(Long answer_id, Long question_id) {
		Optional<Answer> answer = aRepo.findById(answer_id);
		answer.get().setQuestion(qRepo.findById(question_id).get());
		return aRepo.save(answer.get());
	}
	
}
