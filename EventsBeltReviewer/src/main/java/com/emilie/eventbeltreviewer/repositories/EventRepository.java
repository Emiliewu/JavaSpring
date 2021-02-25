package com.emilie.eventbeltreviewer.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.emilie.eventbeltreviewer.models.Event;


public interface EventRepository extends CrudRepository<Event, Long> {
	List<Event> findAll();
	List<Event> findByStateContaining(String state);
	List<Event> findByStateNot(String state);
	boolean existsByHost_id(Long Host_id);
}
