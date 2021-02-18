package com.emilie.driverlicense.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.emilie.driverlicense.models.Person;
import com.emilie.driverlicense.repositories.PersonRepository;



@Service
public class PersonService {
	private final PersonRepository pRepo;
	
	public PersonService(PersonRepository pRepo) {
		this.pRepo = pRepo;
	}
	
	public List<Person> allPerson() {
		return pRepo.findAll();
	}
	
	public Person createPerson(Person p) {
		return pRepo.save(p);
	}
	
	public Person findById(Long id) {
		Optional<Person> optionalP = pRepo.findById(id);
		if(optionalP.isPresent()) {
			return optionalP.get();
		} else {
			return null;
		}
	}
}
