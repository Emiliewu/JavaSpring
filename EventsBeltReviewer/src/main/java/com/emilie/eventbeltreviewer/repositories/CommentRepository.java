package com.emilie.eventbeltreviewer.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.emilie.eventbeltreviewer.models.Comment;
import com.emilie.eventbeltreviewer.models.Event;
import com.emilie.eventbeltreviewer.models.User;

public interface CommentRepository extends CrudRepository<Comment, Long>{
	List<Comment> findAll();
	List<Comment> findByEvent(Event event);
	List<Comment> findByUser(User user);
	List<Comment> findByUserNot(User user);
}
