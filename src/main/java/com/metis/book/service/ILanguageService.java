package com.metis.book.service;

import java.util.List;

import javax.validation.Valid;

import com.metis.book.dto.LanguageForm;
import com.metis.book.model.Language;

public interface ILanguageService {

	List<Language> getAllLanguages();

	void insert(@Valid LanguageForm language);

    List<LanguageForm> getLanguageShows();

	LanguageForm getById(long parseLong);

	void updateLanguage(LanguageForm languageForm);
}
