package com.metis.book.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metis.book.model.Language;
import com.metis.book.repository.LanguageRepository;
import com.metis.book.service.ILanguageService;

@Service
public class LanguageServiceImpl implements ILanguageService {
	
	@Autowired
	LanguageRepository languageRepository;

	@Override
	public List<Language> getAllLanguages() {
		return languageRepository.findAll(); 
	}

}
