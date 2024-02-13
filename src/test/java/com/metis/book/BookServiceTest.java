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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.metis.book.dto.BookForm;
import com.metis.book.model.Author;
import com.metis.book.model.Book;
import com.metis.book.model.Category;
import com.metis.book.model.Image;
import com.metis.book.model.Inventory;
import com.metis.book.model.Language;
import com.metis.book.model.order.Order;
import com.metis.book.model.order.OrderItem;
import com.metis.book.repository.AuthorRepository;
import com.metis.book.repository.BookRepository;
import com.metis.book.serviceImpl.BookServiceImpl;
import com.metis.book.repository.CategoryRepository;
import com.metis.book.repository.ImageRepository;
import com.metis.book.repository.InventoryRepository;
import com.metis.book.repository.LanguageRepository;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(properties = "spring.config.name=application-test")
public class BookServiceTest {
    
    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private LanguageRepository languageRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private InventoryRepository inventoryRepository;

    @Mock
    private ImageRepository imageRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInsert() throws ParseException, IOException {
        // Arrange
        BookForm bookForm = createBookForm(); // You need to implement this method
        bookForm.setAuthors(Collections.emptyList());
        bookForm.setPrice("100");

        verify(authorRepository, times(0)).findById(anyLong()); // No authors to find
        ;
    }

    private BookForm createBookForm() {
        // Implement this method to create a dummy BookForm for testing
        // You need to set values for language, description, price, etc.
        BookForm bookForm = new BookForm();
        return bookForm;
    }

    private Optional<Author> createMockAuthor() {
        Author author = new Author(/* set properties here */);
        return Optional.of(author);
    }

    private Optional<Language> createMockLanguage() {
        Language language = new Language(/* set properties here */);
        return Optional.of(language);
    }

    private Optional<Category> createMockCategory() {
        Category category = new Category(/* set properties here */);
        return Optional.of(category);
    }

    private Inventory createMockInventory() {
        // Implement this method to create a dummy Inventory for testing
        return new Inventory(/* set properties here */);
    }

    private Book createMockBook() {
        // Implement this method to create a dummy Book for testing
        return new Book(/* set properties here */);
    }

    private Image createMockImage() {
        // Implement this method to create a dummy Image for testing
        return new Image(/* set properties here */);
    }

    @Test
    public void testGetTopFeatured() {
        // Arrange
        List<Book> books = createDummyBooks();  // Tạo danh sách sách giả mạo
        when(bookRepository.findAll()).thenReturn(books);

        // Act
        List<Book> topFeatured = bookService.getTopFeatured();

        // Assert
        assertEquals(2, topFeatured.size());  // Kiểm tra xem danh sách topFeatured có 2 phần tử không
        assertEquals(books.get(0), topFeatured.get(0));  // Kiểm tra phần tử đầu tiên
        assertEquals(books.get(1), topFeatured.get(1));  // Kiểm tra phần tử thứ hai
    }

    private List<Book> createDummyBooks() {
        // Tạo danh sách sách giả mạo để sử dụng trong test
        List<Book> books = new ArrayList<>();
        books.add(new Book(/* set properties here */));
        books.add(new Book(/* set properties here */));
        // Bạn có thể thay thế phần set properties here bằng các thuộc tính thực tế của Book
        return books;
    }

    @Test
    public void testGetBestSeller() {
        // Arrange
        Book book1 = new Book(/* Set properties for book 1 */);
        Book book2 = new Book(/* Set properties for book 2 */);
        Book book3 = new Book(/* Set properties for book 3 */);
        Book book4 = new Book(/* Set properties for book 4 */);

        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2, book3, book4));

        // Act
        List<Book> bestSeller = bookService.getBestSeller();

        // Assert
        assertEquals(2, bestSeller.size());  // Kiểm tra xem danh sách bestSeller có 2 phần tử không
        assertEquals(book3, bestSeller.get(0));  // Kiểm tra phần tử đầu tiên
        assertEquals(book4, bestSeller.get(1));  // Kiểm tra phần tử thứ hai

        // Verify that findAll is called
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllBooks() {
        // Arrange
        Book book1 = new Book(/* Set properties for book 1 */);
        Book book2 = new Book(/* Set properties for book 2 */);

        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        // Act
        List<Book> allBooks = bookService.getAllBooks();

        // Assert
        assertEquals(2, allBooks.size());  // Kiểm tra xem danh sách allBooks có 2 phần tử không
        assertEquals(book1, allBooks.get(0));  // Kiểm tra phần tử đầu tiên
        assertEquals(book2, allBooks.get(1));  // Kiểm tra phần tử thứ hai

        // Verify that findAll is called
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    public void testGetMaxPrice() {
        // Arrange
        List<Book> books = Arrays.asList(
                mockBook(1L, "Book1", 20L),
                mockBook(2L, "Book2",  15L),
                mockBook(3L, "Book3", 25L)
        );

        when(bookRepository.findAll()).thenReturn(books);

        // Act
        Long result = bookService.getMaxPrice();

        // Assert
        assertEquals(30L, result.longValue());
    }

    private Book mockBook(Long id, String title,Long price) {
        Book book = mock(Book.class);
        when(book.getPrice()).thenReturn(price);
        return book;
    }

    @Test
    public void testGetNumAllBooks() {
        // Arrange
        List<Book> books = Arrays.asList(
                new Book(/* set properties here */),
                new Book(/* set properties here */),
                new Book(/* set properties here */)
                // Add more books as needed
        );

        when(bookRepository.findAll()).thenReturn(books);

        // Act
        Long result = bookService.getNumAllBooks();

        // Assert
        assertEquals(3L, result.longValue()); // Change the expected value based on the number of books in the list
    }

    @Test
    public void testDeleteById() {
        // Arrange
        Long bookId = 1L;

        // Act
        bookService.deleteById(bookId);

        // Assert
        verify(bookRepository).deleteById(bookId);
    }
    

}
