package com.emilie.languages.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.emilie.languages.models.Language;
import com.emilie.languages.repositories.LanguageRepository;

@Service
public class LanguageService {
	private final LanguageRepository languageRepo;
	public LanguageService(LanguageRepository languageRepo) {
		this.languageRepo = languageRepo;
	}
		
	public List<Language> allLanguages() {
		return languageRepo.findAll();
	}
	public Language createLanguage(Language language) {
		return languageRepo.save(language);
	}
	public Language findLanguage(Long id) {
		Optional<Language> optionalLang = languageRepo.findById(id);
		if(optionalLang.isPresent()) {
			return optionalLang.get();
		} else {
			return null;
		}
	}
	public Language updateLang(Language l) {
		return languageRepo.save(l);
	}
	public void deleteLang(Long id) {
		languageRepo.deleteById(id);
	}

}
