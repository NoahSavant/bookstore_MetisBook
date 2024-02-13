package com.metis.book;

import com.metis.book.dto.CategoryForm;
import com.metis.book.model.Category;
import com.metis.book.model.Image;
import com.metis.book.model.user.User;
import com.metis.book.repository.CategoryRepository;
import com.metis.book.repository.ImageRepository;
import com.metis.book.repository.UserRepository;
import com.metis.book.serviceImpl.CategoryServiceImpl;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ImageRepository imageRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    public void testInsert() throws IOException {
        CategoryForm categoryForm = new CategoryForm();
        categoryForm.setName("TestCategory");
        categoryForm.setFile(new MockMultipartFile("test.png", new byte[0]));

        Category savedCategory = new Category();
        savedCategory.setId(1L); // Set an ID for the saved category

        when(categoryRepository.save(any(Category.class))).thenReturn(savedCategory);
        when(imageRepository.save(any(Image.class))).thenReturn(new Image());

        categoryService.insert(categoryForm);

        // Add verification steps if needed
        verify(categoryRepository, times(2)).save(any(Category.class));
        verify(imageRepository, times(1)).save(any(Image.class));
        // Verify other interactions as required
    }

    @Test
    public void testGetCategoryShows() {
        long userId = 1L;
        String userName = "JohnDoe";

        User user = new User();
        user.setId(userId);
        user.setUsername(userName);

        Category category1 = new Category();
        category1.setId(1L);
        category1.setName("Category1");
        category1.setCreateBy(userId); // Set createBy as user ID
        category1.setUpdateBy(userId); // Set updateBy as user ID
        Date createdAt = new Date();
        Date updatedAt = new Date();
        category1.setCreatedAt(createdAt);
        category1.setUpdatedAt(updatedAt);

        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("Category2");
        category2.setCreatedAt(createdAt);
        category2.setUpdatedAt(updatedAt);

        List<Category> categories = new ArrayList<>();
        categories.add(category1);
        categories.add(category2);

        when(categoryRepository.findAll()).thenReturn(categories);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        List<CategoryForm> categoryForms = categoryService.getCategoryShows();

        assertEquals(2, categoryForms.size());

        CategoryForm categoryForm1 = categoryForms.get(0);
        assertEquals("1", categoryForm1.getId());
        assertEquals("Category1", categoryForm1.getName());
        assertEquals(userName, categoryForm1.getCreateBy());
        assertEquals(userName, categoryForm1.getLastUpdateBy());

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        assertEquals(formatter.format(createdAt), categoryForm1.getCreateDate());
        assertEquals(formatter.format(updatedAt), categoryForm1.getLastUpdateDate());

        CategoryForm categoryForm2 = categoryForms.get(1);
        assertEquals("2", categoryForm2.getId());
        assertEquals("Category2", categoryForm2.getName());
        assertEquals("", categoryForm2.getCreateBy());
        assertEquals("", categoryForm2.getLastUpdateBy());
        // Add assertions for the second category's properties

        // Verify interactions and assertions for the user lookup in
        // userRepository.findById
        verify(userRepository, times(2)).findById(userId);
    }
}
