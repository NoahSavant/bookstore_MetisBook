package com.metis.book.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metis.book.model.Category;
import com.metis.book.model.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {

	Optional<Language> findByName(String string);

}
