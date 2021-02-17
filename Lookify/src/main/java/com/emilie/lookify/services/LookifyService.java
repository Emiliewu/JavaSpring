package com.emilie.lookify.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.emilie.lookify.models.Lookify;
import com.emilie.lookify.repositories.LookifyRepository;

@Service
public class LookifyService {
	private final LookifyRepository lookifyRepo;
	
	public LookifyService(LookifyRepository lookifyRepo) {
		this.lookifyRepo = lookifyRepo;
	}
	
	public List<Lookify> allLookify() {
		return lookifyRepo.findAll();
	}
	public Lookify createLookify(Lookify lookify) {
		return lookifyRepo.save(lookify);
	}
	public Lookify findLookify(Long id) {
		Optional<Lookify> optionalL = lookifyRepo.findById(id);
		if(optionalL.isPresent()) {
			return optionalL.get();
		} else {
			return null;
		}
	}
	public Lookify updateLookify(Lookify l) {
		return lookifyRepo.save(l);
	}
	public Lookify deleteLookify(Long id) {
		Lookify l = this.findLookify(id);
		lookifyRepo.deleteById(id);
		return l;
	}
	public List<Lookify> findAllByRating(){
		return lookifyRepo.findTop10ByOrderByRatingDesc();
	}
	public List<Lookify> findArtist(String searchString){
		return lookifyRepo.findByArtistContainingIgnoreCase(searchString);
	}
}
