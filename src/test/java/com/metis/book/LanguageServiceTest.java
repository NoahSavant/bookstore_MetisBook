package com.metis.book;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import com.metis.book.model.Language;
import com.metis.book.repository.LanguageRepository;
import com.metis.book.serviceImpl.LanguageServiceImpl;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(properties = "spring.config.name=application-test")
public class LanguageServiceTest {
    @Mock
    private LanguageRepository languageRepository;

    @InjectMocks
    private LanguageServiceImpl languageService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllLanguages() {
        // Arrange
        Language language1 = new Language(1L, "English", null);
        Language language2 = new Language(2L, "Spanish", null);
        
        List<Language> languages = new ArrayList<>();
        languages.add(language1);
        languages.add(language2);

        when(languageRepository.findAll()).thenReturn(languages);

        // Act
        List<Language> result = languageService.getAllLanguages();

        // Assert
        // Perform assertions based on the expected behavior
        // Verify that the findAll method of languageRepository is called once
        verify(languageRepository, times(1)).findAll();

        // Verify the size of the result list
        assertEquals(2, result.size());

        // Verify the content of the result list
        assertEquals(language1, result.get(0));
        assertEquals(language2, result.get(1));
    }
}
