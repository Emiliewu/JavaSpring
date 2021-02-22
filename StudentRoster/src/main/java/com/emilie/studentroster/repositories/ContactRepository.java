package com.emilie.studentroster.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.emilie.studentroster.models.Contact;


@Repository
public interface ContactRepository extends CrudRepository<Contact, Long>{
	List<Contact> findAll();
	Optional<Contact> findById(Long id);
	Optional<Contact> findByStudent_id(Long id);
//	Contact update(Contact c);
}
