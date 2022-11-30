package com.metis.book.serviceImpl;

import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import com.metis.book.dto.CategoryForm;
import com.metis.book.model.Category;
import com.metis.book.model.Image;
import com.metis.book.repository.UserRepository;
import com.metis.book.utils.AppConstant;
import com.metis.book.utils.FileUploadUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metis.book.dto.LanguageForm;
import com.metis.book.model.Language;
import com.metis.book.repository.LanguageRepository;
import com.metis.book.service.ILanguageService;

@Service
@Slf4j
public class LanguageServiceImpl implements ILanguageService {
	
	@Autowired
	LanguageRepository languageRepository;
	@Autowired
	UserRepository userRepository;
	@Override
	public List<Language> getAllLanguages() {
		return languageRepository.findAll(); 
	}

	@Override
	public void insert(@Valid LanguageForm languageForm) {
		Language language = new Language();
		language.setName(languageForm.getName());
		languageRepository.save(language);
		
	}

	@Override
	public List<LanguageForm> getLanguageShows() {
		List<Language> languages = languageRepository.findAll();
		List<LanguageForm> languageForms = new ArrayList<>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		for (Language language: languages) {
			LanguageForm languageForm = new LanguageForm();
			languageForm.setId(language.getId().toString());
			languageForm.setName(language.getName());
			if (language.getCreateBy()!=null){
				languageForm.setCreateBy(userRepository.findById(language.getCreateBy()).get().getUsername());
			} else {
				languageForm.setCreateBy("");
			}
			if (language.getUpdateBy()!=null){
				languageForm.setLastUpdateBy(userRepository.findById(language.getUpdateBy()).get().getUsername());
			} else {
				languageForm.setLastUpdateBy("");
			}
			languageForm.setCreateDate(formatter.format(language.getCreatedAt()));
			languageForm.setLastUpdateDate(formatter.format(language.getUpdatedAt()));
			languageForms.add(languageForm);
		}
		return languageForms;
	}

	@Override
	public LanguageForm getById(long id) {
		Language language = languageRepository.findById(id).get();
		LanguageForm languageForm = new LanguageForm();
		if (Objects.isNull(language)) {
			log.error(AppConstant.LANGUAGE_NOT_FOUND + " " + id);
		}
		languageForm.setId(language.getId().toString());
		languageForm.setName(language.getName());
		return languageForm;
	}

	@Override
	public void updateLanguage(LanguageForm languageForm) {
		Language language = languageRepository.findById(Long.parseLong(languageForm.getId())).get();
		if (Objects.isNull(language)) {
			log.error(AppConstant.LANGUAGE_NOT_FOUND + " " + languageForm.getId());
		}
		language.setName(languageForm.getName());
		languageRepository.save(language);

	}

}
