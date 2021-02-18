package com.emilie.dojoninja.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.emilie.dojoninja.models.Dojo;
import com.emilie.dojoninja.models.Ninja;
import com.emilie.dojoninja.repositories.DojoRepository;
import com.emilie.dojoninja.repositories.NinjaRepository;

@Service
public class DNService {
	DojoRepository dRepo;
	NinjaRepository nRepo;
	
	public DNService(DojoRepository dRepo, NinjaRepository nRepo) {
		this.dRepo = dRepo;
		this.nRepo = nRepo;
	}
	
	//find all Ninjas
	public List<Ninja> findAllNinja() {
		return nRepo.findAll();
	}
	//find all Dojos
	public List<Dojo> findAllDojo() {
		return dRepo.findAll();
	}
	//find Ninja by ID
	public Ninja findNinjaById(Long id) {
		Optional<Ninja> optionalNinja = nRepo.findById(id);
		if(optionalNinja.isPresent()) {
			return optionalNinja.get();
		} else {
			return null;
		}
	}
	//find Dojo by ID
	public Dojo findDojoById(Long id) {
		Optional<Dojo> optionalDojo = dRepo.findById(id);
		if(optionalDojo.isPresent()) {
			return optionalDojo.get();
		} else {
			return null;
		}
	}
	// create a new ninja
	public Ninja createNinja(Ninja nj) {
		return nRepo.save(nj);
	}
	// create a new dojo
	public Dojo createDojo(Dojo d) {
		return dRepo.save(d);
	}
	// add Ninja to Dojo
	public Dojo addNinjaToDojo(Long dojo_id, Long ninja_id) {
		Optional<Dojo> dojo = dRepo.findById(dojo_id);
		Optional<Ninja> ninja = nRepo.findById(ninja_id);
		if(dojo.isPresent() & dojo.isPresent()) {
//			dojo.get().setDojo(dojo.get().getId());
			List<Ninja> ninjas = dojo.get().getNinjas();
			ninjas.add(ninja.get());
			dojo.get().setNinjas(ninjas);
			return dojo.get();
		} else {
			return null;
		}
	}
	
	// remove a ninja from dojo
	public Dojo removeNinjaFromDojo(Long dojo_id, Long ninja_id) {
		Optional<Dojo> dojo = dRepo.findById(dojo_id);
		Optional<Ninja> ninja = nRepo.findById(ninja_id);
		if(dojo.isPresent()) {
			List<Ninja> ninjas = dojo.get().getNinjas();
			if(ninjas.contains(ninja.get())) {
				ninjas.remove(ninja.get());
				dojo.get().setNinjas(ninjas);
			} 
			return dojo.get();
		} else {
			return null;
		}
	}

}
