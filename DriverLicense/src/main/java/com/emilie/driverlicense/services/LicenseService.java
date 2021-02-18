package com.emilie.driverlicense.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.emilie.driverlicense.models.License;
import com.emilie.driverlicense.repositories.LicenseRepository;


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
	
	public List<License> findByPerson(Long id) {
		List<License> infolist = lRepo.findByPerson(id);
		if(infolist.isEmpty()) {
			return null;
		} else {
			return infolist;
		}
	}
	
	public License createLicense(License l) {
		return lRepo.save(l);
	}
	
}