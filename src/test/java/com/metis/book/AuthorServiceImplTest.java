package com.metis.book;

import com.metis.book.dto.AuthorForm;
import com.metis.book.model.Author;
import com.metis.book.repository.AuthorRepository;
import com.metis.book.repository.UserRepository;
import com.metis.book.serviceImpl.AuthorServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(properties = "spring.config.name=application-test")
public class AuthorServiceImplTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllAuthors() {
        when(authorRepository.findAll()).thenReturn(Collections.emptyList());
        assertEquals(Collections.emptyList(), authorService.getAllAuthors());
    }

    @Test
    public void testInsert() {
        AuthorForm authorForm = new AuthorForm();
        authorForm.setName("Nguyen Duc Thinh");

        authorService.insert(authorForm);

        verify(authorRepository, times(1)).save(any(Author.class));
    }

    @Test
    public void testGetById() {
        // Mock data
        Author author = new Author();
        author.setId(1L);
        author.setName("Nguyen Duc Thinh");

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        // Perform the test
        AuthorForm authorForm = authorService.getById(1L);
        assertEquals("1", authorForm.getId());
        assertEquals("Nguyen Duc Thinh", authorForm.getName());
    }

    @Test
    public void testUpdateAuthor() {
        // Mock data
        AuthorForm authorForm = new AuthorForm();
        authorForm.setId("1");
        authorForm.setName("Nguyen Duc Thinh");

        Author existingAuthor = new Author();
        existingAuthor.setId(1L);
        existingAuthor.setName("Nguyen Van Nam");

        when(authorRepository.findById(1L)).thenReturn(Optional.of(existingAuthor));

        // Perform the test
        authorService.updateAuthor(authorForm);

        verify(authorRepository, times(1)).save(existingAuthor);
        assertEquals("Nguyen Duc Thinh", existingAuthor.getName());
    }
}
