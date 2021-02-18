package com.emilie.relationship.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.emilie.relationship.models.License;
import com.emilie.relationship.repositories.LicenseRepository;

@Service
public class LicenseService {
	private final LicenseRepository lRepo;
	
	public LicenseService(LicenseRepository lRepo) {
		this.lRepo = lRepo;
	}
	
	public List<License> findAll() {
		return lRepo.findAll();
	}
	
	public License findById(Long id) {
		Optional<License> optionalL = lRepo.findById(id);
		if (optionalL.isPresent()) {
			return optionalL.get();
		} else {
			return null;
		}
	}
	
	public License findByPerson(Long id) {
		Optional<License> infolist = lRepo.findByPerson_id(id);
		if(infolist.isPresent()) {
			return infolist.get();
		} else {
			return null;
		}
	}
	
	public License createLicense(License l) {
		lRepo.save(l);
		Long num = l.getId();
		num += 1;
		String str = num.toString();
		int zeros = 6 - str.length();
		for(int i = 0; i < zeros; i++) {
			str = "0" + str;
		}
		l.setNumber(str);
		return lRepo.save(l);
	}
	public License updateLicense(License l) {
		Long person_id = l.getPerson().getId();
		Optional<License> optionalLicense = lRepo.findByPerson_id(person_id);
		if(optionalLicense.isPresent()) {
			License thisLicense = optionalLicense.get();
			thisLicense.setExpirationDate(l.getExpirationDate());
			thisLicense.setState(l.getState());

			return lRepo.save(thisLicense);
		} else {
			return null;
		}
	}
	
}
