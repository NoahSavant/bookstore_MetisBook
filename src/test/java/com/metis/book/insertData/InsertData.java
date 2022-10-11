package com.metis.book.insertData;

import java.time.LocalDate;
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
import com.metis.book.model.Cart;
import com.metis.book.model.CartItem;
import com.metis.book.model.Category;
import com.metis.book.model.Language;
import com.metis.book.model.Stock;
import com.metis.book.model.user.Address;
import com.metis.book.model.user.Role;
import com.metis.book.model.user.RoleName;
import com.metis.book.model.user.User;
import com.metis.book.repository.AddressRepository;
import com.metis.book.repository.AuthorRepository;
import com.metis.book.repository.BookRepository;
import com.metis.book.repository.CartItemReposirory;
import com.metis.book.repository.CartReposiroty;
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
	
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	CartReposiroty cartReposiroty;
	
	@Autowired
	CartItemReposirory cartItemReposirory;
	
	@Test
	@Order(1)
	public void testCreateAuthor() {
		
		// Create author
		Author author = new Author();
		author.setName("Nguyễn Nhật Ánh");
		author.setBooks(null);
		authorRepository.save(author);
	}
	
	
	@Test
	@Order(2)
	public void testCreateCategory() {
		
		// Create author
		Category category = new Category();
		category.setName("Tiểu thuyết");
		categoryRepository.save(category);
		
	}
	
	@Test
	@Order(3)
	public void testCreateLanguage() {
		Language language = new Language();
		language.setName("Tiếng Việt");
		languageRepository.save(language);
	}
	
	
	@Test
	@Order(4)
	public void testCreateBook() {
		
		// Get category
		Category category = categoryRepository.findByName("Tiểu Thuyết");
		if(Objects.isNull(category)) {
			log.error(AppConstant.CATEGORY_NOT_FOUND+ "Tiểu thuyết");
		}
		
		// Get language
		Language language = languageRepository.findByName("Tiếng Việt");
		if(Objects.isNull(language)) {
			log.error(AppConstant.LANGUAGE_NOT_FOUND+ "Tiếng Việt");
		}
		
		// Get Author
		Author author = authorRepository.findByName("Nguyễn Nhật Ánh");
		if(Objects.isNull(author)) {
			log.error(AppConstant.AUTHOR_NOT_FOUND+ "Nguyễn Nhật Ánh");
		}
		
		// Create new stock for Book1
		Stock stockForBook1 = new Stock();
		stockForBook1.setQuantiy(10);
		stockForBook1.setBook(null);
		Stock stockSaved1 = stockRepository.save(stockForBook1);
		
		// Create new stock for Book2
		Stock stockForBook2 = new Stock();
		stockForBook2.setQuantiy(5);
		stockForBook2.setBook(null);
		Stock stockSaved2 = stockRepository.save(stockForBook2);
		
		// Create Book 1
		Book book1 = Book.builder()
				.title("Tôi thấy hoa vàng trên cỏ xanh")
				.available(Boolean.TRUE)
				.category(category)
				.description("Một cuốn tiểu thuyết giành cho giới trẻ")
				.language(language)
				.publicationDate(null)
				.publisherName("Kim Đồng")
				.stock(stockSaved1)
				.authors(Arrays.asList(author))
				.build();
		bookRepository.save(book1);
		
		// Create Book 2
		
		Book book2 = Book.builder()
				.title("Mắt biếc")
				.available(Boolean.TRUE)
				.category(category)
				.description("Một cuốn tiểu thuyết chốn đồng quê")
				.language(language)
				.publicationDate(null)
				.publisherName("Kim Đồng")
				.stock(stockSaved2)
				.authors(Arrays.asList(author))
				.build();
		bookRepository.save(book2);
	}
	
	
	@Test
	@Order(5)
	public void testCreateRole() {
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
	public void testCreateUser() {
		createAdmin();
		createCustomer();
	}
	
	@Test
	@Order(7)
	public void testCreateAddress() {
		
		// get user admin
		User userAdmin = userRepository.findByUsername("khai");
		
		if(Objects.isNull(userAdmin)) {
			log.error(AppConstant.USER_NOT_FOUND+ "khai");
		}
		
		// Create new address for admin 
		Address address = new Address();
		address.setFullAddress("241 Nguyễn Trãi, Lái Thiêu, Thuận An, Bình Dương");
		address.setStreet("241 Nguyễn Trãi");
		address.setSubDistrict("Lái Thiêu");
		address.setDistrict("Thuận An");
		address.setProvince("Bình Dương");
		address.setIsPrimary(Boolean.TRUE);
		address.setUser(userAdmin);
		addressRepository.save(address);
		
		// get user customer
		User userCustomer = userRepository.findByUsername("kiet");
		
		if(Objects.isNull(userCustomer)) {
			log.error(AppConstant.USER_NOT_FOUND+ "kiet");
		}
		
		// Create new address for customer
		Address addressCustomer = new Address();
		addressCustomer.setFullAddress("168 Trương Văn Bang, Thạnh Mỹ Lợi, Thủ Đức, Hồ Chí Minh");
		addressCustomer.setStreet("168 Trương Văn Bang");
		addressCustomer.setSubDistrict("Thạnh Mỹ Lợi");
		addressCustomer.setDistrict("Thủ Đức");
		addressCustomer.setProvince("Hồ Chí Minh");
		addressCustomer.setIsPrimary(Boolean.TRUE);
		addressCustomer.setUser(userCustomer);
		addressRepository.save(addressCustomer);
	}
	
	@Test
	@Order(8)
	public void testCreateCartItem() {
		
		// get User
		User user = userRepository.findByUsername("kiet");
		if(Objects.isNull(user)) {
			log.error(AppConstant.USER_NOT_FOUND+ "kiet");
		}

		// get Cart
		Cart cart = user.getCart();
		
		// get Book 1
		Book book1 = bookRepository.findByTitle("Tôi thấy hoa vàng trên cỏ xanh");
		if(Objects.isNull(book1)) {
			log.error(AppConstant.BOOK_NOT_FOUND+" Tôi thấy hoa vàng trên cỏ xanh");
		}
		
		// get Book 2
		Book book2 = bookRepository.findByTitle("Mắt biếc");
		if(Objects.isNull(book2)) {
			log.error(AppConstant.BOOK_NOT_FOUND+ "Mắt biếc");
		}
		
		// Create cartItem1
		CartItem cartItem1 = new CartItem();
		cartItem1.setQuantity(1);
		cartItem1.setCart(cart);
		cartItem1.setBook(book1);
		cartItemReposirory.save(cartItem1);
		
		// Create cartItem2
		CartItem cartItem2 = new CartItem();
		cartItem2.setQuantity(3);
		cartItem2.setCart(cart);
		cartItem2.setBook(book2);
		cartItemReposirory.save(cartItem2);
		
	}
	
	public void createCustomer() {
		
		// Create new Cart
		Cart cart = new Cart();
		cart.setCartItems(null);
		cart.setUser(null);
		Cart cartSaved = cartReposiroty.save(cart);
		// get user role
		Role roleUser = roleRepository.findByName(RoleName.USER);
		
		if(Objects.isNull(roleUser)) {
			log.error(AppConstant.ROLE_NOT_FOUND+ "User");
		}
				
		User user = User.builder()
				.username("kiet")
				.password(passwordEncoder.encode("456"))
				.email("kietle1709@gmail.com")
				.firstName("kiet")
				.lastName("Le Nguyen Tuan")
				.birthday(LocalDate.of(2002,9,17))
				.gender(1) // 1: male, 2: female, 3: Not know
				.phoneNumber("01255145165")
				.enabled(Boolean.TRUE)
				.roles(Arrays.asList(roleUser))
				.cart(cartSaved)
				.addresses(null)
				.build();
		User userSaved = userRepository.save(user);

		// Map user to cart because user not exits
		userSaved.getCart().setUser(userSaved);

	}
	
	
	public void createAdmin() {
		
		// get admin role
		Role roleAdmin = roleRepository.findByName(RoleName.ADMIN);
		
		if(Objects.isNull(roleAdmin)) {
			log.error(AppConstant.ROLE_NOT_FOUND+" Admin");
		}
		
		// Create new Admin
		User user = User.builder()
				.username("khai")
				.password(passwordEncoder.encode("123"))
				.email("duckhailinux@gmail.com")
				.firstName("khai")
				.lastName("Nguyen")
				.birthday(LocalDate.of(2002,06,06))
				.gender(1) // 1: male, 2: female, 3: Not know
				.phoneNumber("0783511740")
				.enabled(Boolean.TRUE)
				.roles(Arrays.asList(roleAdmin))
				.cart(null)
				.addresses(null)
				.build();
		userRepository.save(user);
		
	}
	
	
}
