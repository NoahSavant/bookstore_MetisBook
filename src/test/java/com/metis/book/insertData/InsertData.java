//package com.metis.book.insertData;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.Objects;
//
//import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.annotation.Rollback;
//
//import com.metis.book.model.Aim;
//import com.metis.book.model.Author;
//import com.metis.book.model.Blog;
//import com.metis.book.model.Book;
//import com.metis.book.model.Cart;
//import com.metis.book.model.CartItem;
//import com.metis.book.model.Category;
//import com.metis.book.model.Feedback;
//import com.metis.book.model.Image;
//import com.metis.book.model.Inventory;
//import com.metis.book.model.Language;
//import com.metis.book.model.order.OrderItem;
//import com.metis.book.model.order.OrderTrack;
//import com.metis.book.model.user.Address;
//import com.metis.book.model.user.Role;
//import com.metis.book.model.user.RoleName;
//import com.metis.book.model.user.User;
//import com.metis.book.repository.AddressRepository;
//import com.metis.book.repository.AimRepository;
//import com.metis.book.repository.AuthorRepository;
//import com.metis.book.repository.BlogRepository;
//import com.metis.book.repository.BookRepository;
//import com.metis.book.repository.CartItemReposirory;
//import com.metis.book.repository.CartReposiroty;
//import com.metis.book.repository.CategoryRepository;
//import com.metis.book.repository.FeedbackRepository;
//import com.metis.book.repository.ImageRepository;
//import com.metis.book.repository.InventoryRepository;
//import com.metis.book.repository.LanguageRepository;
//import com.metis.book.repository.OrderItemRepository;
//import com.metis.book.repository.OrderRepository;
//import com.metis.book.repository.OrderTrackRepository;
//import com.metis.book.repository.RoleRepository;
//import com.metis.book.repository.UserRepository;
//import com.metis.book.utils.AppConstant;
//
//import groovyjarjarantlr4.v4.parse.ANTLRParser.block_return;
//import lombok.extern.slf4j.Slf4j;
//
//@SpringBootTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
//@TestMethodOrder(OrderAnnotation.class)
//@Slf4j
//@Rollback(false)
//public class InsertData {
//
//	@Autowired
//	RoleRepository roleRepository;
//
//	@Autowired
//	UserRepository userRepository;
//
//	@Autowired
//	PasswordEncoder passwordEncoder;
//
//	@Autowired
//	BookRepository bookRepository;
//
//	@Autowired
//	CategoryRepository categoryRepository;
//
//	@Autowired
//	AuthorRepository authorRepository;
//
//	@Autowired
//	LanguageRepository languageRepository;
//
//	@Autowired
//	InventoryRepository inventoryRepository;
//
//	@Autowired
//	AddressRepository addressRepository;
//
//	@Autowired
//	CartReposiroty cartReposiroty;
//
//	@Autowired
//	CartItemReposirory cartItemReposirory;
//
//	@Autowired
//	ImageRepository imageRepository;
//
//	@Autowired
//	OrderTrackRepository orderTrackRepository;
//
//	@Autowired
//	OrderRepository orderRepository;
//
//	@Autowired
//	OrderItemRepository orderItemRepository;
//
//	@Autowired
//	BlogRepository blogRepository;
//
//	@Autowired
//	FeedbackRepository feedbackRepository;
//
//	@Autowired
//	AimRepository aimRepository;
//
//	@Test
//	@Order(1)
//	public void testCreateAuthor() {
//
//		// Create author
//		Author author1 = new Author();
//		author1.setName("Nguyễn Nhật Ánh");
//		author1.setBooks(null);
//		authorRepository.save(author1);
//
//		Author author2 = new Author();
//		author2.setName("Hà Minh Hoàng");
//		author2.setBooks(null);
//		authorRepository.save(author2);
//
//		Author author3= new Author();
//		author3.setName("Huyền Trang");
//		author3.setBooks(null);
//		authorRepository.save(author3);
//
//		Author author4= new Author();
//		author4.setName("Nguyễn Quang Ngọc");
//		author4.setBooks(null);
//		authorRepository.save(author4);
//
//		Author author5= new Author();
//		author5.setName("Trịnh Ngọc Trang");
//		author5.setBooks(null);
//		authorRepository.save(author5);
//
//		Author author6= new Author();
//		author6.setName("Kousuke Sawamura");
//		author6.setBooks(null);
//		authorRepository.save(author6);
//
//		Author author7= new Author();
//		author7.setName("Trang Anh");
//		author7.setBooks(null);
//		authorRepository.save(author7);
//	}
//
//	@Test
//	@Order(2)
//	public void testCreateCategory() {
//
//
//		// Create image Tieu Thuyet
//		Image image1 = new Image();
//		image1.setTitle("1.png");
//		image1.setUrl("uploads\\categories\\1.png");
//		imageRepository.save(image1);
//
//		// Create image Ngon Tinh
//		Image image2 = new Image();
//		image2.setTitle("2.png");
//		image2.setUrl("uploads\\categories\\2.png");
//		imageRepository.save(image2);
//
//		// Create image Ngon Tinh
//		Image image3 = new Image();
//		image3.setTitle("3.png");
//		image3.setUrl("uploads\\categories\\3.png");
//		imageRepository.save(image3);
//
//		// Create image Cung đấu
//		Image image4 = new Image();
//		image4.setTitle("4.png");
//		image4.setUrl("uploads\\categories\\4.png");
//		imageRepository.save(image4);
//
//		// Create image kinh te
//		Image image5 = new Image();
//		image5.setTitle("5.png");
//		image5.setUrl("uploads\\categories\\5.png");
//		imageRepository.save(image5);
//
//		// Create image kinh te
//		Image image6 = new Image();
//		image6.setTitle("6.png");
//		image6.setUrl("uploads\\categories\\6.png");
//		imageRepository.save(image6);
//
//		// Create image kinh te
//		Image image7 = new Image();
//		image7.setTitle("7.png");
//		image7.setUrl("uploads\\categories\\7.png");
//		imageRepository.save(image7);
//
//		// Create category
//		Category category1 = new Category();
//		category1.setName("Tiểu thuyết");
//		category1.setImage(image1);
//		categoryRepository.save(category1);
//
//		Category category2 = new Category();
//		category2.setName("Ngôn tình");
//		category2.setImage(image2);
//		categoryRepository.save(category2);
//
//		Category category3 = new Category();
//		category3.setName("Trinh thám");
//		category3.setImage(image3);
//		categoryRepository.save(category3);
//
//		Category category4 = new Category();
//		category4.setName("Cung đấu");
//		category4.setImage(image4);
//		categoryRepository.save(category4);
//
//		Category category5 = new Category();
//		category5.setName("Kinh tế");
//		category5.setImage(image5);
//		categoryRepository.save(category5);
//
//		Category category6= new Category();
//		category6.setName("Kỹ thuật");
//		category6.setImage(image6);
//		categoryRepository.save(category6);
//
//		Category category7= new Category();
//		category7.setName("Giáo dục");
//		category7.setImage(image7);
//		categoryRepository.save(category7);
//	}
//
//	@Test
//	@Order(3)
//	public void testCreateLanguage() {
//		Language language1 = new Language();
//		language1.setName("Tiếng Việt");
//		languageRepository.save(language1);
//
//		Language language2 = new Language();
//		language2.setName("Tiếng Anh");
//		languageRepository.save(language2);
//
//		Language language3 = new Language();
//		language3.setName("Tiếng Trung");
//		languageRepository.save(language3);
//
//		Language language4 = new Language();
//		language4.setName("Tiếng Nhật");
//		languageRepository.save(language4);
//
//		Language language5 = new Language();
//		language5.setName("Tiếng Hàn");
//		languageRepository.save(language5);
//	}
//
//	@Test
//	@Order(4)
//	public void testCreateBook() {
//
//		// Get category
//		Category categoryTieuThuyet = categoryRepository.findByName("Tiểu Thuyết");
//		if (Objects.isNull(categoryTieuThuyet)) {
//			log.error(AppConstant.CATEGORY_NOT_FOUND + "Tiểu thuyết");
//		}
//
//		Category categoryNgonTinh = categoryRepository.findByName("Ngôn tình");
//		if (Objects.isNull(categoryNgonTinh)) {
//			log.error(AppConstant.CATEGORY_NOT_FOUND + "Ngôn tình");
//		}
//
//		Category categoryCungDau = categoryRepository.findByName("Cung đấu");
//		if (Objects.isNull(categoryCungDau)) {
//			log.error(AppConstant.CATEGORY_NOT_FOUND + "Cung đấu");
//		}
//
//		Category categoryTrinhTham = categoryRepository.findByName("Trinh thám");
//		if (Objects.isNull(categoryCungDau)) {
//			log.error(AppConstant.CATEGORY_NOT_FOUND + "Trinh thám");
//		}
//
//		Category categoryKinhTe = categoryRepository.findByName("Kinh tế");
//		if (Objects.isNull(categoryKinhTe)) {
//			log.error(AppConstant.CATEGORY_NOT_FOUND + "Kinh tế");
//		}
//
//		Category categoryKyThuat = categoryRepository.findByName("Kỹ thuật");
//		if (Objects.isNull(categoryKyThuat)) {
//			log.error(AppConstant.CATEGORY_NOT_FOUND + "Kỹ thuật");
//		}
//
//		Category categoryGiaoDuc = categoryRepository.findByName("Giáo dục");
//		if (Objects.isNull(categoryGiaoDuc)) {
//			log.error(AppConstant.CATEGORY_NOT_FOUND + "Giáo dục");
//		}
//
//		// Get language
//		Language languageTiengViet = languageRepository.findByName("Tiếng Việt");
//		if (Objects.isNull(languageTiengViet)) {
//			log.error(AppConstant.LANGUAGE_NOT_FOUND + "Tiếng Việt");
//		}
//
//		Language languageTiengAnh = languageRepository.findByName("Tiếng Anh");
//		if (Objects.isNull(languageTiengAnh)) {
//			log.error(AppConstant.LANGUAGE_NOT_FOUND + "Tiếng Anh");
//		}
//
//		Language languageTiengTrung = languageRepository.findByName("Tiếng Trung");
//		if (Objects.isNull(languageTiengTrung)) {
//			log.error(AppConstant.LANGUAGE_NOT_FOUND + "Tiếng Trung");
//		}
//
//		Language languageTiengNhat = languageRepository.findByName("Tiếng Nhật");
//		if (Objects.isNull(languageTiengNhat)) {
//			log.error(AppConstant.LANGUAGE_NOT_FOUND + "Tiếng Nhật");
//		}
//
//		Language languageTiengHan = languageRepository.findByName("Tiếng Hàn");
//		if (Objects.isNull(languageTiengHan)) {
//			log.error(AppConstant.LANGUAGE_NOT_FOUND + "Tiếng Hàn");
//		}
//
//		// Get Author
//		Author author1 = authorRepository.findByName("Nguyễn Nhật Ánh");
//		if (Objects.isNull(author1)) {
//			log.error(AppConstant.AUTHOR_NOT_FOUND + "Nguyễn Nhật Ánh");
//		}
//
//		Author author2 = authorRepository.findByName("Hà Minh Hoàng");
//		if (Objects.isNull(author2)) {
//			log.error(AppConstant.AUTHOR_NOT_FOUND + "Hà Minh Hoàng");
//		}
//
//		Author author3 = authorRepository.findByName("Huyền Trang");
//		if (Objects.isNull(author3)) {
//			log.error(AppConstant.AUTHOR_NOT_FOUND + "Huyền Trang");
//		}
//
//		Author author4 = authorRepository.findByName("Nguyễn Quang Ngọc");
//		if (Objects.isNull(author4)) {
//			log.error(AppConstant.AUTHOR_NOT_FOUND + "Nguyễn Quang Ngọc");
//		}
//
//		Author author5 = authorRepository.findByName("Trịnh Ngọc Trang");
//		if (Objects.isNull(author5)) {
//			log.error(AppConstant.AUTHOR_NOT_FOUND + "Trịnh Ngọc Trang");
//		}
//
//		Author author6 = authorRepository.findByName("Kousuke Sawamura");
//		if (Objects.isNull(author6)) {
//			log.error(AppConstant.AUTHOR_NOT_FOUND + "Kousuke Sawamura");
//		}
//
//		Author author7 = authorRepository.findByName("Trang Anh");
//		if (Objects.isNull(author7)) {
//			log.error(AppConstant.AUTHOR_NOT_FOUND + "Trang Anh");
//		}
//
//		// Create new inventory for Book1
//		Inventory inventoryForBook1 = new Inventory();
//		inventoryForBook1.setQuantiy(10);
//		inventoryForBook1.setBook(null);
//		Inventory inventorySaved1 = inventoryRepository.save(inventoryForBook1);
//
//		// Create new inventory for Book2
//		Inventory inventoryForBook2 = new Inventory();
//		inventoryForBook2.setQuantiy(5);
//		inventoryForBook2.setBook(null);
//		Inventory inventorySaved2 = inventoryRepository.save(inventoryForBook2);
//
//		// Create new inventory for Book3
//		Inventory inventoryForBook3 = new Inventory();
//		inventoryForBook3.setQuantiy(5);
//		inventoryForBook3.setBook(null);
//		Inventory inventorySaved3 = inventoryRepository.save(inventoryForBook3);
//
//		// Create new inventory for Book4
//		Inventory inventoryForBook4 = new Inventory();
//		inventoryForBook4.setQuantiy(5);
//		inventoryForBook4.setBook(null);
//		Inventory inventorySaved4 = inventoryRepository.save(inventoryForBook4);
//
//		// Create new inventory for Book5
//		Inventory inventoryForBook5 = new Inventory();
//		inventoryForBook5.setQuantiy(4);
//		inventoryForBook5.setBook(null);
//		Inventory inventorySaved5 = inventoryRepository.save(inventoryForBook5);
//
//		// Create new inventory for Book6
//		Inventory inventoryForBook6 = new Inventory();
//		inventoryForBook6.setQuantiy(7);
//		inventoryForBook6.setBook(null);
//		Inventory inventorySaved6 = inventoryRepository.save(inventoryForBook6);
//
//		// Create new inventory for Book7
//		Inventory inventoryForBook7 = new Inventory();
//		inventoryForBook7.setQuantiy(3);
//		inventoryForBook7.setBook(null);
//		Inventory inventorySaved7 = inventoryRepository.save(inventoryForBook7);
//
//		// Create new inventory for Book8
//		Inventory inventoryForBook8 = new Inventory();
//		inventoryForBook8.setQuantiy(10);
//		inventoryForBook8.setBook(null);
//		Inventory inventorySaved8 = inventoryRepository.save(inventoryForBook8);
//
//		// Create new inventory for Book9
//		Inventory inventoryForBook9 = new Inventory();
//		inventoryForBook9.setQuantiy(9);
//		inventoryForBook9.setBook(null);
//		Inventory inventorySaved9 = inventoryRepository.save(inventoryForBook9);
//
//		// Create new inventory for Book10
//		Inventory inventoryForBook10 = new Inventory();
//		inventoryForBook10.setQuantiy(10);
//		inventoryForBook10.setBook(null);
//		Inventory inventorySaved10 = inventoryRepository.save(inventoryForBook10);
//
//		// Create new Thumbnail for Book1
//		Image image1 = new Image();
//		image1.setTitle("1.png");
//		image1.setUrl("uploads\\books\\1.png");
//		image1.setThumbnailName("BookThumbnail.png");
//		image1.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\BookThumbnail.png");
//		imageRepository.save(image1);
//
//
//		// Create new Thumbnail for Book2
//		Image image2 = new Image();
//		image2.setTitle("2.png");
//		image2.setUrl("uploads\\books\\2.png");
//		image2.setThumbnailName("BookThumbnail.png");
//		image2.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\BookThumbnail.png");
//		imageRepository.save(image2);
//
//		// Create new Thumbnail for Book3
//		Image image3 = new Image();
//		image3.setTitle("3.png");
//		image3.setUrl("uploads\\books\\3.png");
//		image3.setThumbnailName("BookThumbnail.png");
//		image3.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\BookThumbnail.png");
//		imageRepository.save(image3);
//
//		// Create new Thumbnail for Book4
//		Image image4 = new Image();
//		image4.setTitle("4.png");
//		image4.setUrl("uploads\\books\\4.png");
//		image4.setThumbnailName("BookThumbnail.png");
//		image4.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\BookThumbnail.png");
//		imageRepository.save(image4);
//
//		// Create new Thumbnail for Book5
//		Image image5 = new Image();
//		image5.setTitle("5.png");
//		image5.setUrl("uploads\\books\\5.png");
//		image5.setThumbnailName("BookThumbnail.png");
//		image5.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\BookThumbnail.png");
//		imageRepository.save(image5);
//
//
//		// Create new Thumbnail for Book6
//		Image image6 = new Image();
//		image6.setTitle("6.png");
//		image6.setUrl("uploads\\books\\6.png");
//		image6.setThumbnailName("BookThumbnail.png");
//		image6.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\BookThumbnail.png");
//		imageRepository.save(image6);
//
//		// Create new Thumbnail for Book7
//		Image image7 = new Image();
//		image7.setTitle("7.png");
//		image7.setUrl("uploads\\books\\7.png");
//		image7.setThumbnailName("BookThumbnail.png");
//		image7.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\BookThumbnail.png");
//		imageRepository.save(image7);
//
//		// Create new Thumbnail for Book8
//		Image image8 = new Image();
//		image8.setTitle("8.png");
//		image8.setUrl("uploads\\books\\8.png");
//		image8.setThumbnailName("BookThumbnail.png");
//		image8.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\BookThumbnail.png");
//		imageRepository.save(image8);
//
//		// Create new Thumbnail for Book9
//		Image image9 = new Image();
//		image9.setTitle("9.png");
//		image9.setUrl("uploads\\books\\9.png");
//		image9.setThumbnailName("BookThumbnail.png");
//		image9.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\BookThumbnail.png");
//		imageRepository.save(image9);
//
//		// Create new Thumbnail for Book10
//		Image image10 = new Image();
//		image10.setTitle("10.png");
//		image10.setUrl("uploads\\books\\10.png");
//		image10.setThumbnailName("BookThumbnail.png");
//		image10.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\BookThumbnail.png");
//		imageRepository.save(image10);
//
//		// Create Book 1
//		Book book1 = Book.builder().title("Tôi thấy hoa vàng trên cỏ xanh").available(Boolean.TRUE).price(50000L)
//				.category(categoryTieuThuyet).description("Một cuốn tiểu thuyết giành cho giới trẻ").language(languageTiengViet)
//				.publicationDate(new Date()).publisherName("Kim Đồng").inventory(inventorySaved1)
//				.authors(Arrays.asList(author1)).image(image1).build();
//		bookRepository.save(book1);
//
//		// Create Book 2
//
//		Book book2 = Book.builder().title("Mắt biếc").available(Boolean.TRUE).category(categoryTrinhTham).price(45000L)
//				.description("Một cuốn tiểu thuyết chốn đồng quê").language(languageTiengViet).publicationDate(new Date())
//				.publisherName("Kim Đồng").inventory(inventorySaved2).authors(Arrays.asList(author1))
//				.image(image2).build();
//		bookRepository.save(book2);
//
//		// Create Book 3
//
//		Book book3 = Book.builder().title("Game of throne").available(Boolean.TRUE).category(categoryCungDau)
//				.price(58000L).description("Một cuốn sách về cung đấu").language(languageTiengViet).publicationDate(new Date())
//				.publisherName("Phụ nữ").inventory(inventorySaved3).authors(Arrays.asList(author1))
//				.image(image3).build();
//		bookRepository.save(book3);
//
//		// Create Book 4
//
//		Book book4 = Book.builder().title("Your Name").available(Boolean.TRUE).category(categoryNgonTinh).price(26000L)
//				.description("Một cuốn sách ngôn tình").language(languageTiengViet).publicationDate(new Date())
//				.publisherName("Kadokawa").inventory(inventorySaved4).authors(Arrays.asList(author1))
//				.image(image4).build();
//		bookRepository.save(book4);
//
//		// Create Book 5
//
//		Book book5 = Book.builder().title("Marketing căn bản").available(Boolean.TRUE).category(categoryKinhTe).price(126000L)
//				.description("Tất cả các thông tin cần thiết để phục vụ cho marketing").language(languageTiengViet).publicationDate(new Date())
//				.publisherName("Nhà xuất bản lao động").inventory(inventorySaved5).authors(Arrays.asList(author2, author3))
//				.image(image5).build();
//		bookRepository.save(book5);
//
//		// Create Book 6
//
//		Book book6 = Book.builder().title("Thư bán hàng đỉnh cao").available(Boolean.TRUE).category(categoryKinhTe).price(130000L)
//				.description("Để trở thành người bán hàng đỉnh cao").language(languageTiengViet).publicationDate(new Date())
//				.publisherName("Nhà xuất bản tổng hợp").inventory(inventorySaved6).authors(Arrays.asList(author4))
//				.image(image6).build();
//		bookRepository.save(book6);
//
//		// Create Book 7
//
//		Book book7 = Book.builder().title("Kỹ thuật sữa chữa máy in").available(Boolean.TRUE).category(categoryKyThuat).price(30000L)
//				.description("Dành cho ai đam mê xử lý với máy in").language(languageTiengViet).publicationDate(new Date())
//				.publisherName("Nhà xuất bản giao thông vận tải").inventory(inventorySaved7).authors(Arrays.asList(author5))
//				.image(image7).build();
//		bookRepository.save(book7);
//
//		// Create Book 8
//
//		Book book8 = Book.builder().title("Tiệm cắt tóc lúc nửa đêm").available(Boolean.TRUE).category(categoryTieuThuyet).price(90000L)
//				.description("Câu chuyện về tình bạn cảm động").language(languageTiengViet).publicationDate(new Date())
//				.publisherName("Nhà xuất bản giao thông vận tải").inventory(inventorySaved8).authors(Arrays.asList(author6))
//				.image(image8).build();
//		bookRepository.save(book8);
//
//		// Create Book 9
//
//		Book book9 = Book.builder().title("Cẩm nang cấu trúc tiếng anh").available(Boolean.TRUE).category(categoryGiaoDuc).price(80000L)
//				.description("Học cấu trúc tiếng anh").language(languageTiengViet).publicationDate(new Date())
//				.publisherName("Nhà xuất bản đại học sư phạm").inventory(inventorySaved9).authors(Arrays.asList(author7))
//				.image(image9).build();
//		bookRepository.save(book9);
//
//		// Create Book 10
//
//		Book book10 = Book.builder().title("25 chuyên đề ngữ pháp tiếng anh").available(Boolean.TRUE).category(categoryGiaoDuc).price(110000L)
//				.description("Học ngữ pháp tiếng anh").language(languageTiengViet).publicationDate(new Date())
//				.publisherName("Nhà xuất bản đại học sư phạm").inventory(inventorySaved10).authors(Arrays.asList(author7))
//				.image(image10).build();
//		bookRepository.save(book10);
//	}
//
//	@Test
//	@Order(5)
//	public void testCreateRole() {
//		// Create role admin
//		Role roleAdmin = new Role();
//		roleAdmin.setName(RoleName.ADMIN);
//		roleRepository.save(roleAdmin);
//
//		// Create role user
//		Role roleUser = new Role();
//		roleUser.setName(RoleName.USER);
//		roleRepository.save(roleUser);
//
//		// Create role staff
//		Role roleStaff = new Role();
//		roleStaff.setName(RoleName.STAFF);
//		roleRepository.save(roleStaff);
//	}
//
//	@Test
//	@Order(6)
//	public void testCreateUser() {
//		createAdmin();
//		createCustomer();
//	}
//
//	@Test
//	@Order(7)
//	public void testCreateAddress() {
//
//		// get user admin
//		User userAdmin = userRepository.findByUsername("khai");
//
//		if (Objects.isNull(userAdmin)) {
//			log.error(AppConstant.USER_NOT_FOUND + "khai");
//		}
//
//		// Create new address for admin
//		Address address = new Address();
//		address.setFullAddress("");
//		address.setStreet("241 Nguyễn Trãi");
//		address.setSubDistrict("Lái Thiêu");
//		address.setDistrict("Thuận An");
//		address.setProvince("Bình Dương");
//		address.setIsPrimary(Boolean.TRUE);
//		address.setRecievePhoneNumber(userAdmin.getPhoneNumber());
//		address.setUser(userAdmin);
//		addressRepository.save(address);
//
//		// get user customer
//		User userCustomer = userRepository.findByUsername("kiet");
//
//		if (Objects.isNull(userCustomer)) {
//			log.error(AppConstant.USER_NOT_FOUND + "kiet");
//		}
//
//		// Create new address for customer
//		Address addressCustomer1 = new Address();
//		addressCustomer1.setFullAddress("");
//		addressCustomer1.setStreet("168 Trương Văn Bang");
//		addressCustomer1.setSubDistrict("Thạnh Mỹ Lợi");
//		addressCustomer1.setDistrict("Thủ Đức");
//		addressCustomer1.setProvince("Hồ Chí Minh");
//		addressCustomer1.setIsPrimary(Boolean.TRUE);
//		addressCustomer1.setRecievePhoneNumber("0783511740");
//		addressCustomer1.setUser(userCustomer);
//		addressRepository.save(addressCustomer1);
//
//
//		// Create new address for customer
//		Address addressCustomer2 = new Address();
//		addressCustomer2.setFullAddress("");
//		addressCustomer2.setStreet("24 Hồ Văn Hà");
//		addressCustomer2.setSubDistrict("Thạnh Mỹ Lợi");
//		addressCustomer2.setDistrict("Thủ Đức");
//		addressCustomer2.setProvince("Hồ Chí Minh");
//		addressCustomer2.setIsPrimary(Boolean.FALSE);
//		addressCustomer2.setRecievePhoneNumber("0912145167");
//		addressCustomer2.setUser(userCustomer);
//		addressRepository.save(addressCustomer2);
//	}
//
//	@Test
//	@Order(8)
//	public void testCreateCartItem() {
//
//		// get User
//		User userKiet = userRepository.findByUsername("kiet");
//		if (Objects.isNull(userKiet)) {
//			log.error(AppConstant.USER_NOT_FOUND + "kiet");
//		}
//
//
//		// get Cart
//		Cart cartKiet = userKiet.getCart();
//
//		// get Book 1
//		Book book1 = bookRepository.findByTitle("Tôi thấy hoa vàng trên cỏ xanh");
//		if (Objects.isNull(book1)) {
//			log.error(AppConstant.BOOK_NOT_FOUND + " Tôi thấy hoa vàng trên cỏ xanh");
//		}
//
//		// get Book 2
//		Book book2 = bookRepository.findByTitle("Mắt biếc");
//		if (Objects.isNull(book2)) {
//			log.error(AppConstant.BOOK_NOT_FOUND + "Mắt biếc");
//		}
//
//		// Create cartItem1 for user kiet
//		CartItem cartItem1 = new CartItem();
//		cartItem1.setQuantity(1);
//		cartItem1.setCart(cartKiet);
//		cartItem1.setBook(book1);
//		cartItemReposirory.save(cartItem1);
//
//		// Create cartItem2 for user Kiet
//		CartItem cartItem2 = new CartItem();
//		cartItem2.setQuantity(3);
//		cartItem2.setCart(cartKiet);
//		cartItem2.setBook(book2);
//		cartItemReposirory.save(cartItem2);
//
//	}
//
//	@Test
//	@Order(9)
//	public void testCreateOrderTrack() {
//
//		// Payment process
//		OrderTrack trackPaymentProcess = new OrderTrack();
//		trackPaymentProcess.setStatus("Chờ thanh toán");
//		orderTrackRepository.save(trackPaymentProcess);
//
//		// Delivery
//		OrderTrack trackDelivering = new OrderTrack();
//		trackDelivering.setStatus("Đang giao");
//		orderTrackRepository.save(trackDelivering);
//
//		// Preparing
//		OrderTrack trackPreparing = new OrderTrack();
//		trackPreparing.setStatus("Đang chuẩn bị");
//		orderTrackRepository.save(trackPreparing);
//
//		// Completed
//		OrderTrack trackCompleted = new OrderTrack();
//		trackCompleted.setStatus("Đã giao");
//		orderTrackRepository.save(trackCompleted);
//
//	}
//
//	@Test
//	@Order(10)
//	public void testCreateOrder() {
//
//		// get User
//		User user = userRepository.findByUsername("kiet");
//		if (Objects.isNull(user)) {
//			log.error(AppConstant.USER_NOT_FOUND + "kiet");
//		}
//
//		// get order track
//		OrderTrack trackDelivering = orderTrackRepository.findByStatus("Đang giao");
//
//		// Create order1
//		com.metis.book.model.order.Order order1 = new com.metis.book.model.order.Order();
//		order1.setOrderDate(new Date());
//		order1.setOrderTrack(trackDelivering);
//		order1.setPaymentMethod("Cash");
//		order1.setCostVAT("0");
//		order1.setDeliverCost("20000");
//		order1.setDeliverMethod("Tiêu chuẩn");
//		order1.setUser(user);
//		order1.setCreateBy(2L);
//		order1.setUpdateBy(2L);
//		orderRepository.save(order1);
//
//		com.metis.book.model.order.Order order2 = new com.metis.book.model.order.Order();
//		order2.setOrderDate(new Date());
//		order2.setOrderTrack(trackDelivering);
//		order2.setPaymentMethod("momo");
//		order2.setCostVAT("0");
//		order2.setDeliverCost("40000");
//		order2.setDeliverMethod("Nhanh");
//		order2.setUser(user);
//		order2.setCreateBy(2L);
//		order2.setUpdateBy(2L);
//		orderRepository.save(order2);
//	}
//
//	@Test
//	@Order(11)
//	public void testCreateOrderItem() {
//
//		// get User
//		User user = userRepository.findByUsername("kiet");
//		if (Objects.isNull(user)) {
//			log.error(AppConstant.USER_NOT_FOUND + "kiet");
//		}
//
//		// get order1
//		com.metis.book.model.order.Order order1 = orderRepository.findById(1L).get();
//		// get order2
//		com.metis.book.model.order.Order order2 = orderRepository.findById(2L).get();
//
//		// get Book1
//		Book book1 = bookRepository.findById(1L).get();
//		// get Book2
//		Book book2 = bookRepository.findById(2L).get();
//		// get Book3
//		Book book3 = bookRepository.findById(3L).get();
//		// get Book4
//		Book book4 = bookRepository.findById(4L).get();
//
//		// Create OrderItem1 for order1
//		OrderItem orderItem1 = new OrderItem();
//		orderItem1.setQuantity(2);
//		orderItem1.setBook(book1);
//		orderItem1.setOrder(order1);
//		orderItemRepository.save(orderItem1);
//
//		// Create OrderItem2 for order1
//		OrderItem orderItem2 = new OrderItem();
//		orderItem2.setQuantity(3);
//		orderItem2.setBook(book2);
//		orderItem2.setOrder(order1);
//		orderItemRepository.save(orderItem2);
//
//		// Create OrderItem3 for order2
//		OrderItem orderItem3 = new OrderItem();
//		orderItem3.setQuantity(2);
//		orderItem3.setBook(book3);
//		orderItem3.setOrder(order2);
//		orderItemRepository.save(orderItem3);
//
//		// Create OrderItem4 for order2
//		OrderItem orderItem4 = new OrderItem();
//		orderItem4.setQuantity(3);
//		orderItem4.setBook(book4);
//		orderItem4.setOrder(order2);
//		orderItemRepository.save(orderItem4);
//	}
//
//	@Test
//	@Order(12)
//	public void testCreateBlog() {
//
//		// Create thumbnail image 1
//		Image imageThumbnail1 = new Image();
//		imageThumbnail1.setTitle("1.png");
//		imageThumbnail1.setUrl("https://tranhsondaudepviet.com/wp-content/uploads/2020/09/tranh-phong-canh-dep.jpeg");
//		imageThumbnail1.setThumbnailName("BlogThumbnail.png");
//		imageThumbnail1.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\BlogThumbnail.png");
//		imageRepository.save(imageThumbnail1);
//
//		// Create thumbnail image 2
//		Image imageThumbnail2 = new Image();
//		imageThumbnail2.setTitle("2.png");
//		imageThumbnail2.setUrl("https://sieuthitranhsondau.com/wp-content/uploads/2022/05/Nhung-buc-tranh-phong-canh-dep-nhat2.jpg");
//		imageThumbnail2.setThumbnailName("BlogThumbnail.png");
//		imageThumbnail2.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\BlogThumbnail.png");
//		imageRepository.save(imageThumbnail2);
//
//		// Create thumbnail image 3
//		Image imageThumbnail3 = new Image();
//		imageThumbnail3.setTitle("3.png");
//		imageThumbnail3.setUrl("https://anhdepfree.com/wp-content/uploads/2020/11/hinh-nen-phong-canh.jpg");
//		imageThumbnail3.setThumbnailName("BlogThumbnail.png");
//		imageThumbnail3.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\BlogThumbnail.png");
//		imageRepository.save(imageThumbnail3);
//
//		// Create thumbnail image 4
//		Image imageThumbnail4 = new Image();
//		imageThumbnail4.setTitle("4.png");
//		imageThumbnail4.setUrl("https://nld.mediacdn.vn/2017/photo-1-1502239793492.jpg");
//		imageThumbnail4.setThumbnailName("BlogThumbnail.png");
//		imageThumbnail4.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\BlogThumbnail.png");
//		imageRepository.save(imageThumbnail4);
//
//		Blog blog1 = new Blog();
//		blog1.setTitle("Review Sách Tôi Thấy Hoa Vàng Trên Cỏ Xanh");
//		blog1.setSubTitle("Một Lần Tìm Về Bầu Trời Tuổi Thơ Êm Đềm, Tĩnh Lặng. Tôi thấy hoa vàng trên cỏ xanh là tác phẩm đã được chuyển thể thành phim cùng tên của nhà văn Nguyễn Nhật Ánh. Truyện bác Ánh lúc nào cũng dung dị, nhẹ nhàng, lãng mạn và rất tình. Truyện ngắn kể về tuổi thơ của những đứa trẻ lớn lên ở một vùng quê nghèo khó, với những suy nghĩ và rung động tinh tế trong những năm đầu đời.");
//		blog1.setContent("<em style=\"color: rgb(34, 34, 34); font-family: arial, helvetica, sans-serif; font-size: 18.6667px; text-align: justify;\"><strong style=\"font-weight: bold;\">Tôi thấy hoa vàng trên cỏ xanh</strong>&nbsp;là tác phẩm đã được chuyển thể thành phim cùng tên của nhà văn Nguyễn Nhật Ánh. Truyện bác Ánh lúc nào cũng dung dị, nhẹ nhàng, lãng mạn và rất tình. Truyện ngắn kể về tuổi thơ của những đứa trẻ lớn lên ở một vùng quê nghèo khó, với những suy nghĩ và rung động tinh tế trong những năm đầu đời.<br></em><h2 style=\"font-family: Roboto, sans-serif; color: rgb(17, 17, 17); margin: 30px 0px 20px; font-size: 27px; line-height: 38px;\"><span style=\"font-family: arial, helvetica, sans-serif; font-size: 18pt;\"><strong style=\"font-weight: bold;\">Giới Thiệu Tác Giả Sách Tôi Thấy Hoa Vàng Trên Cỏ Xanh</strong></span><span class=\"ez-toc-section-end\"></span></h2><div><span style=\"font-family: arial, helvetica, sans-serif; font-size: 14pt;\">Nhà văn Nguyễn Nhật Ánh được biết đến qua nhiều tác phẩm văn học về đề tài tuổi mới lớn. Cốt truyện đơn giản, tình cảm trong sáng, cảnh đẹp làng quê là những nét đặc trưng trong các truyện ngắn của Nguyễn Nhật Ánh. Ngoài Tôi thấy hoa vàng trên cỏ xanh, truyện ngắn&nbsp;<a href=\"https://revisach.com/truyen-ngan-mat-biec-nguyen-nhat-anh/\" target=\"_blank\" rel=\"noopener noreferrer\" style=\"color: rgb(77, 178, 236);\">Mắt Biếc</a>&nbsp;và Cô gái đến từ hôm qua của bác đều đã được chuyển thể thành phim và mang lại tiếng vang lớn.</span></div><figure id=\"attachment_686\" aria-describedby=\"caption-attachment-686\" class=\"wp-caption aligncenter\" style=\"margin: 6px auto 0px; text-align: center; max-width: 100%; clear: both; color: rgb(34, 34, 34); font-family: Verdana, Geneva, sans-serif; font-size: 15px; width: 800px;\"><img decoding=\"async\" class=\"size-full wp-image-686\" src=\"https://revisach.com/wp-content/uploads/2020/07/review-sach-toi-thay-hoa-vang-tren-co-xanh-nguyen-nhat-anh-2.png\" alt=\"review-sach-toi-thay-hoa-vang-tren-co-xanh-nguyen-nhat-anh-2\" width=\"800\" height=\"530\" srcset=\"https://revisach.com/wp-content/uploads/2020/07/review-sach-toi-thay-hoa-vang-tren-co-xanh-nguyen-nhat-anh-2.png 800w, https://revisach.com/wp-content/uploads/2020/07/review-sach-toi-thay-hoa-vang-tren-co-xanh-nguyen-nhat-anh-2-300x199.png 300w, https://revisach.com/wp-content/uploads/2020/07/review-sach-toi-thay-hoa-vang-tren-co-xanh-nguyen-nhat-anh-2-768x509.png 768w, https://revisach.com/wp-content/uploads/2020/07/review-sach-toi-thay-hoa-vang-tren-co-xanh-nguyen-nhat-anh-2-696x461.png 696w, https://revisach.com/wp-content/uploads/2020/07/review-sach-toi-thay-hoa-vang-tren-co-xanh-nguyen-nhat-anh-2-634x420.png 634w, https://revisach.com/wp-content/uploads/2020/07/review-sach-toi-thay-hoa-vang-tren-co-xanh-nguyen-nhat-anh-2-600x398.png 600w\" sizes=\"(max-width: 800px) 100vw, 800px\" style=\"border: 0px; max-width: 100%; height: auto; margin-bottom: 0px; width: 696px; display: block;\"><figcaption id=\"caption-attachment-686\" class=\"wp-caption-text\" style=\"margin: 6px 0px 26px; font-size: 11px; font-style: italic; line-height: 17px; color: rgb(68, 68, 68);\">Nhà văn Nguyễn Nhật Ánh – tác giả Tôi thấy hoa vàng trên cỏ xanh</figcaption></figure><div><strong style=\"font-family: arial, helvetica, sans-serif; font-size: 18pt; color: rgb(17, 17, 17); font-weight: bold;\">Thông Tin Cơ Bản Của Cuốn Sách&nbsp;</strong><strong style=\"font-family: arial, helvetica, sans-serif; font-size: 18pt; color: rgb(17, 17, 17); font-weight: bold;\">Tôi Thấy Hoa Vàng Trên Cỏ Xanh</strong><br></div><h2 style=\"font-family: Roboto, sans-serif; color: rgb(17, 17, 17); margin: 30px 0px 20px; font-size: 27px; line-height: 38px;\"><span class=\"ez-toc-section-end\"></span></h2><table style=\"width: 696px; margin-bottom: 21px; color: rgb(34, 34, 34); font-family: Verdana, Geneva, sans-serif; font-size: 15px; height: 104px;\"><tbody><tr style=\"height: 26px;\"><td style=\"padding: 2px 8px; border-color: rgb(237, 237, 237); width: 347.6px; height: 26px;\">Công ty phát hành</td><td style=\"padding: 2px 8px; border-color: rgb(237, 237, 237); width: 347.6px; height: 26px;\">NXB Trẻ</td></tr><tr style=\"height: 26px;\"><td style=\"padding: 2px 8px; border-color: rgb(237, 237, 237); width: 347.6px; height: 26px;\">Ngày xuất bản</td><td style=\"padding: 2px 8px; border-color: rgb(237, 237, 237); width: 347.6px; height: 26px;\">12-2018</td></tr><tr style=\"height: 26px;\"><td style=\"padding: 2px 8px; border-color: rgb(237, 237, 237); width: 347.6px; height: 26px;\">Loại bìa</td><td style=\"padding: 2px 8px; border-color: rgb(237, 237, 237); width: 347.6px; height: 26px;\">Bìa mềm</td></tr><tr style=\"height: 26px;\"><td style=\"padding: 2px 8px; border-color: rgb(237, 237, 237); width: 347.6px; height: 26px;\">Số trang</td><td style=\"padding: 2px 8px; border-color: rgb(237, 237, 237); width: 347.6px; height: 26px;\">378</td></tr></tbody></table><h2 style=\"font-family: Roboto, sans-serif; color: rgb(17, 17, 17); margin: 30px 0px 20px; font-size: 27px; line-height: 38px;\"><span class=\"ez-toc-section\" id=\"Noi_Dung_Toi_Thay_Hoa_Vang_Tren_Co_Xanh\"></span><span style=\"font-family: arial, helvetica, sans-serif; font-size: 18pt;\"><strong style=\"font-weight: bold;\">Nội Dung Tôi Thấy Hoa Vàng Trên Cỏ Xanh</strong></span><span class=\"ez-toc-section-end\"></span></h2><div><span style=\"font-family: arial, helvetica, sans-serif; font-size: 14pt;\">Cuốn Tôi thấy hoa vàng trên cỏ xanh kể về tuổi thơ nghèo khó của hai anh em Thiều và Tường cùng cô bạn thân hàng xóm. Mạch truyện tự nhiên, dẫn dắt người đọc chứng kiến những rung động đầu đời của tụi nhỏ, xen vào đó là những nét đẹp của tình anh em và vài nốt trầm của sự đau đớn khi trưởng thành. Truyện Nguyễn Nhật Ánh thường không nói quá nhiều về trắng đen, thiện ác nhưng trong tác phẩm này, tác giả đã đưa vấn đề đạo đức vào sách và khiến người đọc suy ngẫm.<br></span><h3 style=\"font-family: Roboto, sans-serif; color: rgb(17, 17, 17); margin: 27px 0px 17px; font-size: 22px; line-height: 30px; text-align: justify;\"><span style=\"font-family: arial, helvetica, sans-serif; font-size: 14pt;\"><strong style=\"font-weight: bold;\">Tuổi Thơ Hồn Nhiên, Mơ Mộng</strong></span><span class=\"ez-toc-section-end\"></span></h3><div><span style=\"font-family: arial, helvetica, sans-serif; font-size: 14pt;\">Sách Tôi thấy hoa vàng trên cỏ xanh gợi nhớ lại một thời tuổi thơ hồn thiên, mơ mộng. Những ngày Tường trở thành chú chim xanh chuyên giao thư qua lại giữa chú Đàn và chị Vinh nhưng phải thật khéo vì nếu cha chị Vinh biết thì cả ba xong đời.</span></div><div><span style=\"font-family: arial, helvetica, sans-serif; font-size: 14pt;\">Hay những lúc nghe chuyện ma, hai anh em sợ đến nỗi bị nhát là sẽ chạy tán loạn, đến khi về nhà còn bị đánh cho một trận vì tội con trai mà lại sợ ma. Dẫu yếu bóng vía là thế nhưng khi nghe kể về xóm Miễu có ma cọp thì hai anh em vẫn nổi máu tò mò và đồi đi khám phá.</span></div><h3 style=\"font-family: Roboto, sans-serif; color: rgb(17, 17, 17); margin: 27px 0px 17px; font-size: 22px; line-height: 30px; text-align: justify;\"><span class=\"ez-toc-section\" id=\"Tinh_Yeu_Dau_Doi_Ngay_Ngo_Trong_Sang\"></span><span style=\"font-family: arial, helvetica, sans-serif; font-size: 14pt;\"><strong style=\"font-weight: bold;\">Tình Yêu Đầu Đời Ngây Ngô, Trong Sáng</strong></span><span class=\"ez-toc-section-end\"></span></h3><div><span style=\"font-family: arial, helvetica, sans-serif; font-size: 14pt;\">Tình yêu trong truyện là những rung động ngây ngô, trong sáng đầu đời. Lần đầu tiên biết đến hoa tay, chú bé Thiều đã chạy khắp làng để xem hoa tay cho từng đứa. Rồi đến khi biết đến thư tình, Thiều cũng mượn thơ của chú Đàn để viết vào mảnh giấy:&nbsp;</span></div><div><span style=\"font-family: arial, helvetica, sans-serif; font-size: 14pt;\"><strong style=\"font-weight: bold;\"><em>Nắng mưa là bệnh của trời</em></strong></span><br><span style=\"font-family: arial, helvetica, sans-serif; font-size: 14pt;\"><strong style=\"font-weight: bold;\"><em>Tương tư là chuyện của tôi yêu nàng.</em></strong></span></div><div><span style=\"font-family: arial, helvetica, sans-serif; font-size: 14pt;\">Cái thời mà smartphone còn chưa xuất hiện, những người yêu nhau sẽ lén trao nhau từng bức thư tay để bày tỏ tâm tình. Thiều thương Mận nhưng không dám nói, viết thư tay còn chưa kịp trao thì đã bị cô giáo bắt được và đọc cho cả lớp nghe. “Những ngày ẩm ương, chưa lớn mà cũng chẳng còn nhỏ, có ai chưa từng dõi mắt nhìn theo một ai”.</span></div><div class=\"revis-content\" id=\"revis-987856976\" style=\"color: rgb(34, 34, 34); font-family: Verdana, Geneva, sans-serif; font-size: 15px;\"><ins class=\"adsbygoogle\" data-ad-client=\"ca-pub-8175304222497765\" data-ad-slot=\"8971687406\" data-ad-format=\"auto\" data-full-width-responsive=\"true\" style=\"background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial; text-decoration-line: none; display: block;\"></ins></div><h3 style=\"font-family: Roboto, sans-serif; color: rgb(17, 17, 17); margin: 27px 0px 17px; font-size: 22px; line-height: 30px;\"><span class=\"ez-toc-section\" id=\"Tinh_Cam_Gia_Dinh_Thieng_Lieng_Dang_Tran_Trong\"></span><span style=\"font-family: arial, helvetica, sans-serif; font-size: 14pt;\"><strong style=\"font-weight: bold;\">Tình Cảm Gia Đình Thiêng Liêng, Đáng Trân Trọng</strong></span><span class=\"ez-toc-section-end\"></span></h3><div><span style=\"font-family: arial, helvetica, sans-serif; font-size: 14pt;\">Tình anh em là một trong những chủ đề chính của truyện. Dù đôi lúc anh lớn là Thiếu có vô tình đối xử chưa tốt với em nhưng Tường vẫn luôn thương anh và lo lắng cho anh. Đi chơi về bị ba đánh, Thiều thì vắt chân lên cổ bỏ chạy còn Tường ở lại chịu trận thay cho anh hai. Tường thay anh làm hết các công việc trong nhà chỉ vì nghe mẹ bảo “để yên cho anh hai học bài”.</span></div><div><span style=\"font-family: arial, helvetica, sans-serif; font-size: 14pt;\">Chi tiết cảm động nhất truyện là khi anh hai bị đánh, Tường giúp anh trả thù nhưng lại tự mình chịu đòn vì không để anh bị oan. Ở cái tuổi dở trăng dở đèn, khi nội tâm và tính cách có nhiều biến động, Thiều đã không ít lần để cơn nóng giận bùng cháy và là tổn thương em trai.&nbsp;</span></div><div><span style=\"font-family: arial, helvetica, sans-serif; font-size: 14pt;\"><i>Mọi thứ chợt hiện ra trong đầu Thiều và cả nỗi ân hận như dao khắc vào tim Thiều, làm Thiều đứng sững như người mất hồn mà không hay rằng thằng Tường vẫn còn nằm ngửa dưới nền nhà. Thời gian như tiếp tục trôi qua khi thằng Thiều nghe tiếng thằng Tường kêu “Giúp em với, anh Hai!”.</i></span></div><div><span style=\"font-family: arial, helvetica, sans-serif; font-size: 14pt;\"><i>Khi Thiều xốc nách nó thì đã rên lên “Đau em!” và thấy nước mắt nó úa ra. Sau khi thấu hiểu sự tình, Thiều liền đi tìm ông Xung&nbsp; cứu Tường nhưng trước đi tường lại kêu anh hai bảo là: “Anh đừng nói với ông Xung là anh đánh em nhé, hãy bảo là em trèo cây bị tuột tay rơi xuống đất.”<br></i></span><h3 style=\"font-family: Roboto, sans-serif; color: rgb(17, 17, 17); margin: 27px 0px 17px; font-size: 22px; line-height: 30px;\"><span style=\"font-family: arial, helvetica, sans-serif; font-size: 14pt;\"><strong style=\"font-weight: bold;\">Tình Người Nơi Làng Quê Thân Thương</strong></span><span class=\"ez-toc-section-end\"></span></h3><div><span style=\"font-family: arial, helvetica, sans-serif; font-size: 14pt;\">Tôi thấy hoa vàng trên cỏ xanh cũng đem đến cho ta bài học về tình người nơi làng quê ấm áp. Cha của Mận chịu cảnh tù túng, trốn hàng năm trời trong căn gác bí ẩn xấp xệ cùng căn bệnh phong quái lạ. Mận – cô con gái hiếu thảo và tình cảm đã bỏ cả học bài để chăm sóc cha. Và bố mẹ hai anh em Thiều Tường đã ngỏ ý nhận Mận về nuôi khi nhà cô bé bị cháy, dù sự thật là cả gia đình cũng chẳng khá giả gì.</span></div><figure id=\"attachment_688\" aria-describedby=\"caption-attachment-688\" class=\"wp-caption aligncenter\" style=\"margin: 6px auto 0px; text-align: center; max-width: 100%; clear: both; color: rgb(34, 34, 34); font-family: Verdana, Geneva, sans-serif; font-size: 15px; width: 800px;\"><img decoding=\"async\" loading=\"lazy\" class=\"size-full wp-image-688\" src=\"https://revisach.com/wp-content/uploads/2020/07/review-sach-toi-thay-hoa-vang-tren-co-xanh-nguyen-nhat-anh.png\" alt=\"review-sach-toi-thay-hoa-vang-tren-co-xanh-nguyen-nhat-anh\" width=\"800\" height=\"530\" srcset=\"https://revisach.com/wp-content/uploads/2020/07/review-sach-toi-thay-hoa-vang-tren-co-xanh-nguyen-nhat-anh.png 800w, https://revisach.com/wp-content/uploads/2020/07/review-sach-toi-thay-hoa-vang-tren-co-xanh-nguyen-nhat-anh-300x199.png 300w, https://revisach.com/wp-content/uploads/2020/07/review-sach-toi-thay-hoa-vang-tren-co-xanh-nguyen-nhat-anh-768x509.png 768w, https://revisach.com/wp-content/uploads/2020/07/review-sach-toi-thay-hoa-vang-tren-co-xanh-nguyen-nhat-anh-696x461.png 696w, https://revisach.com/wp-content/uploads/2020/07/review-sach-toi-thay-hoa-vang-tren-co-xanh-nguyen-nhat-anh-634x420.png 634w, https://revisach.com/wp-content/uploads/2020/07/review-sach-toi-thay-hoa-vang-tren-co-xanh-nguyen-nhat-anh-600x398.png 600w\" sizes=\"(max-width: 800px) 100vw, 800px\" style=\"border: 0px; max-width: 100%; height: auto; margin-bottom: 0px; width: 696px; display: block;\"><figcaption id=\"caption-attachment-688\" class=\"wp-caption-text\" style=\"margin: 6px 0px 26px; font-size: 11px; font-style: italic; line-height: 17px; color: rgb(68, 68, 68);\">Poster phim Tôi thấy hoa vàng trên cỏ xanh</figcaption></figure><div><span style=\"font-family: arial, helvetica, sans-serif; font-size: 14pt;\"><i>Nửa khuya, lũ từ trên nguồn tràn về, mực nước từ từ dâng lên mấp mé mặt giường trong ánh mắt lo lắng của mẹ tôi. Cả nhà tôi leo hết lên giường, xách theo mấy chiếc đòn kê để ngồi cho khỏi ướt mông, co ro chờ trời sáng.</i></span></div><h2 style=\"font-family: Roboto, sans-serif; color: rgb(17, 17, 17); margin: 30px 0px 20px; font-size: 27px; line-height: 38px; text-align: justify;\"><span class=\"ez-toc-section\" id=\"Triet_Ly_Trong_Truyen_Toi_Thay_Hoa_Vang_Tren_Co_Xanh\"></span><span style=\"font-family: arial, helvetica, sans-serif; font-size: 18pt;\"><strong style=\"font-weight: bold;\">Triết Lý Trong Truyện Tôi Thấy Hoa Vàng Trên Cỏ Xanh</strong></span><span class=\"ez-toc-section-end\"></span></h2><div><span style=\"font-family: arial, helvetica, sans-serif; font-size: 14pt;\">Điểm khác biệt của Tôi thấy hoa vàng trên cỏ xanh so với những tác phẩm khác của Nguyễn Nhật Ánh là tác giả đã nói về những mặt xấu trong tính cách của cả những nhân vật chính. Ai cũng có những nét méo mó trong tâm hồn vì suy cho cùng thì ranh giới giữa thiện và ác thực ra rất mong manh. Thiều bồng bột, thiếu sót do không kiểm soát được cảm xúc của chính mình nên đôi khi đã cư xử tệ với em trai.&nbsp;</span></div><div><span style=\"font-family: arial, helvetica, sans-serif; font-size: 14pt;\">Tuổi thơ của những nhân vật trong truyện tuy không êm đềm, có gia đình phải trải qua những biến cố nhưng chính những thử thách ấy đã tạo nên con người chúng ta khi trưởng thành.</span></div><h2 style=\"font-family: Roboto, sans-serif; color: rgb(17, 17, 17); margin: 30px 0px 20px; font-size: 27px; line-height: 38px; text-align: justify;\"><span class=\"ez-toc-section\" id=\"Loi_Ket\"></span><span style=\"font-family: arial, helvetica, sans-serif; font-size: 18pt;\"><strong style=\"font-weight: bold;\">Lời Kết</strong></span><span class=\"ez-toc-section-end\"></span></h2><div><span style=\"font-family: arial, helvetica, sans-serif; font-size: 14pt;\">Truyện Tôi thấy hoa vàng trên cỏ xanh có nhiều nét trong sáng nhưng có những khúc trắc trở hay trầm buồn. Đó chính là cuộc sống. Có nhiều người nghiện truyện Nguyễn Nhật Ánh, có chăng bởi họ nhớ nhung những ngôn ngữ giản dị, gần gũi, tràn đầy hơi thở tuổi thơ trong từng câu chữ. Nếu có một lúc bạn thấy mệt mỏi vì cuộc sống hiện đại, hãy tìm đến Tôi thấy hoa vàng trên cỏ xanh để được thư giãn và trở vể tuổi thơ hồn nhiên, yên bình.</span></div></div></div>");
//		blog1.setBook(bookRepository.findByTitle("Tôi thấy hoa vàng trên cỏ xanh"));
//		blog1.setImage(imageThumbnail1);
//		blogRepository.save(blog1);
//
//		Blog blog2 = new Blog();
//		blog2.setTitle("Review truyện Your Name");
//		blog2.setSubTitle("Sự gắn kết xuyên không gian và thời gian.Nhân vật chính trong Your Name là Mitsuha, một cô nữ sinh 17 tuổi, sống ở một thị trấn nhỏ mang tên Itomori của một miền quê hẻo lánh. Mẹ cô mất từ khi cô còn nhỏ, cha cô sau cái chết của người vợ đã vô cùng đau lòng nên quyết không chấp nhận nối nghiệp gia đình, mà đi theo con đường chính trị, rời xa cô cùng đứa em gái và người bà trong căn nhà nhỏ.");
//		blog2.setContent("<div><h2 style=\"padding: 0px; margin: 25px 0px 20px; outline: none; list-style: none; border: 0px none; line-height: 43px; font-family: Helvetica; font-size: 33px; color: rgb(55, 55, 88); text-shadow: rgb(34, 24, 177) 0px 0px;\"><span id=\"i-your-name-duoc-lay-cam-hung-tu-dau\" style=\"padding: 0px; margin: 0px; outline: none; list-style: none; border: 0px none;\"><strong style=\"padding: 0px; margin: 0px; outline: none; list-style: none; border: 0px none;\">I. Your Name được lấy cảm hứng từ đâu?</strong></span></h2><div>Trong cuộc phỏng vấn báo chí, chính đạo diễn Makoto Shinkai cũng cho biết rằng&nbsp;<strong style=\"padding: 0px; margin: 0px; outline: none; list-style: none; border: 0px none;\">Your Name&nbsp;</strong>được lấy cảm hứng từ một truyện dân gian Nhật Bản từ thế kỉ 12, mang tên Torikaebaya Monogatari và bộ Manga Hono no Tenkousei ra mắt vào năm 1983.</div></div><div><div>Torikaebaya Monogatari kể về một cặp sinh đôi trong đó nam thì bị hiểu nhầm là nữ, và nữ thì hiểu nhầm là nam.</div><div>Còn Hono no Tenkousei thì lại là câu chuyện của một cặp nam nữ trung học do trượt ngã ở đền thần và khi tỉnh dậy phát hiện linh hồn đang ở một cơ thể khác.</div><h2 style=\"padding: 0px; margin: 25px 0px 20px; outline: none; list-style: none; border: 0px none; line-height: 43px; font-family: Helvetica; font-size: 33px; color: rgb(55, 55, 88); text-shadow: rgb(34, 24, 177) 0px 0px;\"><span id=\"ii-danh-gia-ca-nhan\" style=\"padding: 0px; margin: 0px; outline: none; list-style: none; border: 0px none;\"><strong style=\"padding: 0px; margin: 0px; outline: none; list-style: none; border: 0px none;\">II. Đánh giá cá nhân</strong></span></h2><div>Your Name quả là một câu chuyện vô cùng thú vị, nó đưa chúng ta rời xa chốn Tokyo sầm uất, phồn hoa để đến với Itomori thanh bình. Đây chính là không gian chủ yếu bao trùm cả tác phẩm.</div><div><img decoding=\"async\" class=\"aligncenter size-full wp-image-39148 entered lazyloaded\" src=\"https://blogchiasekienthuc.com/wp-content/uploads/2020/06/review-truyen-your-name-3.png\" alt=\"review-truyen-your-name (3)\" width=\"730\" height=\"559\" data-lazy-src=\"https://blogchiasekienthuc.com/wp-content/uploads/2020/06/review-truyen-your-name-3.png\" data-ll-status=\"loaded\" style=\"padding: 0px; margin: 5px auto; outline: none; list-style: none; border-width: 0px; border-color: initial; border-image: initial; max-width: 100%; height: auto; clear: both; box-shadow: rgb(9, 144, 192) 3px 3px 5px; border-radius: 0px; display: block;\"></div><h3 style=\"padding: 0px; margin: 25px 0px 20px; outline: none; list-style: none; border: 0px none; line-height: 43px; font-family: Helvetica; font-size: 29px; color: rgb(55, 55, 88); text-shadow: rgb(34, 24, 177) 0px 0px;\"><span id=\"1-nhan-vat-chinh-voi-mot-phong-thai-tuoi-noi-loan\" style=\"padding: 0px; margin: 0px; outline: none; list-style: none; border: 0px none;\"><strong style=\"padding: 0px; margin: 0px; outline: none; list-style: none; border: 0px none;\">#1. Nhân vật chính với một phong thái “tuổi nổi loạn”</strong></span></h3><div>Nhân vật chính trong&nbsp;<strong style=\"padding: 0px; margin: 0px; outline: none; list-style: none; border: 0px none;\">Your Name&nbsp;</strong>là Mitsuha, một cô nữ sinh 17 tuổi, sống ở một thị trấn nhỏ mang tên Itomori của một miền quê hẻo lánh.</div><div>Mẹ cô mất từ khi cô còn nhỏ, cha cô sau cái chết của người vợ đã vô cùng đau lòng nên quyết không chấp nhận nối nghiệp gia đình, mà đi theo con đường chính trị, rời xa cô cùng đứa em gái và người bà trong căn nhà nhỏ.</div><div>Giống như bao cô cậu thiếu niên trong thời kì “tuổi nổi loạn”, Mitsuha chán ngán cuộc sống Itomori yên tĩnh và muốn tới Tokyo nhộn nhịp.</div><div>Cô liên tục lải nhải “ca khúc than vãn”: “Ôiii, tớ chỉ muốn tốt nghiệp cho nhanh rồi đi Tokyo. Thị trấn này chật chội và ngột ngạt quá!” và thậm chí là than cả với thần linh “Con ghét cái thị trấn này! Con ghét cuộc sống này! Kiếp sau xin hãy cho con trở thành một anh chàng đẹp trai ở Tokyo …”</div><div>Tuy nhiên một điều kỳ diệu đã xảy ra, cầu được ước thấy, chẳng cần phải chờ đến kiếp sau, Mitsuha bất ngờ tỉnh dậy trong hình hài của một chàng trai có tên Taki ở Tokyo luôn bận rộn với việc học và làm thêm.</div><div>Sau những ngày tháng sống với thân thể mới, Mitsuha và Taki nhận ra đây không phải là một giấc mơ, họ đã hoàn toàn hoán đổi thân thể với nhau qua giấc ngủ một cách bất ngờ mà không hề rõ nguyên nhân.</div><div>Từ đây, cả Mitsuha cùng Taki đều bộc lộ rõ tính cách “nổi loạn” của mình. Ban đầu, họ vô cùng bất ngờ nhưng rồi cũng làm quen dần với cuộc sống mới đồng thời giúp đỡ lẫn nhau, đặt ra những luật lệ để bảo vệ cuộc sống của mình, hay thậm chí là gửi cả mấy trò chơi khăm để “cà khịa” đối phương.</div><div>Và dần dần, họ nhận ra mình có tình cảm với người còn lại mặc dù họ chưa từng gặp mặt tận mắt, và rồi cả hai quyết định tìm cách để gặp nhau.</div><div>Có thể nói, việc hoán đổi liên tục đã tạo ra một tia sáng mới trong cuộc sống nhàm chán của cả hai nhân vật, nó giống như một sợi dây bện để gắn kết Mitsuha và Taki.</div><h3 style=\"padding: 0px; margin: 25px 0px 20px; outline: none; list-style: none; border: 0px none; line-height: 43px; font-family: Helvetica; font-size: 29px; color: rgb(55, 55, 88); text-shadow: rgb(34, 24, 177) 0px 0px;\"><span id=\"2-nut-that-va-cao-trao\" style=\"padding: 0px; margin: 0px; outline: none; list-style: none; border: 0px none;\"><strong style=\"padding: 0px; margin: 0px; outline: none; list-style: none; border: 0px none;\">#2. Nút thắt và cao trào</strong></span></h3><div>Một điểm sáng nữa của light novel&nbsp;<strong style=\"padding: 0px; margin: 0px; outline: none; list-style: none; border: 0px none;\">Your Name&nbsp;</strong>đó là nút thắt trong câu chuyện khi Taki nhận ra được sự tồn tại mơ hồ giống như hồn ma của Mitsuha khi hình ảnh một ngôi sao chổi xuất hiện cắt ngang qua bầu trời.</div><div>Đây cũng chính là giây phút cả hai mất liên lạc hoàn toàn với nhau, việc hoán đổi của họ bỗng bị chấm dứt.</div><div>Và rồi cao trào của&nbsp;<strong style=\"padding: 0px; margin: 0px; outline: none; list-style: none; border: 0px none;\">Your Name&nbsp;</strong>bắt đầu được đẩy lên khi Taki phát hiện ra sự thật, cậu tìm đủ mọi cách để kết nối lại với Mitsuha và rồi khoảnh khắc tuyệt đẹp mang tên Takaware doki trong phim đã một lần nữa tái hiện trong phiên bản light novel, Taki cùng Mitsuha đã xuyên không gian, vượt thời gian để gặp được nhau.</div><h3 style=\"padding: 0px; margin: 25px 0px 20px; outline: none; list-style: none; border: 0px none; line-height: 43px; font-family: Helvetica; font-size: 29px; color: rgb(55, 55, 88); text-shadow: rgb(34, 24, 177) 0px 0px;\"><span id=\"3-goi-nho-den-tham-kich-dong-dat-kinh-hoang\" style=\"padding: 0px; margin: 0px; outline: none; list-style: none; border: 0px none;\"><strong style=\"padding: 0px; margin: 0px; outline: none; list-style: none; border: 0px none;\">#3. Gợi nhớ đến thảm kịch động đất kinh hoàng</strong></span></h3><div>Bên cạch câu chuyện du hành thời gian đầy thú vị, light novel&nbsp;<strong style=\"padding: 0px; margin: 0px; outline: none; list-style: none; border: 0px none;\">Your Name&nbsp;</strong>còn gợi cho người đọc nhớ đến trận động đất kinh hoàng của Nhật Bản vào năm 2011, nó đã cướp đi sinh mạng của 16 nghìn người và gây ra không ít hoang mang đến tinh thần con người Nhật Bản.</div><div>Trong tác phẩm, người đọc có thể cảm nhận được cảm giác từng đầu ngón tay của Taki run sợ khi phát hiện ra Mitsuha đã thiệt mạng trong vụ rơi thiên thạch ba năm về trước, hay hình dung ra cảnh Mitsuha bất lực khi đứng nhìn viên thiên thạch nghiền nát chính mình.</div><div>Mặc dù đây chỉ là một chi tiết rất nhỏ nhưng nó đã nói ra nỗi ám ảnh của những người dân Nhật Bản lúc bấy giờ.</div><h2 style=\"padding: 0px; margin: 25px 0px 20px; outline: none; list-style: none; border: 0px none; line-height: 43px; font-family: Helvetica; font-size: 33px; color: rgb(55, 55, 88); text-shadow: rgb(34, 24, 177) 0px 0px;\"><span id=\"iii-sau-khi-doc-xong-cuon-sach-nay8230\" style=\"padding: 0px; margin: 0px; outline: none; list-style: none; border: 0px none;\"><strong style=\"padding: 0px; margin: 0px; outline: none; list-style: none; border: 0px none;\">III. Sau khi đọc xong cuốn sách này…</strong></span></h2><h3 style=\"padding: 0px; margin: 25px 0px 20px; outline: none; list-style: none; border: 0px none; line-height: 43px; font-family: Helvetica; font-size: 29px; color: rgb(55, 55, 88); text-shadow: rgb(34, 24, 177) 0px 0px;\"><span id=\"1-ban-se-thich\" style=\"padding: 0px; margin: 0px; outline: none; list-style: none; border: 0px none;\"><strong style=\"padding: 0px; margin: 0px; outline: none; list-style: none; border: 0px none;\">#1. Bạn sẽ thích</strong></span></h3><ol style=\"padding: 0px; margin: 0px 0px 21px 18px; outline: none; list-style: none; border: 0px none; line-height: 30px; color: rgb(10, 0, 10); font-family: Roboto; font-size: 17px;\"><li style=\"padding: 0px; margin: 0px 0px 9px 28px; outline: none; list-style: outside decimal; border: 0px none; line-height: 34px; letter-spacing: 0.4px; word-spacing: 3px;\">Một câu chuyện đầy cảm xúc và ý nghĩa, khi thì đem đến cho người đọc cảm giác vui vẻ, hài hước, khi thì lại chan chứa nỗi buồn man mác.</li><li style=\"padding: 0px; margin: 0px 0px 9px 28px; outline: none; list-style: outside decimal; border: 0px none; line-height: 34px; letter-spacing: 0.4px; word-spacing: 3px;\">Miêu tả một cách thú vị, sâu sắc tính cách của từng nhân vật.</li><li style=\"padding: 0px; margin: 0px 0px 9px 28px; outline: none; list-style: outside decimal; border: 0px none; line-height: 34px; letter-spacing: 0.4px; word-spacing: 3px;\">Các yếu tố kỳ ảo đan xen với nhau một cách hài hòa, tạo nên cảm giác tò mò, hứng thú cho người đọc.</li><li style=\"padding: 0px; margin: 0px 0px 9px 28px; outline: none; list-style: outside decimal; border: 0px none; line-height: 34px; letter-spacing: 0.4px; word-spacing: 3px;\">Mang đậm bản sắc văn hóa Nhật Bản.</li></ol><h3 style=\"padding: 0px; margin: 25px 0px 20px; outline: none; list-style: none; border: 0px none; line-height: 43px; font-family: Helvetica; font-size: 29px; color: rgb(55, 55, 88); text-shadow: rgb(34, 24, 177) 0px 0px;\"><span id=\"2-co-the-ban-se-khong-thich\" style=\"padding: 0px; margin: 0px; outline: none; list-style: none; border: 0px none;\">#2.&nbsp;<strong style=\"padding: 0px; margin: 0px; outline: none; list-style: none; border: 0px none;\">Có thể bạn sẽ không thích</strong></span></h3><ol style=\"padding: 0px; margin: 0px 0px 21px 18px; outline: none; list-style: none; border: 0px none; line-height: 30px; color: rgb(10, 0, 10); font-family: Roboto; font-size: 17px;\"><li style=\"padding: 0px; margin: 0px 0px 9px 28px; outline: none; list-style: outside decimal; border: 0px none; line-height: 34px; letter-spacing: 0.4px; word-spacing: 3px;\">Cực kỳ “hack não”, yêu cầu vừa đọc kết hợp nghiền ngẫm lâu.</li><li style=\"padding: 0px; margin: 0px 0px 9px 28px; outline: none; list-style: outside decimal; border: 0px none; line-height: 34px; letter-spacing: 0.4px; word-spacing: 3px;\">Yêu cầu bạn phải có một chút ít kiến thức về văn hóa người Nhật (thực ra thì không cần cũng vẫn có thể hiểu nếu như bạn chịu suy nghĩ kỹ các tình tiết trong tác phẩm).</li><li style=\"padding: 0px; margin: 0px 0px 9px 28px; outline: none; list-style: outside decimal; border: 0px none; line-height: 34px; letter-spacing: 0.4px; word-spacing: 3px;\">Không đạt đến đỉnh cao của việc đưa đẩy cảm xúc hay việc diễn đạt nó như trong phiên bản Anime gốc.</li></ol><div>Suy cho cùng, light novel&nbsp;<strong style=\"padding: 0px; margin: 0px; outline: none; list-style: none; border: 0px none;\">Your Name&nbsp;</strong>cũng là một cuốn tiểu thuyết rất đáng để cho bạn đọc và nếu như bạn thích nó thì mình khuyên bạn hãy đọc thêm phiên bản ngoại truyện là&nbsp;<strong style=\"padding: 0px; margin: 0px; outline: none; list-style: none; border: 0px none;\">Your Name.&nbsp;</strong><strong style=\"padding: 0px; margin: 0px; outline: none; list-style: none; border: 0px none;\">Another Side: Earthbou&nbsp;</strong>để khám phá thêm về từng nhân vật trong tác phẩm với những tình tiết chưa bao giờ xuất hiện trong cả Anime lẫn light novel của&nbsp;<strong style=\"padding: 0px; margin: 0px; outline: none; list-style: none; border: 0px none;\">Your Name</strong>.</div></div>");
//		blog2.setBook(bookRepository.findByTitle("Your Name"));
//		blog2.setImage(imageThumbnail2);
//		blogRepository.save(blog2);
//
//		Blog blog3 = new Blog();
//		blog3.setTitle("Review sách Mắt Biếc");
//		blog3.setSubTitle("Một Tác Phẩm Phải Dùng Cả Quãng Thời Gian Thanh Xuân Để Cảm Nhận. “Tôi gửi tình yêu cho mùa hè, nhưng mùa hè không giữ nổi. Mùa hè chỉ biết ra hoa, phượng đỏ sân trường và tiếng ve nỉ non trong lá. Mùa hè ngây ngô, giống như tôi vậy. Nó chẳng làm được những điều tôi kí thác. Nó để Hà Lan đốt tôi, đốt rụi. Trái tim tôi cháy thành tro, rơi vãi trên đường về.” ");
//		blog3.setContent("<div><div dir=\"ltr\" style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px; font-family: Roboto; font-size: 14px; text-align: justify; font-weight: bold;\">“Tôi gửi tình yêu cho mùa hè, nhưng mùa hè không giữ nổi. Mùa hè chỉ biết ra hoa, phượng đỏ sân trường và tiếng ve nỉ non trong lá. Mùa hè ngây ngô, giống như tôi vậy. Nó chẳng làm được những điều tôi kí thác. Nó để Hà Lan đốt tôi, đốt rụi. Trái tim tôi cháy thành tro, rơi vãi trên đường về.”</div><div dir=\"ltr\" style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent; margin: 0px; padding: 0px; font-family: Roboto; font-size: 14px; text-align: justify; font-weight: bold;\"><br style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\"></div><div><i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">“Mắt Biếc”</i>&nbsp;là một trong những tác phẩm nổi tiếng nhất của nhà văn Nguyễn Nhật Ánh. Nếu ở các tác phẩm khác của ông, độc giả thường bắt gặp cốt truyện với nội trong sáng, ngây thơ dành cho trẻ nhỏ, thì đến với&nbsp;<i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">“Mắt Biếc”</i>, nó như một luồng gió mới dành cho người đọc. Vẫn là những ngôn từ giản đơn, gần gũi nhưng việc xây dựng hình tượng và nội dung có phần khác. Nó nói về tình yêu của những đứa trẻ, của sự trưởng thành, tình yêu với quê hương và hơn cả là sự hy sinh vô bờ bến, sự vĩ đại trong tình yêu.&nbsp;<br style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\"></div><div><i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\"><span style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent; font-weight: 700;\">Tiêu đề và cách thiết kế bìa nhẹ nhàng</span></i></div><div>Tiêu đề cùng bìa sách chắc hẳn sẽ làm cho các bạn cảm thấy ấn tượng ngay. Vỏn vẹn hai chữ&nbsp;<i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">“Mắt Biếc”</i>&nbsp;nhưng đã mở ra rất nhiều cảm nhận cho độc giả bởi ai cũng biết mắt biếc là một đôi mặt đẹp, nhưng buồn. Tiêu đề đã phần nào gợi nên một cốt truyện có nội dung phảng phất nỗi buồn. Phông xanh chủ đạo với hình ảnh một người con trai ngồi đánh đàn bên cô gái dưới tán cây được phác họa một cách đơn giản, mang tới một cảm giác dễ chịu.</div><div><i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">Một câu chuyện mang lại quá nhiều cảm xúc, để lại ám ảnh cho người đọc&nbsp;</i></div><div>Câu chuyện kể về cuộc đời của một chàng trai tên Ngạn - nhân vật chính trong cuốn sách, một cậu bé sinh ra và lớn lên trong một ngôi làng tên là Đo Đo ở vùng quê Quảng Nam đậm tình sâu nghĩa. Tuổi thơ của cậu ngoài gắn bó với những buổi trưa hè oi ả, các sạp tạp hóa được được bày biện những món hàng tạp hóa xinh xắn lung linh, những trận roi nổ đom đóm mắt của bố,... thì cậu còn có bên cạnh mình một cô bạn hàng xóm - Hà Lan - với đôi mắt đẹp như&nbsp;<i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">\"Mắt Biếc\"</i>. Ngạn bộc bạch:&nbsp;<i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">&nbsp;“Đó là đôi mắt có hàng mi dài, lúc nào cũng mở to, hồn nhiên và ngơ ngác. Và sau này cũng đôi mắt đó làm khổ tôi ghê gớm”&nbsp;</i>&nbsp;</div><div>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">“Uống nhầm một ánh mắt. Cơn say theo cả đời</i></div><div><i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</i>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">Thương thầm một nụ cười, cả một đời phiêu lãng”</i></div><div>Câu thơ trên có lẽ sinh ra là dành cho Ngạn. Bởi vì yêu, say đắm trước vẻ đẹp của đôi mắt ấy, mà ngay từ nhỏ, Ngạn đã bất chấp mọi thứ để bảo vệ Hà Lan và làm Hà Lan vui lòng. Từ khi học lớp vỡ lòng, vì bênh vực Hà Lan, Ngạn đã hạ gục thắng Hòa - một kẻ mà trong lớp chẳng ngán bắt nạt ai và chẳng ai trong lớp muốn động vào. Để rồi sau đó, Ngạn bị thầy Phu phạt nhảy cóc mười vòng sân giữa trời nóng tới ngất xỉu. Ngạn cũng tham gia vào những trận đánh nhau long trời lở đất trong vườn nhà ông Cửu Hoành để giành lấy cho Hà Lan những trái thị hiếm hoi. Sau này, khi lên cấp 2, cũng vì thỏa mãn ý thích của Hà Lan, người con trai ấy lại sẵn sàng nện nhau nhừ tử vì cái dùi trống. Và cũng vì Hà Lan thích những cái trứng chim sẻ mà Ngạn cũng chấp nhận đầu u những cục to và chảy máu mũi vì té khi trèo lên chiếc thang lỏng lẻo để lấy bằng được trứng chim cho Hà Lan. Câu chuyện mở đầu với những hình ảnh nhẹ nhàng cùng những âm thanh trong trẻo bình an nhất. Một đôi thanh mai trúc mã tưởng chừng rồi sẽ có kết quả tốt đẹp, ăm ắp, đủ đầy, chứa chan tình cảm. Bởi lẽ tình yêu đã nảy sinh dần dần trong Ngạn bắt nguồn từ tình bạn đẹp như mơ của Ngạn và Hà Lan. Thế nhưng không, đến khi lớn hơn một chút, cả hai cùng ra thành phố học tập, trong khi tấm lòng của Ngạn vẫn chỉ duy nhất hướng về Hà Lan, hường về làng Đo Đo thì cô bạn lại không cưỡng lại được trước những cám dỗ của thành thị xa hoa.</div><div>Ngạn và Hà Lan bắt đầu có những đối lập trong suy nghĩ. Ở ngưỡng cửa của tuổi mới lớn, thế giới của cô bé lại là sự háo hức, tò mò về thành thị. Đôi chân của Hà Lan chạy theo những điều mới mẻ, đôi chân của Ngạn lại chậm rãi về với những điều xưa cũ. Thế giới trong Hà Lan là thành thị đầy màu sắc thì Ngạn lại là màn trời đầy sao của làng Đo Đo. Hà Lan muốn đến với sự ồn ào của thành thị, Ngạn lại bỏ quên hồn mình ở làng. Hà Lan khám phá ánh sáng của đô thị, Ngạn mơ màng ở đồi Sim. Thành phố đầy ánh điện, đầy hiện đại; làng Đo Đo nhỏ bé và yên bình. Cứ thế dần dần với những trái ngược kia, hai người cứ ngày một xa nhau, cảm giác như hai đường thẳng song song mà ngay từ điểm xuất phát đã trái ngược nhau…, có lẽ Ngạn đủ thông minh để biết con đường của Hà Lan mong muốn nhưng anh không thể khôn nguôi về những kỉ niệm ấy. Bởi lẽ khoảng thời gian gắn bó thời xưa ấy đã quá lâu để rồi hình thành một thói quen khó bỏ, Hà Lan trong Ngạn mãi là cô bé có đôi mắt đẹp thơ ngây, trong vắt như một tờ giấy đến nỗi anh quên mất rằng người anh yêu đến đau lòng đã thay đổi.&nbsp;<br><div>Ngạn trải lòng mình với âm nhạc, âm nhạc ở đây đã như một người bạn tri kỷ sưởi ấm trái tim của kẻ si tình. Âm nhạc nói hộ nỗi lòng, Ngạn viết ra những bản nhạc và bản nhạc nào cũng đau đáu về Hà Lan. Ngạn gửi tình yêu vào nhạc, gửi vào mùa hè nhưng mùa hè không giữ nổi. Hoa phượng vẫn cứ đỏ, thời gian vẫn cứ trôi, cảnh vật vẫn thế chỉ là thiếu bóng Hà Lan.&nbsp;</div><div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">&nbsp;</i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">“Gửi mùa hè</i></div><div><i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">Giữ hộ chút tình yêu</i></div><div><i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">Khi chia xa</i></div><div><i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</i>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; V<i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">ẫn nhớ ngày gặp lại</i></div><div><i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</i>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;<i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">Lúc ấy em có là cô gái</i></div><div><i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;</i>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;<i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">Đốt tôi bằng ngọn lửa&nbsp;</i></div><div><i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</i>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;<i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">Của riêng em?”</i></div><div>Phượng đỏ rực góc trời như một đốm lửa nhỏ, nhưng có lẽ chưa đủ thiêu rụi mà ánh mắt Hà Lan chỉ cần lướt qua đã đủ thiêu rụi cõi lòng. Hà Lan thay đổi đến ngỡ ngàng, Đứng trước sự thay đổi của Hà Lan, Ngạn càng trở nên đau lòng, Ngạn đã thể hiện nỗi buồn vô bờ và ước mong quay trở lại như thời xưa qua lời bài hát&nbsp;<i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">“Thà như ngày thơ ấu”</i>&nbsp;tới mức:</div><div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">&nbsp;&nbsp;</i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">“Nhớ ngày xưa tuổi nhỏ</i></div><div><i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">Ta suốt ngày bên nhau</i></div><div><i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">Kể bao điều thầm kín</i></div><div><i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</i>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">Lòng có ngại gì đâu</i></div><div><i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">Bây giờ sao quá khó</i></div><div><i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</i>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">Lòng anh và tình em</i></div><div><i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</i>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">Chút hương thầm trong gió</i></div><div><i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">Biết ngày nào bay lên</i></div><div><i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">Nếu biết tình như thế</i></div><div><i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">Chẳng lớn lên làm gì</i></div><div><i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">Thà như ngày thơ ấu</i></div><div><i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">Hai đứa cầm tay đi”</i></div><div>Khoảng cách địa lý đã xa, khoảng cách của tâm hồn lại càng xa hơn, khi Hà Lan phải lòng Dũng. Dũng mang hình thái phong trần, lịch lãm và mới mẻ, như một làn gió mới thổi qua đời Hà Lan. Hà Lan đã yêu Dũng bằng trái tim vô bờ của một người con gái. Dũng như đại diện cho thành thị, Ngạn đại diện cho cội nguồn quê hương. Dũng mang những bụi bặm của thành phố, của sự ồn ào nhộn nhịp, đó là những cái mà Hà Lan theo đuổi. Phải! Hà Lan đã chọn một gã thanh niên nhà giàu, sành điệu, giỏi võ nhưng lại thiếu đứng đắn, một kẻ chuộng tự do, luôn nuông chiều bản thân để gửi gắm tình cảm vào. Nhưng Ngạn vẫn đứng đó.</div><div>Ngạn đau lòng, Ngạn xảy ra mâu thuẫn xung đột trong cảm xúc. Ngạn không muốn Hà Lan yêu Dũng, Ngạn cũng tự thú nhận với bản thân cảm thấy vui khi thấy Dũng rời bỏ Hà Lan để yêu một người con gái khác. Nhưng Ngạn lại muốn Hà Lan hạnh phúc, cả kể là hạnh phúc bên Dũng. Anh chấp nhận nghe những lời giãi bày, tâm sự của người con gái anh yêu đang kể về một người đàn ông khác, anh tự nguyện là một điểm tựa để bất cứ khi nào cô tìm tới. Rồi Dũng lại quay lại với Hà Lan, trái tim Ngạn lại như có ai đó dùng dao rạch một nhát sâu, sâu tới mức không thở được. Và đỉnh điểm của nỗi đau chính là Hà Lan mang thai với Dũng, nhưng bị hắn ruồng bỏ. Cô đành gửi con về cho bà ngoại chăm sóc và đặt tên là Trà Long. Tuy hiểu rõ tình yêu của Ngạn dành cho mình, Hà Lan vẫn không đáp lại vì chỉ cô mới hiểu rõ suy nghĩ của bản thân nhất. Bản thân chúng ta cũng rất sợ khi có người đối xử quá tốt vì thực lòng mình không thể nghĩ ra được làm thế nào để trả ơn họ, nếu Hà Lan lấy Ngạn thì sẽ càng độc ác vì vốn dĩ tình cảm của Hà Lan với Ngạn chỉ là sự cảm động, chỉ là người bạn tâm giao. Làm sao có thể chắc chắn Hà Lan lấy Ngạn về bi kịch sẽ giảm đi? Khi không hề có tình cảm lấy về chỉ làm khổ nhau mà thôi…</div><div><img src=\"https://static.ybox.vn/2019/11/5/1575011309733-m%E1%BA%AFt%20bi%E1%BA%BFc.jpg\" style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent; border: 0px; max-width: 100%; height: auto; display: block; margin: 0px auto;\"></div><div>Dù bị Hà Lan từ chối nhưng Ngạn vẫn dành hết tình yêu của mình cho bé Trà Long. Ngạn chăm sóc và thương yêu Trà Long hết mình. Trà Long có gương mặt và đôi mắt biếc giống y hệt Hà Lan khi còn trẻ. Nhưng trái với mẹ mình, Trà Long một lòng hướng về quê nhà, tâm hồn cô sinh ra cũng dành cho làng Đo Đo, cô yêu quê, yêu những thứ giản dị, đời thường, y hệt Ngạn. Và Trà Long cũng yêu Ngạn! Còn Ngạn, Ngạn cũng có tình cảm với Trà Long.</div><div>Đọc đến đây, tôi cảm giác mình bị lạc vào chốn mê cung xa lạ nào đó của tình cảm, của yêu và thương. Rốt cuộc tôi vẫn không hiểu rõ tình cảm của Trà Long đối với Ngạn mang tên chi? Là yêu ư? Hay chính là thương, là kính trọng, quý mến? Và chính Ngạn đã xem Trà Long là gì ta cũng không rõ nữa. Là đứa cháu bé bỏng, hay là người yêu hay chỉ là người thế thân cho mối tình đầu của Ngạn mà anh mãi chẳng thể quên được? Hay chính là người sẽ tiếp tục vẽ tiếp cuộc đời dở dang của Ngạn?</div><div><i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">Một cái kết khắc khoải, nao lòng!</i><br style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\"></div><div>Suốt bao nhiêu năm, Ngạn giận có giận, trách có trách nhưng chưa một lần nào anh hết thương Hà Lan. Cuối cùng Ngạn chọn ra đi, bỏ lại Trà Long và làng quê. Bỏ lại cả Hà Lan và đôi mắt biếc.</div><div>Một nỗi buồn chơi vơi, mơ hồ, sự thấu hiểu và cảm thông của người đọc với nỗi lòng của Ngạn. Cuộc đời của anh dành để yêu và ôm lấy nỗi đau. Đến cuối cùng cũng dốc hết lòng mà giữ trọn một nắm tình con dành cho Hà Lan, dành cho Mắt Biếc.</div><div>Ngạn chọn ra đi, có lẽ chính bởi vì sau anh đã hiểu, rốt cuộc rồi Trà Long cũng chỉ là cái bóng của Hà Lan và tình cảm của anh với Trà Long là không thể tiếp tục được. Anh quyết định giữ trọn hình ảnh Mắt Biếc đẹp nhất trong trái tim mình...</div><div>Một nốt lặng trong bài tình ca, một nốt lặng nuối tiếc, đau lòng!</div><div><i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">Vì sao nói đây là một tác phẩm phải dùng cả quãng thời gian thanh xuân để cảm nhận?</i><br style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\"></div><div>Nói đây là một tác phẩm phải dùng cả quãng thời gian thanh xuân để cảm nhận là bởi vào năm bạn 15 tuổi, khi bạn đọc câu chuyện này, có lẽ bạn sẽ phải thốt lên rằng&nbsp;<i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">“Tại sao Ngạn lại phải chịu khổ như vậy? Tại sao không bỏ Hà Lan đi”&nbsp;</i>hay&nbsp;<i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">“Tại sao Ngạn tốt như vậy mà Hà Lan không yêu Ngạn mà lại yêu Dũng”.</i>&nbsp;Nhưng sau này, khi đã trải qua vài ba mối tình, đã hiểu hy sinh cho tình yêu là như thế nào, đọc lại câu chuyện, biết đâu bạn lại nói “Nếu giả sử có Ngạn và Hà Lan ngoài đời thực, có lẽ tôi cũng không khuyên Ngạn từ bỏ mối tình này đi. Bởi với Ngạn, Hà Lan là cả một bầu trời, là cả một cuộc đời, là sinh ra để thương, để nhớ và Ngạn bất chấp làm mọi thứ chỉ để Hà Lan được hạnh phúc dù cho bản thân mình có đau lòng.” Với mỗi giai đoạn và thời điểm khác nhau, bạn sẽ có một cảm nhận riêng cho các nhân vật và nội dung cho cốt truyện. Đây là một trong những điều làm nên sự tuyệt vời và khác biệt của tác phẩm.&nbsp;</div><div><img src=\"https://static.ybox.vn/2019/11/5/1575012369968-m%E1%BA%AFt%20bi%E1%BA%BFc%206.jpg\" style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent; border: 0px; max-width: 100%; height: auto; display: block; margin: 0px auto;\"></div><div><i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">Một bức tranh làng quê rất đẹp, rất thương!</i><br style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\"></div><div>Và điều đặc biệt cuối cùng trong cuốn sách mà tác giả muốn chia sẻ với bạn đọc đó chính là tình yêu quê hương. Nó bao trùm lên tất cả mọi thứ, bao trùm lên cả tình yêu của Ngạn dành cho Hà Lan. Nguyễn Nhật Ánh đã vẽ nên một Đo Đo rất riêng, rất quê hương trong cuốn sách. Một làng quê an bình, nên thơ nằm lặng im bên đồi sim, bên đồng cỏ, vô cùng nên thơ. Một mảnh đất bình dị, nghèo khó nhưng lại là mảnh đất của tuổi thơ nơi có trời xanh cao vời vợi, trong suốt như pha lê, có hoa dâm bụt đỏ chói, có những quả thị vàng ươm và có cả tình bạn trong sáng, tình yêu nghề tha thiết. Tất cả tạo nên một bức tranh đẹp đẽ về một chốn đồng quê nơi đất nước Việt Nam này. Ngạn lúc nào cũng đau đáu một tình cảm duy nhất cho Hà Lan có lẽ cũng bởi vì cô gái ấy cũng là một phần của quê hương. Đôi mắt của Hà Lan, Ngạn soi mình vào trong đôi mắt trong veo kia là những kỉ niệm từ thời còn nhỏ hai đứa ở bên nhau, những hình ảnh của làng quê mộc mạc.</div><div><i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">Lời kết</i><br style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\"></div><div>Mắt Biếc để lại quá nhiều cung bậc cảm xúc khác nhau cho người đọc. Gập trang sách cuối cùng lại, sau cùng ta thấy Ngạn mới là người đau khổ nhất. Phải chăng đôi mắt Hà Lan buồn vì trong đó, chưa hẳn là nó phản chiếu cuộc đời của cô, mà bởi nó phản chiếu cuộc đời của Ngạn. Đây thực sự là một tác phẩm phải dùng cả quãng thời gian thanh xuân để cảm nhận. Bởi, năm 15 tuổi, bạn có thể hỏi&nbsp;<i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">“Vì sao Ngạn không bỏ Hà Lan đi?”</i>&nbsp;hay&nbsp;<i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">“Tại sao Ngạn tốt như vậy mà Hà Lan không yêu Ngạn mà lại yêu Dũng”</i>. Nhưng sau này, khi đã trải qua vài ba mối tình, đã hiểu hy sinh cho tình yêu là như thế nào, đọc lại câu chuyện, biết đâu bạn lại nói&nbsp;<i style=\"background-repeat: no-repeat; -webkit-tap-highlight-color: transparent;\">“Nếu giả sử có Ngạn và Hà Lan ngoài đời thực, có lẽ tôi cũng không khuyên Ngạn từ bỏ mối tình này đi. Bởi với Ngạn, Hà Lan là cả một bầu trời, là cả một cuộc đời, là sinh ra để thương, để nhớ và Ngạn bất chấp làm mọi thứ chỉ để Hà Lan được hạnh phúc dù cho bản thân mình có đau lòng.”</i>&nbsp;Với mỗi giai đoạn và thời điểm khác nhau, bạn sẽ có một cảm nhận riêng cho các nhân vật và nội dung cho cốt truyện. Đây là một trong những điều làm nên sự tuyệt vời và khác biệt của tác phẩm.&nbsp;</div></div></div>");
//		blog3.setBook(bookRepository.findByTitle("Mắt Biếc"));
//		blog3.setImage(imageThumbnail3);
//		blogRepository.save(blog3);
//
//		Blog blog4 = new Blog();
//		blog4.setTitle("Review sách Marketing căn bản");
//		blog4.setSubTitle("Các bạn đã nghe câu chuyện “Bán lược cho sư”. Đó là bài học về Marketing kinh điển và được nhắc đến trong giới kinh doanh hiện nay. Đồng thời bài học này cũng là ví dụ điển hình trong các bài giảng của giảng viên hoặc các chuyên gia các khóa học đào tạo nổi tiếng hiện nay lựa chọn để bắt đầu môn học chuyên đề về Marketing.");
//		blog4.setContent("<h2 id=\"ftoc-heading-1\" class=\"ftwp-heading\" style=\"box-sizing: inherit; border: 0px; font-family: &quot;Nunito Sans&quot;, sans-serif; font-size: 24px; font-weight: bold; margin-right: 0px; margin-bottom: 10px; margin-left: 0px; outline: 0px; padding: 0px; vertical-align: baseline; clear: both; color: rgb(4, 191, 191); line-height: 1.3;\">Giới thiệu sách Marketing Căn Bản – Tác giả&nbsp;Don Sexton</h2><div><em style=\"box-sizing: inherit; border: 0px; font-family: inherit; font-weight: inherit; margin: 0px; outline: 0px; padding: 0px; vertical-align: baseline;\"><strong style=\"box-sizing: inherit; border: 0px; font-family: inherit; font-style: inherit; font-weight: bold; margin: 0px; outline: 0px; padding: 0px; vertical-align: baseline;\">Marketing Căn Bản</strong></em></div><div>Với những hướng dẫn tỉ mỉ và những bảng tính dễ sử dụng, “Marketing căn bản” mang đến một cái nhìn thực tế về marketing và hỗ trợ tất cả những ai muốn tăng doanh thu, lợi nhuận, đồng tiền và RIO của doanh nghiệp mình. Dưới sự hướng dẫn của Don Sexton, bạn sẽ tự mình khám phá những chiến lược kinh doanh hiệu quả cũng như làm chủ chúng để tăng doanh thu, lợi nhuận, xây dựng thương hiệu và niềm tin của khách hàng.</div><div>Được viết với mục đích vừa như một cuốn sách vỡ lòng vừa như một cuốn sách hướng dẫn cho các khoá học marketing trong trường Đại học Trump, Làm thế nào sử dụng những ý tưởng marketing hiệu quả nhất để thu hút khách hàng cung cấp tất cả các thông tin và chiến lược cần thiết để marketing thành công sản phẩm hoặc dịch vụ. Dù bạn bán cái gì và bán như thế nào, bạn điều hành một doanh nghiệp nhỏ hay một tập đoàn lớn, cuốn sách hướng dẫn đầy đủ và toàn diện này giải thích tất cả những điều bạn nên biết về marketing hiện đại.</div><div><div>Tính cập nhật của cuốn sách được thể hiện qua những kiến thức mới về marketing trong một thế giới phẳng, trong một thời đại mới, khi sự cạnh tranh ngày một khốc liệt thì marketing-mix không chỉ bó hẹp trong 4 Ps truyền thống.</div><figure id=\"attachment_5335\" class=\"wp-caption aligncenter\" style=\"box-sizing: inherit; margin-right: auto; margin-left: auto; clear: both; border: 1px solid rgb(240, 240, 240); max-width: 100%; color: rgb(61, 61, 61); font-family: Roboto, sans-serif; font-size: 18px; width: 314px;\"><img class=\"wp-image-5335 size-full\" title=\"Marketing Căn Bản\" src=\"http://timkiemkhoinghiep.com/wp-content/uploads/2020/01/Marketing-Can-Ban.jpg\" alt=\"Marketing Căn Bản\" width=\"314\" height=\"508\" srcset=\"http://timkiemkhoinghiep.com/wp-content/uploads/2020/01/Marketing-Can-Ban.jpg 314w, http://timkiemkhoinghiep.com/wp-content/uploads/2020/01/Marketing-Can-Ban-185x300.jpg 185w\" sizes=\"(max-width: 314px) 100vw, 314px\" style=\"box-sizing: inherit; border: 0px; height: auto; max-width: 100%; display: block; margin: 0px auto;\"></figure></div><div><div>Marketing căn bản – cái chạm tay đầu tiên của “mối duyên” với thương trường</div><div>Khi ai đó hỏi “Marketing là gì?” và chỉ sau một giây, Google sẽ có cho chúng ta rất nhiều kết quả đa dạng về nội dung và cách lý giải. Nhưng nếu bạn là một người mới bắt đầu “nhón chân” vào kinh doanh thì chưa hẳn là quá nhiều thông tin khiến bạn khó hình dung một cách tổng quan về marketing.</div></div><div><div>Thứ bạn cần là một sự tổng hợp, một định nghĩa cơ bản, rõ ràng về marketing một cái nhìn thực tế và cập nhật về kinh doanh trong chính thời điểm hiện tại của bạn.</div><div>Với những hướng dẫn tỉ mỉ và những bảng tính dễ sử dụng, “Marketing căn bản” mang đến một cái nhìn thực tế về Marketing và hỗ trợ tất cả những ai muốn tăng doanh thu, lợi nhuận, đồng tiền cho doanh nghiệp mình.</div><div>Dưới sự hướng dẫn của tác giả Don Sexton, bạn sẽ tự mình khám phá những chiến lược kinh doanh hiệu quả cũng như làm chủ chúng để phát triển việc kinh doanh, xây dựng thương hiệu và niềm tin của khách hàng.</div></div><div><div>“Marketing căn bản” vừa là một cuốn sách vỡ lòng, vừa là một cuốn sách hướng dẫn cho các khóa học Marketing trong trường Đại học Trump.</div><div>Cuốn sách sẽ giúp bạn hiểu được cách sử dụng những ý tưởng Marketing hiệu quả nhất để thu hút khách hàng, xây dựng chiến lược cần thiết để Marketing thành công sản phẩm hoặc dịch vụ.</div><div>Dù bạn bán cái gì và bán như thế nào, bạn điều hành một doanh nghiệp nhỏ hay một tập đoàn lớn, bạn cũng nên biết về Marketing hiện đại, khi sự cạnh tranh ngày một khốc liệt thì marketing-mix không chỉ bó hẹp trong 4 Ps truyền thống.</div></div>");
//		blog4.setBook(bookRepository.findByTitle("Marketing căn bản"));
//		blog4.setImage(imageThumbnail4);
//		blogRepository.save(blog4);
//
//	}
//
//	@Test
//	@Order(13)
//	public void testCreateFeedBack() {
//
//		// Get book
//		Book book = bookRepository.findById(1L).get();
//
//		// Get user
//		User user = userRepository.findById(2L).get();
//
//		// Create new feedback
//		Feedback feedback = new Feedback();
//		feedback.setUser(user);
//		feedback.setBook(book);
//		feedback.setRating(4);
//		feedback.setContent("Quá hay");
//
//		feedbackRepository.save(feedback);
//	}
//
//	@Test
//	@Order(14)
//	public void testCreateAim() {
//
//		Aim aim = new Aim();
//		aim.setValue("1000000 4 6 4 20");
//		aim.setYear(2022);
//
//		aimRepository.save(aim);
//	}
//
//
//	public void createCustomer() {
//
//		// Create new Cart
//		Cart cart = new Cart();
//		cart.setCartItems(null);
//		cart.setUser(null);
//		Cart cartSaved = cartReposiroty.save(cart);
//
//		// Create new Image
//		Image imageThumbnail = new Image();
//		imageThumbnail.setTitle("2.png");
//		imageThumbnail.setUrl("uploads\\users\\2.png");
//		imageThumbnail.setThumbnailName("avtThumbnail.jpg");
//		imageThumbnail.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\avtThumbnail.png");
//		imageRepository.save(imageThumbnail);
//
//		// get user role
//		Role roleUser = roleRepository.findByName(RoleName.USER);
//
//		if (Objects.isNull(roleUser)) {
//			log.error(AppConstant.ROLE_NOT_FOUND + "User");
//		}
//
//		User user = User.builder().username("kiet").password(passwordEncoder.encode("456"))
//				.email("kietle1709@gmail.com").firstName("kiet").lastName("Le Nguyen Tuan")
//				.birthday(LocalDate.of(2002, 9, 17)).gender(1) // 1: male, 2: female, 3: Not know
//				.phoneNumber("0783511740").enabled(Boolean.TRUE).roles(Arrays.asList(roleUser)).cart(cartSaved)
//				.addresses(null).image(imageThumbnail).feedbacks(null).build();
//		userRepository.save(user);
//
//	}
//
//	public void createAdmin() {
//
//		// Create new Cart
//		Cart cart = new Cart();
//		cart.setCartItems(null);
//		cart.setUser(null);
//		Cart cartSaved = cartReposiroty.save(cart);
//
//
//		// get admin role
//		Role roleAdmin = roleRepository.findByName(RoleName.ADMIN);
//
//		if (Objects.isNull(roleAdmin)) {
//			log.error(AppConstant.ROLE_NOT_FOUND + " Admin");
//		}
//
//		// Create new Image
//		Image imageThumbnail = new Image();
//		imageThumbnail.setTitle("1.png");
//		imageThumbnail.setUrl("uploads\\users\\1.png");
//		imageThumbnail.setThumbnailName("avtThumbnail.png");
//		imageThumbnail.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\avtThumbnail.png");
//		imageRepository.save(imageThumbnail);
//
//		// get user role
//		Role roleUser = roleRepository.findByName(RoleName.USER);
//
//		if (Objects.isNull(roleUser)) {
//			log.error(AppConstant.ROLE_NOT_FOUND + "User");
//		}
//
//		// Create new Admin
//		User user = User.builder().username("khai").password(passwordEncoder.encode("123"))
//				.email("duckhailinux@gmail.com").firstName("khai").lastName("Nguyen")
//				.birthday(LocalDate.of(2002, 06, 06)).gender(1) // 1: male, 2: female, 3: Not know
//				.phoneNumber("0783511740").enabled(Boolean.TRUE).roles(Arrays.asList(roleAdmin, roleUser)).cart(null)
//				.addresses(null).image(imageThumbnail).cart(cartSaved).feedbacks(null).build();
//		userRepository.save(user);
//
//	}
//
//}