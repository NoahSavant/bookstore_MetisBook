package com.metis.book.insertData;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
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
import com.metis.book.model.Blog;
import com.metis.book.model.Book;
import com.metis.book.model.Cart;
import com.metis.book.model.CartItem;
import com.metis.book.model.Category;
import com.metis.book.model.Image;
import com.metis.book.model.Inventory;
import com.metis.book.model.Language;
import com.metis.book.model.order.OrderItem;
import com.metis.book.model.order.OrderTrack;
import com.metis.book.model.user.Address;
import com.metis.book.model.user.Role;
import com.metis.book.model.user.RoleName;
import com.metis.book.model.user.User;
import com.metis.book.repository.AddressRepository;
import com.metis.book.repository.AuthorRepository;
import com.metis.book.repository.BlogRepository;
import com.metis.book.repository.BookRepository;
import com.metis.book.repository.CartItemReposirory;
import com.metis.book.repository.CartReposiroty;
import com.metis.book.repository.CategoryRepository;
import com.metis.book.repository.ImageRepository;
import com.metis.book.repository.InventoryRepository;
import com.metis.book.repository.LanguageRepository;
import com.metis.book.repository.OrderItemRepository;
import com.metis.book.repository.OrderRepository;
import com.metis.book.repository.OrderTrackRepository;
import com.metis.book.repository.RoleRepository;
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
	InventoryRepository inventoryRepository;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	CartReposiroty cartReposiroty;

	@Autowired
	CartItemReposirory cartItemReposirory;

	@Autowired
	ImageRepository imageRepository;

	@Autowired
	OrderTrackRepository orderTrackRepository;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrderItemRepository orderItemRepository;

	@Autowired
	BlogRepository blogRepository;
	
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

		// Create category
		Category category1 = new Category();
		category1.setName("Tiểu thuyết");
		categoryRepository.save(category1);

		Category category2 = new Category();
		category2.setName("Ngôn tình");
		categoryRepository.save(category2);

		Category category3 = new Category();
		category3.setName("Trinh thám");
		categoryRepository.save(category3);

		Category category4 = new Category();
		category4.setName("Cung đấu");
		categoryRepository.save(category4);
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
		Category categoryTieuThuyet = categoryRepository.findByName("Tiểu Thuyết");
		if (Objects.isNull(categoryTieuThuyet)) {
			log.error(AppConstant.CATEGORY_NOT_FOUND + "Tiểu thuyết");
		}

		Category categoryNgonTinh = categoryRepository.findByName("Ngôn tình");
		if (Objects.isNull(categoryTieuThuyet)) {
			log.error(AppConstant.CATEGORY_NOT_FOUND + "Ngôn tình");
		}

		Category categoryCungDau = categoryRepository.findByName("Cung đấu");
		if (Objects.isNull(categoryCungDau)) {
			log.error(AppConstant.CATEGORY_NOT_FOUND + "Cung đấu");
		}

		// Get language
		Language language = languageRepository.findByName("Tiếng Việt");
		if (Objects.isNull(language)) {
			log.error(AppConstant.LANGUAGE_NOT_FOUND + "Tiếng Việt");
		}

		// Get Author
		Author author = authorRepository.findByName("Nguyễn Nhật Ánh");
		if (Objects.isNull(author)) {
			log.error(AppConstant.AUTHOR_NOT_FOUND + "Nguyễn Nhật Ánh");
		}

		// Create new inventory for Book1
		Inventory inventoryForBook1 = new Inventory();
		inventoryForBook1.setQuantiy(10);
		inventoryForBook1.setBook(null);
		Inventory inventorySaved1 = inventoryRepository.save(inventoryForBook1);

		// Create new inventory for Book2
		Inventory inventoryForBook2 = new Inventory();
		inventoryForBook2.setQuantiy(5);
		inventoryForBook2.setBook(null);
		Inventory inventorySaved2 = inventoryRepository.save(inventoryForBook2);

		// Create new inventory for Book3
		Inventory inventoryForBook3 = new Inventory();
		inventoryForBook3.setQuantiy(5);
		inventoryForBook3.setBook(null);
		Inventory inventorySaved3 = inventoryRepository.save(inventoryForBook3);

		// Create new inventory for Book4
		Inventory inventoryForBook4 = new Inventory();
		inventoryForBook4.setQuantiy(5);
		inventoryForBook4.setBook(null);
		Inventory inventorySaved4 = inventoryRepository.save(inventoryForBook4);

		// Create Book 1
		Book book1 = Book.builder().title("Tôi thấy hoa vàng trên cỏ xanh").available(Boolean.TRUE)
				.price(50000L)
				.category(categoryTieuThuyet).description("Một cuốn tiểu thuyết giành cho giới trẻ").language(language)
				.publicationDate(null).publisherName("Kim Đồng").inventory(inventorySaved1)
				.authors(Arrays.asList(author)).build();
		bookRepository.save(book1);

		// Create Book 2

		Book book2 = Book.builder().title("Mắt biếc").available(Boolean.TRUE).category(categoryTieuThuyet)
				.price(45000L)
				.description("Một cuốn tiểu thuyết chốn đồng quê").language(language).publicationDate(null)
				.publisherName("Kim Đồng").inventory(inventorySaved2).authors(Arrays.asList(author)).build();
		bookRepository.save(book2);

		// Create Book 3

		Book book3 = Book.builder().title("Game of throne").available(Boolean.TRUE).category(categoryCungDau)
				.price(58000L)
				.description("Một cuốn sách về cung đấu").language(language).publicationDate(null)
				.publisherName("Phụ nữ").inventory(inventorySaved3).authors(Arrays.asList(author)).build();
		bookRepository.save(book3);

		// Create Book 4

		Book book4 = Book.builder().title("Your Name").available(Boolean.TRUE).category(categoryNgonTinh)
				.price(26000L)
				.description("Một cuốn sách ngôn tình").language(language).publicationDate(null)
				.publisherName("Kadokawa").inventory(inventorySaved4).authors(Arrays.asList(author)).build();
		bookRepository.save(book4);
	}

	@Test
	@Order(5)
	public void testCreateRole() {
		// Create role admin
		Role roleAdmin = new Role();
		roleAdmin.setName(RoleName.ADMIN);
		roleRepository.save(roleAdmin);

		// Create role user
		Role roleUser = new Role();
		roleUser.setName(RoleName.USER);
		roleRepository.save(roleUser);

		// Create role staff
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

		if (Objects.isNull(userAdmin)) {
			log.error(AppConstant.USER_NOT_FOUND + "khai");
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

		if (Objects.isNull(userCustomer)) {
			log.error(AppConstant.USER_NOT_FOUND + "kiet");
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
		if (Objects.isNull(user)) {
			log.error(AppConstant.USER_NOT_FOUND + "kiet");
		}

		// get Cart
		Cart cart = user.getCart();

		// get Book 1
		Book book1 = bookRepository.findByTitle("Tôi thấy hoa vàng trên cỏ xanh");
		if (Objects.isNull(book1)) {
			log.error(AppConstant.BOOK_NOT_FOUND + " Tôi thấy hoa vàng trên cỏ xanh");
		}

		// get Book 2
		Book book2 = bookRepository.findByTitle("Mắt biếc");
		if (Objects.isNull(book2)) {
			log.error(AppConstant.BOOK_NOT_FOUND + "Mắt biếc");
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

	@Test
	@Order(9)
	public void testCreateOrderTrack() {

		// Delivery
		OrderTrack trackDelivering = new OrderTrack();
		trackDelivering.setStatus("Đang giao");
		orderTrackRepository.save(trackDelivering);

		// Preparing
		OrderTrack trackPreparing = new OrderTrack();
		trackPreparing.setStatus("Đang chuẩn bị");
		orderTrackRepository.save(trackPreparing);

		// Completed
		OrderTrack trackCompleted = new OrderTrack();
		trackCompleted.setStatus("Đã giao");
		orderTrackRepository.save(trackCompleted);

	}

	@Test
	@Order(10)
	public void testCreateOrder() {

		// get order track
		OrderTrack trackDelivering = orderTrackRepository.findByStatus("Đang giao");
		
		// Create order1
		com.metis.book.model.order.Order order1 = new com.metis.book.model.order.Order();
		order1.setOrderDate(new Date());
		order1.setOrderTrack(trackDelivering);
		order1.setPaymentMethod("paypal");
		orderRepository.save(order1);
		
		com.metis.book.model.order.Order order2 = new com.metis.book.model.order.Order();
		order2.setOrderDate(new Date());
		order2.setOrderTrack(trackDelivering);
		order2.setPaymentMethod("momo");
		orderRepository.save(order2);
	}

	@Test
	@Order(11)
	public void testCreateOrderItem() {

		// get User
		User user = userRepository.findByUsername("kiet");
		if (Objects.isNull(user)) {
			log.error(AppConstant.USER_NOT_FOUND + "kiet");
		}

		// get order1
		com.metis.book.model.order.Order order1 = orderRepository.findById(1L).get();
		// get order2
		com.metis.book.model.order.Order order2 = orderRepository.findById(2L).get();
	
		// get Book1
		Book book1 = bookRepository.findById(1L).get();
		// get Book2
		Book book2 = bookRepository.findById(2L).get();
		// get Book3
		Book book3 = bookRepository.findById(3L).get();
		// get Book4
		Book book4 = bookRepository.findById(4L).get();
		
		// Create OrderItem1 for order1
		OrderItem orderItem1 = new OrderItem();
		orderItem1.setQuantity(2);
		orderItem1.setBook(book1);
		orderItem1.setOrder(order1);
		orderItemRepository.save(orderItem1);
		
		// Create OrderItem2 for order1
		OrderItem orderItem2 = new OrderItem();
		orderItem2.setQuantity(3);
		orderItem2.setBook(book2);
		orderItem2.setOrder(order1);
		orderItemRepository.save(orderItem2);
		
		// Create OrderItem3 for order2
		OrderItem orderItem3 = new OrderItem();
		orderItem3.setQuantity(2);
		orderItem3.setBook(book3);
		orderItem3.setOrder(order2);
		orderItemRepository.save(orderItem3);
		
		// Create OrderItem4 for order2
		OrderItem orderItem4 = new OrderItem();
		orderItem4.setQuantity(3);
		orderItem4.setBook(book4);
		orderItem4.setOrder(order2);
		orderItemRepository.save(orderItem4);
	}
	
	@Test
	@Order(12)
	public void testCreateBlog(){
		Blog blog1 = new Blog();
		blog1.setTitle("Cho em 10 điểm nha cô");
		blog1.setContent("Cho em 10 điểm đi mà");
		blogRepository.save(blog1);
		
		Blog blog2 = new Blog();
		blog2.setTitle("Cho em 9 điểm nha cô");
		blog2.setContent("Cho em 9 điểm đi mà");
		blogRepository.save(blog2);
		
		Blog blog3 = new Blog();
		blog3.setTitle("Cho em 8 điểm nha cô");
		blog3.setContent("Cho em 8 điểm đi mà");
		blogRepository.save(blog3);
	}
// if fetch type = lazy, we can get that objects using get. Only able with eager type.
// ToString is the thing cause stackoverflow, 
	
//	@Test
//	public void testStackOverFlow() {
//		List<com.metis.book.model.order.Order> orders = orderRepository.findAll();
//		log.info(orders.get(0).getOrderItems().get(0).getBook().getLanguage().getName());
//		//log.info("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//	}
//	
	public void createCustomer() {

		// Create new Cart
		Cart cart = new Cart();
		cart.setCartItems(null);
		cart.setUser(null);
		Cart cartSaved = cartReposiroty.save(cart);

		// Create new Image
		Image imageThumbnail = new Image();
		imageThumbnail.setThumbnailName("avtThumbnail.jpg");
		imageThumbnail.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\avtThumbnail.jpg");
		imageRepository.save(imageThumbnail);

		// get user role
		Role roleUser = roleRepository.findByName(RoleName.USER);

		if (Objects.isNull(roleUser)) {
			log.error(AppConstant.ROLE_NOT_FOUND + "User");
		}

		User user = User.builder().username("kiet").password(passwordEncoder.encode("456"))
				.email("kietle1709@gmail.com").firstName("kiet").lastName("Le Nguyen Tuan")
				.birthday(LocalDate.of(2002, 9, 17)).gender(1) // 1: male, 2: female, 3: Not know
				.phoneNumber("01255145165").enabled(Boolean.TRUE).roles(Arrays.asList(roleUser)).cart(cartSaved)
				.addresses(null).image(imageThumbnail).build();
		userRepository.save(user);

//		// Map user to cart because user not exits
//		userSaved.getCart().setUser(userSaved);

	}

	public void createAdmin() {

		// get admin role
		Role roleAdmin = roleRepository.findByName(RoleName.ADMIN);

		if (Objects.isNull(roleAdmin)) {
			log.error(AppConstant.ROLE_NOT_FOUND + " Admin");
		}

		// Create new Image
		Image imageThumbnail = new Image();
		imageThumbnail.setThumbnailName("avtThumbnail.jpg");
		imageThumbnail.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\avtThumbnail.jpg");
		imageRepository.save(imageThumbnail);

		// get user role
		Role roleUser = roleRepository.findByName(RoleName.USER);

		if (Objects.isNull(roleUser)) {
			log.error(AppConstant.ROLE_NOT_FOUND + "User");
		}

		// Create new Admin
		User user = User.builder().username("khai").password(passwordEncoder.encode("123"))
				.email("duckhailinux@gmail.com").firstName("khai").lastName("Nguyen")
				.birthday(LocalDate.of(2002, 06, 06)).gender(1) // 1: male, 2: female, 3: Not know
				.phoneNumber("0783511740").enabled(Boolean.TRUE).roles(Arrays.asList(roleAdmin, roleUser)).cart(null)
				.addresses(null).image(imageThumbnail).build();
		userRepository.save(user);

	}

}
