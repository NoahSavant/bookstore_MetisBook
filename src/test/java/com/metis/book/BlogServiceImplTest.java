package com.metis.book;

import com.metis.book.dto.BlogForm;
import com.metis.book.model.Blog;
import com.metis.book.model.Book;
import com.metis.book.model.Image;
import com.metis.book.repository.BlogRepository;
import com.metis.book.repository.BookRepository;
import com.metis.book.repository.ImageRepository;
import com.metis.book.serviceImpl.BlogServiceImpl;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class BlogServiceImplTest {

    @Mock
    private BlogRepository blogRepository;

    @Mock
    private ImageRepository imageRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BlogServiceImpl blogService;

    @Test
    public void testGetLatestBlogs() {
        when(blogRepository.findAll()).thenReturn(Collections.emptyList());
        assertEquals(new ArrayList<>(), blogService.getLatestBlogs());
    }

    @Test
    public void testGetById() {
        Blog blog = new Blog();
        when(blogRepository.findById(blog.getId())).thenReturn(Optional.of(blog));
        assertEquals(blog, blogService.getById(blog.getId()));
    }

    @Test
    public void testAddBlog_WithBook() throws IOException {
        BlogForm blogForm = new BlogForm();
        blogForm.setTitle("Test Title");
        blogForm.setSubTitle("Test Subtitle");
        blogForm.setContent("Test Content");
        blogForm.setBook("1"); // Assuming book ID is "1"
        blogForm.setFile(new MockMultipartFile("test.png", new byte[0]));

        Blog blog = new Blog();
        blog.setTitle(blogForm.getTitle());
        blog.setSubTitle(blogForm.getSubTitle());
        blog.setContent(blogForm.getContent());

        when(blogRepository.save(any(Blog.class))).thenReturn(blog);
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(new Book()));
        when(imageRepository.save(any(Image.class))).thenReturn(new Image());

        blogService.addBlog(blogForm);

        // Verify save method on blogRepository was called with the blog object
        verify(blogRepository, times(1)).save(blog);

        // Verify findById method on bookRepository was called when a book was selected
        verify(bookRepository, times(1)).findById(anyLong());

        // Verify save method on imageRepository was called when a file was provided
        verify(imageRepository, times(1)).save(any(Image.class));

    }

    @Test
    public void testAddBlog_WithoutBook() throws IOException {
        BlogForm blogForm = new BlogForm();
        blogForm.setTitle("Test Title");
        blogForm.setSubTitle("Test Subtitle");
        blogForm.setContent("Test Content");
        blogForm.setBook("-1"); // No book selected
        blogForm.setFile(new MockMultipartFile("test.png", new byte[0]));

        Blog blog = new Blog();
        blog.setTitle(blogForm.getTitle());
        blog.setSubTitle(blogForm.getSubTitle());
        blog.setContent(blogForm.getContent());

        when(blogRepository.save(any(Blog.class))).thenReturn(blog);
        when(imageRepository.save(any(Image.class))).thenReturn(new Image());

        blogService.addBlog(blogForm);

        verify(blogRepository, times(1)).save(blog);

    }

    @Test
    public void testUpdateBlog() throws IOException {
        BlogForm blogForm = new BlogForm();
        blogForm.setId("1"); // Assuming blog ID to update is "1"
        blogForm.setTitle("Updated Title");
        blogForm.setSubTitle("Updated Subtitle");
        blogForm.setContent("Updated Content");
        blogForm.setFile(new MockMultipartFile("test.png", new byte[0]));

        Blog blog = new Blog();
        blog.setId(1L); // Same ID as the blog being updated
        blog.setTitle("Old Title");
        blog.setSubTitle("Old Subtitle");
        blog.setContent("Old Content");

        when(blogRepository.findById(anyLong())).thenReturn(Optional.of(blog));
        when(blogRepository.save(any(Blog.class))).thenReturn(blog);
        when(imageRepository.save(any(Image.class))).thenReturn(new Image());

        blogService.updateBlog(blogForm);

        verify(blogRepository, times(1)).findById(anyLong());
        // Verify that blogRepository.save is called twice during the updateBlog method
        verify(blogRepository, times(2)).save(any(Blog.class));
        verify(imageRepository, times(1)).save(any(Image.class));
    }

}
