package com.metis.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metis.book.model.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {

	Language findByName(String string);

}
