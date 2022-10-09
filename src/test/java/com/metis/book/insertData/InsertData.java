package com.metis.book.insertData;

import java.util.Arrays;
import java.util.Objects;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.metis.book.model.Author;
import com.metis.book.model.Book;
import com.metis.book.model.Category;
import com.metis.book.model.Language;
import com.metis.book.model.Stock;
import com.metis.book.model.user.Role;
import com.metis.book.model.user.RoleName;
import com.metis.book.model.user.User;
import com.metis.book.repository.AuthorRepository;
import com.metis.book.repository.BookRepository;
import com.metis.book.repository.CategoryRepository;
import com.metis.book.repository.LanguageRepository;
import com.metis.book.repository.RoleRepository;
import com.metis.book.repository.StockRepository;
import com.metis.book.repository.UserRepository;
import com.metis.book.utils.AppConstant;

import lombok.extern.slf4j.Slf4j;


@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
@Slf4j
@Rollback(false)
public class InsertData {

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	LanguageRepository languageRepository;
	
	@Autowired
	StockRepository stockRepository;
	
	@Test
	@Order(1)
	public void TestCreateAuthor() {
		
		// Create author
		Author author = new Author();
		author.setName("Nguyễn Nhật Ánh");
		author.setBooks(null);
		authorRepository.save(author);
	}
	
	
	@Test
	@Order(2)
	public void TestCreateCategory() {
		
		// Create author
		Category category = new Category();
		category.setName("Tiểu thuyết");
		categoryRepository.save(category);
		
	}
	
	@Test
	@Order(3)
	public void TestCreateLanguage() {
		Language language = new Language();
		language.setName("Tiếng Việt");
		languageRepository.save(language);
	}
	
	
	@Test
	@Order(4)
	public void TestCreateBook() {
		
		// Get category
		Category category = categoryRepository.findByName("Tiểu Thuyết").get();
		if(Objects.isNull(category)) {
			log.error(AppConstant.CATEGORY_NOT_FOUND+ "Tiểu thuyết");
		}
		
		// Get language
		Language language = languageRepository.findByName("Tiếng Việt").get();
		if(Objects.isNull(language)) {
			log.error(AppConstant.LANGUAGE_NOT_FOUND+ "Tiếng Việt");
		}
		
		// Get Author
		Author author = authorRepository.findByName("Nguyễn Nhật Ánh").get();
		if(Objects.isNull(author)) {
			log.error(AppConstant.AUTHOR_NOT_FOUND+ "Nguyễn Nhật Ánh");
		}
		
		// Create new stock
		Stock stock = new Stock();
		stock.setQuantiy(10);
		stock.setBook(null);
		Stock stockSaved = stockRepository.save(stock);
		
		// Create new Book
		Book book = Book.builder()
				.title("Tôi thấy hoa vàng trên cỏ xanh")
				.available(Boolean.TRUE)
				.category(category)
				.description("Một cuốn tiểu thuyết giành cho giới trẻ")
				.languages(Arrays.asList(language))
				.publicationDate(null)
				.publisherName("Kim Đồng")
				.stock(stockSaved)
				.authors(Arrays.asList(author))
				.build();
		bookRepository.save(book);
	}
	
	
	@Test
	@Order(5)
	public void TestCreateRole() {
		// Create role admin
		Role roleAdmin = new Role();
		roleAdmin.setName(RoleName.ADMIN);
		roleRepository.save(roleAdmin);
		
		//Create role user
		Role roleUser = new Role();
		roleUser.setName(RoleName.USER);
		roleRepository.save(roleUser);
		
		//Create role staff
		Role roleStaff = new Role();
		roleStaff.setName(RoleName.STAFF);
		roleRepository.save(roleStaff);	
	}
	
	
	@Test
	@Order(6)
	public void TestCreateUser() {
		
		Role roleAdmin = roleRepository.findByName(RoleName.ADMIN).get();
		
		if(Objects.isNull(roleAdmin)) {
			log.error(AppConstant.ROLE_NOT_FOUND+" Admin");
		}
		
		User user = User.builder()
				.username("Nguyen Duc Khai")
				.password(passwordEncoder.encode("123"))
				.enabled(Boolean.TRUE)
				.roles(Arrays.asList(roleAdmin))
				.cart(null)
				.profile(null)
				.build();
		userRepository.save(user);
	}
}
