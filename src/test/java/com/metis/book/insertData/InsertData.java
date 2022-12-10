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

import com.metis.book.model.Aim;
import com.metis.book.model.Author;
import com.metis.book.model.Blog;
import com.metis.book.model.Book;
import com.metis.book.model.Cart;
import com.metis.book.model.CartItem;
import com.metis.book.model.Category;
import com.metis.book.model.Feedback;
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
import com.metis.book.repository.AimRepository;
import com.metis.book.repository.AuthorRepository;
import com.metis.book.repository.BlogRepository;
import com.metis.book.repository.BookRepository;
import com.metis.book.repository.CartItemReposirory;
import com.metis.book.repository.CartReposiroty;
import com.metis.book.repository.CategoryRepository;
import com.metis.book.repository.FeedbackRepository;
import com.metis.book.repository.ImageRepository;
import com.metis.book.repository.InventoryRepository;
import com.metis.book.repository.LanguageRepository;
import com.metis.book.repository.OrderItemRepository;
import com.metis.book.repository.OrderRepository;
import com.metis.book.repository.OrderTrackRepository;
import com.metis.book.repository.RoleRepository;
import com.metis.book.repository.UserRepository;
import com.metis.book.utils.AppConstant;

import groovyjarjarantlr4.v4.parse.ANTLRParser.block_return;
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

	@Autowired
	FeedbackRepository feedbackRepository;
	
	@Autowired
	AimRepository aimRepository;
	
	@Test
	@Order(1)
	public void testCreateAuthor() {

		// Create author
		Author author1 = new Author();
		author1.setName("Nguyễn Nhật Ánh");
		author1.setBooks(null);
		authorRepository.save(author1);

		Author author2 = new Author();
		author2.setName("Hà Minh Hoàng");
		author2.setBooks(null);
		authorRepository.save(author2);

		Author author3= new Author();
		author3.setName("Huyền Trang");
		author3.setBooks(null);
		authorRepository.save(author3);

		Author author4= new Author();
		author4.setName("Nguyễn Quang Ngọc");
		author4.setBooks(null);
		authorRepository.save(author4);

		Author author5= new Author();
		author5.setName("Trịnh Ngọc Trang");
		author5.setBooks(null);
		authorRepository.save(author5);

		Author author6= new Author();
		author6.setName("Kousuke Sawamura");
		author6.setBooks(null);
		authorRepository.save(author6);

		Author author7= new Author();
		author7.setName("Trang Anh");
		author7.setBooks(null);
		authorRepository.save(author7);
	}

	@Test
	@Order(2)
	public void testCreateCategory() {

		
		// Create image Tieu Thuyet
		Image image1 = new Image();
		image1.setTitle("1.png");
		image1.setUrl("images\\categories\\1.png");
		imageRepository.save(image1);
		
		// Create image Ngon Tinh
		Image image2 = new Image();
		image2.setTitle("2.png");
		image2.setUrl("images\\categories\\2.png");
		imageRepository.save(image2);
		
		// Create image Ngon Tinh
		Image image3 = new Image();
		image3.setTitle("3.png");
		image3.setUrl("images\\categories\\3.png");
		imageRepository.save(image3);
		
		// Create image Cung đấu
		Image image4 = new Image();
		image4.setTitle("4.png");
		image4.setUrl("images\\categories\\4.png");
		imageRepository.save(image4);
		
		// Create image kinh te
		Image image5 = new Image();
		image5.setTitle("5.png");
		image5.setUrl("images\\categories\\5.png");
		imageRepository.save(image5);
		
		// Create image kinh te
		Image image6 = new Image();
		image6.setTitle("6.png");
		image6.setUrl("images\\categories\\6.png");
		imageRepository.save(image6);
		
		// Create image kinh te
		Image image7 = new Image();
		image7.setTitle("7.png");
		image7.setUrl("images\\categories\\7.png");
		imageRepository.save(image7);
		
		// Create category
		Category category1 = new Category();
		category1.setName("Tiểu thuyết");
		category1.setImage(image1);
		categoryRepository.save(category1);

		Category category2 = new Category();
		category2.setName("Ngôn tình");
		category2.setImage(image2);
		categoryRepository.save(category2);

		Category category3 = new Category();
		category3.setName("Trinh thám");
		category3.setImage(image3);
		categoryRepository.save(category3);

		Category category4 = new Category();
		category4.setName("Cung đấu");
		category4.setImage(image4);
		categoryRepository.save(category4);

		Category category5 = new Category();
		category5.setName("Kinh tế");
		category5.setImage(image5);
		categoryRepository.save(category5);

		Category category6= new Category();
		category6.setName("Kỹ thuật");
		category6.setImage(image6);
		categoryRepository.save(category6);

		Category category7= new Category();
		category7.setName("Giáo dục");
		category7.setImage(image7);
		categoryRepository.save(category7);
	}

	@Test
	@Order(3)
	public void testCreateLanguage() {
		Language language1 = new Language();
		language1.setName("Tiếng Việt");
		languageRepository.save(language1);

		Language language2 = new Language();
		language2.setName("Tiếng Anh");
		languageRepository.save(language2);

		Language language3 = new Language();
		language3.setName("Tiếng Trung");
		languageRepository.save(language3);

		Language language4 = new Language();
		language4.setName("Tiếng Nhật");
		languageRepository.save(language4);

		Language language5 = new Language();
		language5.setName("Tiếng Hàn");
		languageRepository.save(language5);
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
		if (Objects.isNull(categoryNgonTinh)) {
			log.error(AppConstant.CATEGORY_NOT_FOUND + "Ngôn tình");
		}

		Category categoryCungDau = categoryRepository.findByName("Cung đấu");
		if (Objects.isNull(categoryCungDau)) {
			log.error(AppConstant.CATEGORY_NOT_FOUND + "Cung đấu");
		}

		Category categoryTrinhTham = categoryRepository.findByName("Trinh thám");
		if (Objects.isNull(categoryCungDau)) {
			log.error(AppConstant.CATEGORY_NOT_FOUND + "Trinh thám");
		}

		Category categoryKinhTe = categoryRepository.findByName("Kinh tế");
		if (Objects.isNull(categoryKinhTe)) {
			log.error(AppConstant.CATEGORY_NOT_FOUND + "Kinh tế");
		}

		Category categoryKyThuat = categoryRepository.findByName("Kỹ thuật");
		if (Objects.isNull(categoryKyThuat)) {
			log.error(AppConstant.CATEGORY_NOT_FOUND + "Kỹ thuật");
		}

		Category categoryGiaoDuc = categoryRepository.findByName("Giáo dục");
		if (Objects.isNull(categoryGiaoDuc)) {
			log.error(AppConstant.CATEGORY_NOT_FOUND + "Giáo dục");
		}

		// Get language
		Language languageTiengViet = languageRepository.findByName("Tiếng Việt");
		if (Objects.isNull(languageTiengViet)) {
			log.error(AppConstant.LANGUAGE_NOT_FOUND + "Tiếng Việt");
		}

		Language languageTiengAnh = languageRepository.findByName("Tiếng Anh");
		if (Objects.isNull(languageTiengAnh)) {
			log.error(AppConstant.LANGUAGE_NOT_FOUND + "Tiếng Anh");
		}

		Language languageTiengTrung = languageRepository.findByName("Tiếng Trung");
		if (Objects.isNull(languageTiengTrung)) {
			log.error(AppConstant.LANGUAGE_NOT_FOUND + "Tiếng Trung");
		}

		Language languageTiengNhat = languageRepository.findByName("Tiếng Nhật");
		if (Objects.isNull(languageTiengNhat)) {
			log.error(AppConstant.LANGUAGE_NOT_FOUND + "Tiếng Nhật");
		}

		Language languageTiengHan = languageRepository.findByName("Tiếng Hàn");
		if (Objects.isNull(languageTiengHan)) {
			log.error(AppConstant.LANGUAGE_NOT_FOUND + "Tiếng Hàn");
		}

		// Get Author
		Author author1 = authorRepository.findByName("Nguyễn Nhật Ánh");
		if (Objects.isNull(author1)) {
			log.error(AppConstant.AUTHOR_NOT_FOUND + "Nguyễn Nhật Ánh");
		}

		Author author2 = authorRepository.findByName("Hà Minh Hoàng");
		if (Objects.isNull(author2)) {
			log.error(AppConstant.AUTHOR_NOT_FOUND + "Hà Minh Hoàng");
		}

		Author author3 = authorRepository.findByName("Huyền Trang");
		if (Objects.isNull(author3)) {
			log.error(AppConstant.AUTHOR_NOT_FOUND + "Huyền Trang");
		}

		Author author4 = authorRepository.findByName("Nguyễn Quang Ngọc");
		if (Objects.isNull(author4)) {
			log.error(AppConstant.AUTHOR_NOT_FOUND + "Nguyễn Quang Ngọc");
		}

		Author author5 = authorRepository.findByName("Trịnh Ngọc Trang");
		if (Objects.isNull(author5)) {
			log.error(AppConstant.AUTHOR_NOT_FOUND + "Trịnh Ngọc Trang");
		}

		Author author6 = authorRepository.findByName("Kousuke Sawamura");
		if (Objects.isNull(author6)) {
			log.error(AppConstant.AUTHOR_NOT_FOUND + "Kousuke Sawamura");
		}

		Author author7 = authorRepository.findByName("Trang Anh");
		if (Objects.isNull(author7)) {
			log.error(AppConstant.AUTHOR_NOT_FOUND + "Trang Anh");
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

		// Create new inventory for Book5
		Inventory inventoryForBook5 = new Inventory();
		inventoryForBook5.setQuantiy(4);
		inventoryForBook5.setBook(null);
		Inventory inventorySaved5 = inventoryRepository.save(inventoryForBook5);

		// Create new inventory for Book6
		Inventory inventoryForBook6 = new Inventory();
		inventoryForBook6.setQuantiy(7);
		inventoryForBook6.setBook(null);
		Inventory inventorySaved6 = inventoryRepository.save(inventoryForBook6);

		// Create new inventory for Book7
		Inventory inventoryForBook7 = new Inventory();
		inventoryForBook7.setQuantiy(3);
		inventoryForBook7.setBook(null);
		Inventory inventorySaved7 = inventoryRepository.save(inventoryForBook7);

		// Create new inventory for Book8
		Inventory inventoryForBook8 = new Inventory();
		inventoryForBook8.setQuantiy(10);
		inventoryForBook8.setBook(null);
		Inventory inventorySaved8 = inventoryRepository.save(inventoryForBook8);

		// Create new inventory for Book9
		Inventory inventoryForBook9 = new Inventory();
		inventoryForBook9.setQuantiy(9);
		inventoryForBook9.setBook(null);
		Inventory inventorySaved9 = inventoryRepository.save(inventoryForBook9);

		// Create new inventory for Book10
		Inventory inventoryForBook10 = new Inventory();
		inventoryForBook10.setQuantiy(10);
		inventoryForBook10.setBook(null);
		Inventory inventorySaved10 = inventoryRepository.save(inventoryForBook10);

		// Create new Thumbnail for Book1
		Image image1 = new Image();
		image1.setTitle("1.png");
		image1.setUrl("images\\books\\1.png");
		image1.setThumbnailName("BookThumbnail.png");
		image1.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\BookThumbnail.png");
		imageRepository.save(image1);
		

		// Create new Thumbnail for Book2
		Image image2 = new Image();
		image2.setTitle("2.png");
		image2.setUrl("images\\books\\2.png");
		image2.setThumbnailName("BookThumbnail.png");
		image2.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\BookThumbnail.png");
		imageRepository.save(image2);

		// Create new Thumbnail for Book3
		Image image3 = new Image();
		image3.setTitle("3.png");
		image3.setUrl("images\\books\\3.png");
		image3.setThumbnailName("BookThumbnail.png");
		image3.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\BookThumbnail.png");
		imageRepository.save(image3);

		// Create new Thumbnail for Book4
		Image image4 = new Image();
		image4.setTitle("4.png");
		image4.setUrl("images\\books\\4.png");
		image4.setThumbnailName("BookThumbnail.png");
		image4.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\BookThumbnail.png");
		imageRepository.save(image4);

		// Create new Thumbnail for Book5
		Image image5 = new Image();
		image5.setTitle("5.png");
		image5.setUrl("images\\books\\5.png");
		image5.setThumbnailName("BookThumbnail.png");
		image5.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\BookThumbnail.png");
		imageRepository.save(image5);
		

		// Create new Thumbnail for Book6
		Image image6 = new Image();
		image6.setTitle("6.png");
		image6.setUrl("images\\books\\6.png");
		image6.setThumbnailName("BookThumbnail.png");
		image6.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\BookThumbnail.png");
		imageRepository.save(image6);

		// Create new Thumbnail for Book7
		Image image7 = new Image();
		image7.setTitle("7.png");
		image7.setUrl("images\\books\\7.png");
		image7.setThumbnailName("BookThumbnail.png");
		image7.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\BookThumbnail.png");
		imageRepository.save(image7);

		// Create new Thumbnail for Book8
		Image image8 = new Image();
		image8.setTitle("8.png");
		image8.setUrl("images\\books\\8.png");
		image8.setThumbnailName("BookThumbnail.png");
		image8.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\BookThumbnail.png");
		imageRepository.save(image8);

		// Create new Thumbnail for Book9
		Image image9 = new Image();
		image9.setTitle("9.png");
		image9.setUrl("images\\books\\9.png");
		image9.setThumbnailName("BookThumbnail.png");
		image9.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\BookThumbnail.png");
		imageRepository.save(image9);

		// Create new Thumbnail for Book10
		Image image10 = new Image();
		image10.setTitle("10.png");
		image10.setUrl("images\\books\\10.png");
		image10.setThumbnailName("BookThumbnail.png");
		image10.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\BookThumbnail.png");
		imageRepository.save(image10);

		// Create Book 1
		Book book1 = Book.builder().title("Tôi thấy hoa vàng trên cỏ xanh").available(Boolean.TRUE).price(50000L)
				.category(categoryTieuThuyet).description("Một cuốn tiểu thuyết giành cho giới trẻ").language(languageTiengViet)
				.publicationDate(new Date()).publisherName("Kim Đồng").inventory(inventorySaved1)
				.authors(Arrays.asList(author1)).image(image1).build();
		bookRepository.save(book1);

		// Create Book 2

		Book book2 = Book.builder().title("Mắt biếc").available(Boolean.TRUE).category(categoryTrinhTham).price(45000L)
				.description("Một cuốn tiểu thuyết chốn đồng quê").language(languageTiengViet).publicationDate(new Date())
				.publisherName("Kim Đồng").inventory(inventorySaved2).authors(Arrays.asList(author1))
				.image(image2).build();
		bookRepository.save(book2);

		// Create Book 3

		Book book3 = Book.builder().title("Game of throne").available(Boolean.TRUE).category(categoryCungDau)
				.price(58000L).description("Một cuốn sách về cung đấu").language(languageTiengViet).publicationDate(new Date())
				.publisherName("Phụ nữ").inventory(inventorySaved3).authors(Arrays.asList(author1))
				.image(image3).build();
		bookRepository.save(book3);

		// Create Book 4

		Book book4 = Book.builder().title("Your Name").available(Boolean.TRUE).category(categoryNgonTinh).price(26000L)
				.description("Một cuốn sách ngôn tình").language(languageTiengViet).publicationDate(new Date())
				.publisherName("Kadokawa").inventory(inventorySaved4).authors(Arrays.asList(author1))
				.image(image4).build();
		bookRepository.save(book4);

		// Create Book 5

		Book book5 = Book.builder().title("Marketing căn bản").available(Boolean.TRUE).category(categoryKinhTe).price(126000L)
				.description("Tất cả các thông tin cần thiết để phục vụ cho marketing").language(languageTiengViet).publicationDate(new Date())
				.publisherName("Nhà xuất bản lao động").inventory(inventorySaved5).authors(Arrays.asList(author2, author3))
				.image(image5).build();
		bookRepository.save(book5);

		// Create Book 6

		Book book6 = Book.builder().title("Thư bán hàng đỉnh cao").available(Boolean.TRUE).category(categoryKinhTe).price(130000L)
				.description("Để trở thành người bán hàng đỉnh cao").language(languageTiengViet).publicationDate(new Date())
				.publisherName("Nhà xuất bản tổng hợp").inventory(inventorySaved6).authors(Arrays.asList(author4))
				.image(image6).build();
		bookRepository.save(book6);

		// Create Book 7

		Book book7 = Book.builder().title("Kỹ thuật sữa chữa máy in").available(Boolean.TRUE).category(categoryKyThuat).price(30000L)
				.description("Dành cho ai đam mê xử lý với máy in").language(languageTiengViet).publicationDate(new Date())
				.publisherName("Nhà xuất bản giao thông vận tải").inventory(inventorySaved7).authors(Arrays.asList(author5))
				.image(image7).build();
		bookRepository.save(book7);

		// Create Book 8

		Book book8 = Book.builder().title("Tiệm cắt tóc lúc nửa đêm").available(Boolean.TRUE).category(categoryTieuThuyet).price(90000L)
				.description("Câu chuyện về tình bạn cảm động").language(languageTiengViet).publicationDate(new Date())
				.publisherName("Nhà xuất bản giao thông vận tải").inventory(inventorySaved8).authors(Arrays.asList(author6))
				.image(image8).build();
		bookRepository.save(book8);

		// Create Book 9

		Book book9 = Book.builder().title("Cẩm nang cấu trúc tiếng anh").available(Boolean.TRUE).category(categoryGiaoDuc).price(80000L)
				.description("Học cấu trúc tiếng anh").language(languageTiengViet).publicationDate(new Date())
				.publisherName("Nhà xuất bản đại học sư phạm").inventory(inventorySaved9).authors(Arrays.asList(author7))
				.image(image9).build();
		bookRepository.save(book9);

		// Create Book 10

		Book book10 = Book.builder().title("25 chuyên đề ngữ pháp tiếng anh").available(Boolean.TRUE).category(categoryGiaoDuc).price(110000L)
				.description("Học ngữ pháp tiếng anh").language(languageTiengViet).publicationDate(new Date())
				.publisherName("Nhà xuất bản đại học sư phạm").inventory(inventorySaved10).authors(Arrays.asList(author7))
				.image(image10).build();
		bookRepository.save(book10);
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
		address.setFullAddress("");
		address.setStreet("241 Nguyễn Trãi");
		address.setSubDistrict("Lái Thiêu");
		address.setDistrict("Thuận An");
		address.setProvince("Bình Dương");
		address.setIsPrimary(Boolean.TRUE);
		address.setRecievePhoneNumber(userAdmin.getPhoneNumber());
		address.setUser(userAdmin);
		addressRepository.save(address);

		// get user customer
		User userCustomer = userRepository.findByUsername("kiet");

		if (Objects.isNull(userCustomer)) {
			log.error(AppConstant.USER_NOT_FOUND + "kiet");
		}

		// Create new address for customer
		Address addressCustomer1 = new Address();
		addressCustomer1.setFullAddress("");
		addressCustomer1.setStreet("168 Trương Văn Bang");
		addressCustomer1.setSubDistrict("Thạnh Mỹ Lợi");
		addressCustomer1.setDistrict("Thủ Đức");
		addressCustomer1.setProvince("Hồ Chí Minh");
		addressCustomer1.setIsPrimary(Boolean.TRUE);
		addressCustomer1.setRecievePhoneNumber("0783511740");
		addressCustomer1.setUser(userCustomer);
		addressRepository.save(addressCustomer1);
		

		// Create new address for customer
		Address addressCustomer2 = new Address();
		addressCustomer2.setFullAddress("");
		addressCustomer2.setStreet("24 Hồ Văn Hà");
		addressCustomer2.setSubDistrict("Thạnh Mỹ Lợi");
		addressCustomer2.setDistrict("Thủ Đức");
		addressCustomer2.setProvince("Hồ Chí Minh");
		addressCustomer2.setIsPrimary(Boolean.FALSE);
		addressCustomer2.setRecievePhoneNumber("0912145167");
		addressCustomer2.setUser(userCustomer);
		addressRepository.save(addressCustomer2);
	}

	@Test
	@Order(8)
	public void testCreateCartItem() {

		// get User
		User userKiet = userRepository.findByUsername("kiet");
		if (Objects.isNull(userKiet)) {
			log.error(AppConstant.USER_NOT_FOUND + "kiet");
		}
		

		// get Cart
		Cart cartKiet = userKiet.getCart();

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

		// Create cartItem1 for user kiet
		CartItem cartItem1 = new CartItem();
		cartItem1.setQuantity(1);
		cartItem1.setCart(cartKiet);
		cartItem1.setBook(book1);
		cartItemReposirory.save(cartItem1);

		// Create cartItem2 for user Kiet
		CartItem cartItem2 = new CartItem();
		cartItem2.setQuantity(3);
		cartItem2.setCart(cartKiet);
		cartItem2.setBook(book2);
		cartItemReposirory.save(cartItem2);

	}

	@Test
	@Order(9)
	public void testCreateOrderTrack() {

		// Payment process
		OrderTrack trackPaymentProcess = new OrderTrack();
		trackPaymentProcess.setStatus("Chờ thanh toán");
		orderTrackRepository.save(trackPaymentProcess);
		
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

		// get User
		User user = userRepository.findByUsername("kiet");
		if (Objects.isNull(user)) {
			log.error(AppConstant.USER_NOT_FOUND + "kiet");
		}
		
		// get order track
		OrderTrack trackDelivering = orderTrackRepository.findByStatus("Đang giao");

		// Create order1
		com.metis.book.model.order.Order order1 = new com.metis.book.model.order.Order();
		order1.setOrderDate(new Date());
		order1.setOrderTrack(trackDelivering);
		order1.setPaymentMethod("Cash");
		order1.setCostVAT("0");
		order1.setDeliverCost("20000");
		order1.setDeliverMethod("Tiêu chuẩn");
		order1.setUser(user);
		order1.setCreateBy(2L);
		order1.setUpdateBy(2L);
		orderRepository.save(order1);

		com.metis.book.model.order.Order order2 = new com.metis.book.model.order.Order();
		order2.setOrderDate(new Date());
		order2.setOrderTrack(trackDelivering);
		order2.setPaymentMethod("momo");
		order2.setCostVAT("0");
		order2.setDeliverCost("40000");
		order2.setDeliverMethod("Nhanh");
		order2.setUser(user);
		order2.setCreateBy(2L);
		order2.setUpdateBy(2L);
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
	public void testCreateBlog() {

		// Create thumbnail image 1
		Image imageThumbnail1 = new Image();
		imageThumbnail1.setTitle("1.png");
		imageThumbnail1.setUrl("https://tranhsondaudepviet.com/wp-content/uploads/2020/09/tranh-phong-canh-dep.jpeg");
		imageThumbnail1.setThumbnailName("BlogThumbnail.png");
		imageThumbnail1.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\BlogThumbnail.png");
		imageRepository.save(imageThumbnail1);

		// Create thumbnail image 2
		Image imageThumbnail2 = new Image();
		imageThumbnail2.setTitle("2.png");
		imageThumbnail2.setUrl("https://sieuthitranhsondau.com/wp-content/uploads/2022/05/Nhung-buc-tranh-phong-canh-dep-nhat2.jpg");
		imageThumbnail2.setThumbnailName("BlogThumbnail.png");
		imageThumbnail2.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\BlogThumbnail.png");
		imageRepository.save(imageThumbnail2);

		// Create thumbnail image 3
		Image imageThumbnail3 = new Image();
		imageThumbnail3.setTitle("3.png");
		imageThumbnail3.setUrl("https://anhdepfree.com/wp-content/uploads/2020/11/hinh-nen-phong-canh.jpg");
		imageThumbnail3.setThumbnailName("BlogThumbnail.png");
		imageThumbnail3.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\BlogThumbnail.png");
		imageRepository.save(imageThumbnail3);
		
		// Create thumbnail image 4
		Image imageThumbnail4 = new Image();
		imageThumbnail4.setTitle("4.png");
		imageThumbnail4.setUrl("https://nld.mediacdn.vn/2017/photo-1-1502239793492.jpg");
		imageThumbnail4.setThumbnailName("BlogThumbnail.png");
		imageThumbnail4.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\BlogThumbnail.png");
		imageRepository.save(imageThumbnail4);
		
		Blog blog1 = new Blog();
		blog1.setTitle("Đại dương đen: Ánh sáng giữa đại dương tăm tối");
		blog1.setSubTitle("Dữ dội, loang lổ nhưng vỗ về và nhân văn");
		blog1.setContent(
				"<span style=\"color: rgb(34, 34, 34); font-family: Verdana, BlinkMacSystemFont, -apple-system, &quot;Segoe UI&quot;, Roboto, Oxygen, Ubuntu, Cantarell, &quot;Open Sans&quot;, &quot;Helvetica Neue&quot;, sans-serif; font-size: 15px; text-align: justify;\">Tiếp nối thành công từ những cuốn sách trước đó như&nbsp;</span><span style=\"color: rgb(34, 34, 34); font-family: Verdana, BlinkMacSystemFont, -apple-system, &quot;Segoe UI&quot;, Roboto, Oxygen, Ubuntu, Cantarell, &quot;Open Sans&quot;, &quot;Helvetica Neue&quot;, sans-serif; font-size: 15px; text-align: justify;\">“<a href=\"https://reviewsach.net/buc-xuc-khong-lam-ta-vo-can/\" data-wpel-link=\"internal\" rel=\"nofollow noopener noreferrer\" style=\"color: rgb(130, 113, 39);\">Bức xúc không làm ta vô can</a>”, “<a href=\"https://reviewsach.net/thien-ac-va-smartphone/\" data-wpel-link=\"internal\" rel=\"nofollow noopener noreferrer\" style=\"color: rgb(130, 113, 39);\">Thiện – ác và smartphone</a>”, “<a href=\"https://reviewsach.net/tim-minh-trong-the-gioi-hau-tuoi-tho/\" data-wpel-link=\"internal\" rel=\"nofollow noopener noreferrer\" style=\"color: rgb(130, 113, 39);\">Tìm mình trong thế giới hậu tuổi thơ</a>”</span><span style=\"color: rgb(34, 34, 34); font-family: Verdana, BlinkMacSystemFont, -apple-system, &quot;Segoe UI&quot;, Roboto, Oxygen, Ubuntu, Cantarell, &quot;Open Sans&quot;, &quot;Helvetica Neue&quot;, sans-serif; font-size: 15px; text-align: justify;\">, Tiến sĩ Đặng Hoàng Giang công bố cuốn sách mang tên&nbsp;</span><span style=\"color: rgb(34, 34, 34); font-family: Verdana, BlinkMacSystemFont, -apple-system, &quot;Segoe UI&quot;, Roboto, Oxygen, Ubuntu, Cantarell, &quot;Open Sans&quot;, &quot;Helvetica Neue&quot;, sans-serif; font-size: 15px; text-align: justify;\">“Đại dương đen”</span><span style=\"color: rgb(34, 34, 34); font-family: Verdana, BlinkMacSystemFont, -apple-system, &quot;Segoe UI&quot;, Roboto, Oxygen, Ubuntu, Cantarell, &quot;Open Sans&quot;, &quot;Helvetica Neue&quot;, sans-serif; font-size: 15px; text-align: justify;\">&nbsp;– kết tinh hành trình đầy nhọc nhằn và nhẫn nại của tác giả với người trầm cảm trong suốt hai năm ròng. Cuốn sách ghi lại tiếng nói đầy xót xa, ám ảnh của những người đang ngấp ngoải trong “đại dương” trầm cảm, đồng thời đem đến cho người đọc góc nhìn đầy khoa học khi cung cấp những kiến thức cơ bản về trầm cảm từ nguyên nhân, hậu quả, phương thức trị liệu, đến cách đối mặt với trầm cảm.&nbsp;</span><div style=\"text-align: left;\"><span style=\"color: rgb(34, 34, 34); font-family: Verdana, BlinkMacSystemFont, -apple-system, &quot;Segoe UI&quot;, Roboto, Oxygen, Ubuntu, Cantarell, &quot;Open Sans&quot;, &quot;Helvetica Neue&quot;, sans-serif; font-size: 15px; text-align: justify;\">Sách được chia làm 2 phần. Phần đầu là 12 câu chuyện của những nhân vật có thật cùng những chứng bệnh: trầm cảm, rối loạn lưỡng cực, rối loạn lo âu… Phần thứ 2 cung cấp kiến thức, phương pháp trị liệu và những hiểu biết về trầm cảm mà ai cũng nên biết.&nbsp;</span></div><div style=\"text-align: left;\"><div style=\"text-align:center;\"><img src=\"https://reviewsach.net/wp-content/uploads/2022/03/dai-duong-den-dang-hoang-giang.jpeg\"></div><br></div><div style=\"text-align: left;\"><div style=\"text-align: justify;\"><font color=\"#222222\" face=\"Verdana, BlinkMacSystemFont, -apple-system, Segoe UI, Roboto, Oxygen, Ubuntu, Cantarell, Open Sans, Helvetica Neue, sans-serif\"><span style=\"font-size: 15px;\"><br></span></font></div><div style=\"text-align: justify;\"><br></div></div><div><div style=\"text-align: justify;\"><span style=\"font-size: 1rem; text-align: left;\">Cuốn sách trước hết ghi lại những trải nghiệm thực tế của những người mắc bệnh trầm cảm, cách họ sống và làm việc, yêu đương và cống hiến với căn bệnh quái ác nhưng thiếu sự hiểu biết và đồng cảm từ người thân, gia đình, xã hội. Nhân vật chính trong những câu chuyện không bó hẹp với bất cứ ai ở bất kì giới tính, độ tuổi; nghề nghiệp hay địa vị xã hội.&nbsp;</span><br></div></div><div><div>Như tác giả Đặng Hoàng Giang đã tóm tắt:&nbsp;<i>“Nó không chỉ có ở trong giới trẻ, “vì chúng vốn thất thường trong cảm xúc.” Không chỉ ở trong giới văn nghệ sĩ, “vì họ quá nhạy cảm”. Không chỉ ở người có kinh tế đầy đủ, “bởi người nghèo lo kiếm sống thì lấy đâu ra thời gian mà trầm cảm”. Trầm cảm phổ biến như thế nào? Nếu bạn có 1000 người bạn Facebook, thì trong năm qua, bảy mươi người trong số đó mắc trầm cảm.”</i></div><div>Từng câu từng chữ như đan bện vào nhau tạo ra bức màn tăm tối ngăn cách những bệnh nhân trầm cảm được sống, được cống hiến, được yêu thương và hạnh phúc. Những câu chuyện u ám, ảm đạm, ngạt thở, dữ dội, không lối thoát có thể sẽ gây kích động tâm lí cho người đọc:&nbsp;</div><div>Ta được gặp Uyên, 21 tuổi, sinh viên ngành Kinh tế và cách đối chọi với căn bệnh đầy cực đoan: tự làm đau cơ thể. Ham muốn làm đau bản thân dữ dội và thường trực, Uyên luôn thuyết phục bạn bè “đưa cho mình vật gì nhọn để tự hại”. Chỉ khi đó, Uyên mới có thể “<i>trở lại bình thường và sự căng thẳng dịu xuống”.</i></div><div>Hay người mẹ mới sinh bị cô lập trong sự ghẻ lạnh, thờ ơ của cả gia đình bố mẹ đẻ và gia đình nhà chồng đến độ có những suy nghĩ làm hại chính đứa con mình sinh ra:&nbsp;<i>“Tôi biết là mình đang bị trầm cảm. Đây không phải là lần đầu tôi muốn làm cho H đau. Tôi yêu nó vô cùng, tôi đã suýt mất mạng khi sinh nó ra, nhưng tôi vẫn có cảm giác muốn đày đọa nó.”</i></div><div>Căn bệnh trầm cảm dường như cũng “lây lan” như dịch bệnh, căn bệnh mà mầm mống của nó dường như không thể bị dập tắt. Đời ông/bà, đời cha/mẹ, đời con, đời cháu,… cứ thế duy trì những bất ổn trong tinh thần. Rồi từ một thành viên trong gia đình, trầm cảm hủy hoại cuộc sống của tất cả những người xung quanh. Tựa như một giọt dầu loang trên mặt nước, trầm cảm cứ thế nuốt chửng lấy những ánh sáng thoi thóp của ngày tàn:&nbsp;</div><div>Thành, 29 tuổi, là một nhân viên văn phòng, anh chống chọi với căn bệnh trầm cảm trong nhiều năm. Có lẽ nhiều người không thể hiểu được cảm giác mà Thành và gia đình phải chịu đựng.&nbsp;Khi đi tìm nguồn cơn của căn bệnh, Thành nhận ra căn bệnh đang giày vò anh hàng ngày hàng giờ đã từng giày vò ông, cha mình.&nbsp;</div><div>Thành nhớ về những gì được biết về người ông.&nbsp;Bố Thành như bản sao của ông nội và Thành cũng đau đớn nhận ra, anh là người lưu giữ “vật chất di truyền” đầy trái ngang ấy. Không chỉ ông nội và cha, Thành cũng có một người mẹ chịu nhiều thương tổn, chỉ khác là mẹ của Thành có lẽ chưa phải một người trầm cảm hay có các vấn đề về tâm thần khác.</div><div><i>“Mẹ anh lớn lên với nhiều đòn roi từ bà ngoại mà lại ít sự chỉ bảo, và trở thành một kẻ phụ thuộc và nhu nhược. Đến khi đi lấy chồng, mẹ anh vẫn không phân biệt được các loại thịt. Cái điều khiển ti vi mẹ cũng chỉ biết duy nhất cái nút tắt bật và tiến lùi, và tới giờ bà vẫn không biết đi xe máy.</i></div></div><div><i><br></i></div><div><i><img src=\"https://reviewsach.net/wp-content/uploads/2022/03/reviewsach.net-dang-hoang-giang-dai-duong-den.jpeg\" align=\"left\"><br></i></div><div><i><br></i></div><div><i><br></i></div><div><i><br></i></div><div><i><br></i></div><div><i><br></i></div><div><i><br></i></div><div><i><br></i></div><div><i><br></i></div><div><i><br></i></div><div><i><br></i></div><div><i><br></i></div><div><i><br></i></div><div><i><br></i></div><div><i><br></i></div><div><i><br></i></div><div><i><br></i></div><div><i><br></i></div><div><i><br></i></div><div><i><br></i></div><div><i><br></i></div><div><i><br></i></div><div><i><br></i></div><div><div>Trầm cảm không phải bẩm sinh, những nạn nhân trầm cảm cũng không đối diện với nó theo một mô thức giống nhau. Nhưng kì lạ thay, giữa những con người có những phản ứng trái chiều đối với tổn thương trong quá khứ lại nảy sinh sự hòa hợp đầy “bạo lực và phá hủy”. Bố Thành đập phá cửa nhà, mẹ Thành làm như không rồi hàn gắn những đổ vỡ. Bố Thành “điên loạn”, phá phách, mẹ Thành nhẫn nhục, chịu đựng. Nỗi đau của bệnh nhân trầm cảm là một, nỗi đau của những người thân, gia đình và xã hội là mười.&nbsp;</div><div>Những đoạn văn đầy tăm tối có thể khiến bạn đọc bị kích động mạnh. Nhưng có lẽ, đó là cách duy nhất để mỗi chúng ta có cái nhìn thực tế, khách quan, toàn diện hơn về thế giới của người trầm cảm. Tác giả làm tất cả điều đó với mong muốn kêu gọi mọi người có cái nhìn chân thật, có sự đồng cảm với hoàn cảnh của họ.&nbsp;</div><div>Không chỉ đưa ra những câu chuyện thực tế, Đặng Hoàng Giang còn dày công nghiên cứu, tổng hợp kiến thức từ nhiều nguồn để xây dựng phần hai với những kiến thức cơ bản, thực tiễn, cần thiết cho tất cả mọi người. Suy cho cùng, xã hội đang chung sống với căn bệnh trầm cảm – bạn trung thành của Thần Chết. Trầm cảm không miễn trừ cho bất cứ ai và khi nó đến sẽ như một cơn bão vô hình, hủy diệt và cuốn trôi tất cả.&nbsp;</div><div>Tựa như một bác sĩ với con dao lam sắc nhọn trong tay, Đặng Hoàng Giang dũng cảm mổ xẻ những vấn đề gai góc nhất, khó nhằn nhất của xã hội bằng ngôn từ đầy khoa học, khách quan mà dễ hiểu.&nbsp;</div><div>Tôi không có nhiều kiến thức về tâm lí học, cũng không có nhiều trải nghiệm với bệnh nhân trầm cảm, nhưng tôi tiếp nhận cuốn sách với tất cả sự cảm thông và trân trọng đối với những bệnh nhân, người nhà của họ, và đặc biệt là tác giả cùng đội ngũ của ông. Trầm cảm nguy hại đến sức khỏe tinh thần, đe dọa trực tiếp đến tính mạng của bệnh nhân và những người xung quanh.</div></div><div><br></div><div><img src=\"https://reviewsach.net/wp-content/uploads/2022/03/reviewsach.net-dai-duong-den-by-dang-hoang-giang.jpeg\" align=\"left\"><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><div>Ông vì thế viết nên cuốn sách này bằng giọng văn vừa sâu sắc, cảm thương, vừa khoa học, chính xác, có gì đó khác với các tác phẩm trước đó – phần nhiều là bình luận, phê phán, lên án những hiện tượng, sự kiện, con người xã hội. Thật cảm phục cho sự nhẫn nại, khoan dung của tác giả khi lắng tai nghe mọi thanh âm của cuộc sống, dũng cảm cất tiếng nói thay những người yếu thế, cảnh tỉnh xã hội về một căn bệnh sinh ra bởi chính nó. Người đọc buộc phải nhìn rất thẳng, rất sâu vào vấn đề được đặt ra, vì đối mặt và chống lại trầm cảm là nghĩa vụ của tất cả chúng ta.&nbsp;</div><div>Tiến sĩ Đặng Hoàng Giang cũng là người khởi xướng đường dây nóng Ngày Mai, một hoạt động phi lợi nhuận nhằm cung cấp miển phí tham vấn tâm lý cho người trầm cảm, và nâng cao nhận thức của cộng đồng về sức khỏe tâm thần. Cuốn sách vì thế có giá trị thực tiễn rất cao.&nbsp;</div><div>Có thể một số người có đủ sức vượt qua trầm cảm và sống một cuộc sống bình thản, an yên, hạnh phúc. Nhưng ai cũng biết điều đó là rất khó, phần nhiều, cuộc sống của những bệnh nhân trầm cảm sẽ mãi chìm trong đại dương tăm tối nếu không có ai đó tìm đến và lắng nghe câu chuyện của họ, cố gắng cứu lấy họ khi đang chấp chới. Sự ra đời của cuốn sách và sự đón nhận nhiệt liệt của độc giả có lẽ là tín hiệu đáng mừng, thể hiện sự quan tâm của mọi người đến căn bệnh trầm cảm. Dù chỉ le lói, ánh sáng của tình yêu thương, lòng vị tha hẳn vẫn tồn tại trong xã hội hối hả mà sự bận rộn của cuộc sống khiến chúng ta ít khi nói về nó.&nbsp;&nbsp;</div><div>Không ai biết được cái kết của những nhân vật trong sách, cũng không ai có thể đảm bảo rằng, những phương pháp trị liệu được gợi ý sẽ có tác động thực tế hay không, đường dây nóng Ngày Mai sẽ giúp ích cho bao nhiêu người. Nhưng có một điều là chắc chắn: Chúng ta có thể ngăn chặn và khắc phục những hậu quả kinh hoàng của trầm cảm bằng nhiều cách khác nhau. Thiết nghĩ, cách tốt nhất là thể hiện sự quan tâm, yêu thương của mình đến mọi người xung quanh, đặc biệt là những người thân trong gia đình. Căn bệnh nào cũng cần được chữa trị. Ai ai cũng cần được lắng nghe, được yêu thương và giúp đỡ.&nbsp;</div></div>");
		blog1.setImage(imageThumbnail1);
		blogRepository.save(blog1);

		Blog blog2 = new Blog();
		blog2.setTitle("Review sách Xin đổi tổn thương lấy trưởng thành");
		blog2.setSubTitle("Xin đổi tổn thương lấy trưởng thành - cuốn sách giúp bạn lấy lại tinh thần sau những ngày chán nản với trọng trách gánh vác trên vai “người trưởng thành”. Lý Ái Linh sẽ giúp bạn nhận ra đôi khi chịu tổn thương không phải là xấu, nếu tổn thương ấy giúp chúng ta trưởng thành thì bạn hãy cảm ơn vì tổn thương ấy khiến bạn mạnh mẽ hơn.");
		blog2.setContent("<h2 style=\"margin-right: 0px; margin-bottom: 0px; margin-left: 0px; padding: 12px 0px 8px; overflow: hidden; font-size: 18px; line-height: 28px; color: rgb(17, 17, 17); font-family: Roboto; background-color: rgb(252, 250, 246);\">Cảm nhận sách&nbsp;Xin đổi tổn thương lấy trưởng thành</h2><div>Dù muốn hay không thì ai rồi cũng bắt buộc phải trưởng thành thế nhưng không phải ai cũng chấp nhận được sự thay đổi nhanh chóng của thời gian. Chúng ta buồn vì khi lớn lên rồi, được thoải mái làm điều mình thích nhưng chính chúng ta lại cô đơn đến thế. Liệu phải chịu bao nhiêu tổn thương mới có thể trưởng thành? Nếu muốn biết đáp án mời bạn cùng đọc cuốn sách này nhé!</div><div>Không có bất cứ thành công nào từ trên trời rơi xuống, nếu không phải đánh đổi bằng mồ hôi nước mắt thì sẽ đánh đổi bằng rất nhiều mồ hôi nước mắt. Thế nhưng trên cuộc hành trình ấy bạn không hề cô đơn vì xung quanh chúng ta có rất nhiều người đã và đang cố gắng mỗi ngày như thế. Tuổi trẻ ấy mà, ai chẳng phải trải qua những tháng ngày mệt mỏi không biết nên làm thế nào để vượt qua. Vậy mà bằng một cách nào đó sau này khi nhìn lại chúng ta lại không cảm thấy có chút mệt mỏi nào. Tất cả chỉ là thử thách, bạn kiên trì ắt sẽ được đền đáp.</div><div><div>Trên đời này lấy đâu ra nhiều thứ đồ tốt tự đến tay bạn vậy? Một bữa cơm miễn phí cũng chẳng có lấy đâu ra hạnh phúc tự tìm đến chúng ta. Muốn có một cuộc sống bình dị, hạnh phúc thì người cố gắng không ai khác phải là bạn. Muốn có được thành công người nỗ lực cũng chính là bạn. Bởi chẳng có ai có thể mang lại cho bạn ngoại trừ bản thân. Khi đã trải qua đủ loại bão giông mà cuộc đời mang đến tự nhiên bạn sẽ mạnh mẽ hơn mà không cần phải dựa dẫm vào bất cứ người nào.</div><div>Nếu bạn đang đứng giữa ngã tư đường của tuổi trẻ, bạn không biết mình nên tiến hay lùi vậy thì cuốn sách này dành cho bạn, nó sẽ giúp bạn trưởng thành hơn. Là con gái chúng ta nhất định không được bánh bèo, yếu đuối bởi sự yếu đuối của bạn không phải là lý do để cuộc sống đối xử nhẹ nhàng với bạn. Không ai là trường hợp ngoại lệ, khó khăn vẫn luôn ở đó, trong các giai đoạn sẽ có những mỏi mệt nhất định. Bạn phải kiên cường hơn nữa, muốn có được thành công chúng ta phải kiên trì.</div></div><div><div>Cuốn sách&nbsp;<a href=\"https://www.reader.com.vn/review-sach-xin-doi-ton-thuong-lay-truong-thanh-a865.html\" style=\"margin: 0px; padding: 0px; color: rgb(232, 145, 38);\"><strong style=\"margin: 0px; padding: 0px;\">Xin đổi tổn thương lấy trưởng thành của Lý Ái Linh</strong></a>&nbsp;giống như đang lật mở từng trang nhật ký cuộc đời của chúng ta vậy. Qua từng trang sách chúng ta như được nhìn thấy bản thân, giúp bạn hiểu rõ hơn về chính mình, sống thật với cảm xúc của mình. Từ những câu chuyện, bài học tạo động lực cho chúng ta thay đổi và trở thành phiên bản tốt nhất của chính mình.</div><div><img alt=\"Cảm nhận sách Xin đổi tổn thương lấy trưởng thành\" src=\"https://www.reader.com.vn/uploads/images/xin-doi-ton-thuong-lay-truong-thanh-2.jpeg\" style=\"margin: 0px; padding: 0px; border-width: initial; border-color: initial; border-image: initial; max-width: 100%; height: 468px; width: 640px;\"></div><div>Phải trải qua cơn đau dai dẳng của những mối tình đơn phương chúng ta mới chợt nhận ra chuyện tình yêu đôi khi khó hiểu lắm. Rõ ràng biết đâm đâu vào sẽ nhận về mình những tổn thương vậy mà vẫn can tâm tình nguyện đâm đầu vào. Đến một giai đoạn khi bạn đã đi làm, bạn bị những áp lực bủa vây rồi bạn sẽ nhận ra chẳng có gì quý giá hơn bản thân.</div><div><em style=\"margin: 0px; padding: 0px;\">“Tất cả những tổn thương rồi sẽ được chữa lành theo thời gian, lớp vảy bong ra lưu lại lớp da non hồng nhạt, đó là vết tích đánh dấu ta đã từng đau. Chúng ta dẫn nguồn bằng nhiệt huyết, tự do bay nhảy trên cánh đồng, để vết thương nở hoa, biến thành một tấm huy chương tượng trưng cho tìn yêu trong cuộc đời này.”</em></div></div><div><em style=\"margin: 0px; padding: 0px;\"><div>Một ngày bạn chạy ngược chạy xuôi làm đủ mọi thứ chuyện trên đời vậy mà không nhận được bất cứ lời tán dương từ người khác, bạn nhận ra bản thân bạn đã quá đủ mệt mỏi để quan sát ánh mắt của người khác nhìn bạn, bạn cũng chẳng muốn đau đầu dò đoán xem họ nghĩ gì về mình nữa rồi. Lúc này một giấc ngủ mới khiến bạn có năng lượng trở lại. Vậy thì đừng quan tâm đến thế giới, bản thân bạn mới quan trọng, ngủ một giấc sau đó thức dậy tiếp tục làm tốt công việc của mình.</div><div>Trưởng thành rồi chúng ta cần phải học cách kiềm chế cảm xúc của bản thân, không phải lúc nào muốn làm loạn cũng được. Một lúc nào đó khi bạn quá mệt mỏi rồi hãy để bản thân nghỉ ngơi một cách tốt nhất, lúc nào còn năng lượng thì hãy chiến đấu hết mình. Chúng ta còn trẻ, chúng ta còn thời gian để nỗ lực, đừng để sau này lúc về già mới bắt đầu thấy hối hận vì lúc trẻ đã không nỗ lực hết mình. Kinh nghiệm mà chúng ta học được sau những lần tổn thương rất quý giá vậy nên bạn nhất định phải trải nghiệm thật nhiều.</div><div><h3 style=\"margin-right: 0px; margin-bottom: 0px; margin-left: 0px; padding: 12px 0px 8px; overflow: hidden; font-size: 18px; line-height: 28px; color: rgb(17, 17, 17); font-family: Roboto; font-style: normal; background-color: rgb(252, 250, 246);\"><strong style=\"margin: 0px; padding: 0px;\">Trích dẫn hay trong sách&nbsp;</strong>Xin đổi tổn thương lấy trưởng thành</h3><div>Trưởng thành là quá trình để bạn nhìn nhận lại bản thân và thử thách lòng dũng cảm, dù cho cuộc sống này có bất ngờ sụp đổ hay đẩy bạn rơi vào tình cảnh tuyệt vọng. Không có gì dễ dàng vứt bỏ, cũng chẳng có gì không thể thay thế. Sự tệ bạc và phản bội mà chúng ta đã từng nếm trải chẳng qua đều là những bài kiểm tra đúng thời điểm mà tạo hóa đã ban tặng. Lấy nỗi đau trong chốc lát đổi thành sự sáng suốt vĩnh cửu.</div><div>Những câu chuyện đẹp vẫn luôn diễn ra một cách chậm rãi, tình yêu sâu đậm vẫn luôn chảy dài như dòng nước tĩnh lặng. Mọi tình yêu trên đời đều xứng đáng được lưu giữ và trân trọng. Tất cả những điều tốt đẹp đều xứng đáng được khắc ghi.</div></div><div><span style=\"color: rgb(17, 17, 17); font-family: Roboto; font-style: normal; background-color: rgb(252, 250, 246);\">“Hãy sống như những gì bạn nghĩ, chứ đừng nghĩ như những gì bạn sống.” Rồi sẽ có một ngày chuyến tàu ấy sẽ đến, bạn xứng đáng yêu và được yêu.</span><br></div><div><h3 style=\"margin-right: 0px; margin-bottom: 0px; margin-left: 0px; padding: 12px 0px 8px; overflow: hidden; font-size: 18px; line-height: 28px; color: rgb(17, 17, 17); font-family: Roboto; font-style: normal; background-color: rgb(252, 250, 246);\"><strong style=\"margin: 0px; padding: 0px;\">Lời kết</strong></h3><div>“Chúc cho tất cả những người con gái chân thành và đam mê trong tình yêu, trưởng thành hơn nhờ vào sai lầm, hãy yêu đúng cách để tình yêu được tỏa sáng.”</div><div>Cảm ơn mọi người đã đọc hết bài review của mình. Hy vọng rằng những chia sẻ của mình có thể giúp ích các bạn trong việc lựa chọn cuốn sách phù hợp với bản thân. Chúc các bạn đọc sách vui vẻ. Thân!</div></div></em></div>");
		blog2.setImage(imageThumbnail2);
		blogRepository.save(blog2);

		Blog blog3 = new Blog();
		blog3.setTitle("Review sách Sống thanh thản như người Thụy Điển");
		blog3.setSubTitle("“Sống thanh thản như người Thụy Điển” là cuốn sách hữu ích giúp bạn lọc bớt những đồ dùng không cần thiết trong cuộc sống. Đôi khi những vật dụng ấy có thể gây ra rất nhiều vấn đề mà chúng ta không thể biết và đặc biệt việc tối giản đồ đạc sẽ giúp chúng ta bày trí không gian của một ngôi nhà đẹp hơn và quan trọng hơn hết việc làm này của bạn sẽ đóng góp vào việc bảo vệ môi trường.");
		blog3.setContent("<h2 style=\"margin-right: 0px; margin-bottom: 0px; margin-left: 0px; padding: 12px 0px 8px; overflow: hidden; font-size: 18px; line-height: 28px; color: rgb(17, 17, 17); font-family: Roboto; background-color: rgb(252, 250, 246);\"><strong style=\"margin: 0px; padding: 0px;\">Cuốn sách này dành cho ai?</strong></h2><div>Nếu bạn là một người thích lướt Shopee, Tiki, Lazada vào đêm khuya để mua một đống đồ không bao giờ dùng đến thì cuốn sách này dành cho bạn.</div><div>Nếu không gian sống hiện tại của bạn có quá nhiều đồ đạc, nói đúng hơn là căn nhà của bạn không khác gì nhà kho tích trữ đồ.</div><div><div>Nếu bạn là người đang mong muốn hướng đến lối sống tối giản, gọn gàng và ngăn nắp không gian sống của mình và cả tìm kiếm sự bình yên trong tâm hồn. Vậy thì cuốn sách&nbsp;<strong style=\"margin: 0px; padding: 0px;\">Sống thanh thản như người Thụy Điển</strong>&nbsp;chính xác là dành cho bạn.</div><h3 style=\"margin-right: 0px; margin-bottom: 0px; margin-left: 0px; padding: 12px 0px 8px; overflow: hidden; font-size: 18px; line-height: 28px; color: rgb(17, 17, 17); font-family: Roboto; background-color: rgb(252, 250, 246);\"><strong style=\"margin: 0px; padding: 0px;\">Thuật ngữ Dostadning</strong></h3><div>Dostadning (trong tiếng Anh, nó có nghĩa là “death cleaning”) trong tiếng Thụy Điển, do à cái chết, còn stadning là dọn dẹp; thuật ngữ này có nghĩa là bỏ đi những thứ không cần thiết và làm cho ngôi nhà của bạn đẹp hơn, gọn gàng hơn, ngăn nắp hơn khi bạn nghĩ thời điểm mình bước sang hành trình khác đang gần đến.”</div><div>Nghệ thuật dostadning không chỉ là phủi bụi và sắp xếp gọn gàng mọi thứ. Về cơ bản, đó là hành động lược bỏ những vật dụng không cần thiết từ đống đồ của bạn. Không chỉ vậy, đây còn là hình thức sắp xếp cố định có thể giúp cuộc sống hàng ngày của bạn trôi chảy hơn.</div></div><div><h2 style=\"margin-right: 0px; margin-bottom: 0px; margin-left: 0px; padding: 12px 0px 8px; overflow: hidden; font-size: 18px; line-height: 28px; color: rgb(17, 17, 17); font-family: Roboto; background-color: rgb(252, 250, 246);\">Cảm nhận sách Sống thanh thản như người Thụy Điển</h2><div>Những năm gần đây chúng ta thường nghe đến rất nhiều về lối sống tối giản, xu hướng này ngày càng được phổ biến hơn. Chúng ta có thể hiểu đơn giản đó là vứt bỏ đồ đạc không cần thiết, những thứ bạn giữ lại là thứ bạn luôn dùng đến, sắp xếp không gian sống một cách ngăn nắp, gọn gàng. Bạn nghĩ rằng việc dọn dẹp nhà cửa và vứt bỏ đồ đạc sẽ dễ dàng ư? Thực tế thì chúng ta luôn có tâm lý tiếc của hoặc chúng ta nghĩ rằng cứ giữ đó biết đâu đến một ngày lại dùng đến. Thế nhưng những đồ vật không cần thiết sẽ bào mòn túi tiền của chúng ta, thu hẹp không gian sống của chúng ta.</div><div>Có rất nhiều những món đồ dù đã thay mới nhưng vẫn tồn đọng món cũ. Ví dụ như tủ thuốc của bạn với rất nhiều loại thuốc khác nhau từ vitamin đến thuốc cảm, thuốc đau bụng,… tuy rằng chúng đã hết hạn sử dụng nhưng bạn vẫn giữ nó lại ở trong tủ của mình. Hoặc những bộ quần áo dù đã lỗi mốt, không thể mặc được nữa nhưng bạn vẫn thích tích trữ nó ở trong nhà, kết quả những món đồ ngày một nhiều lên.</div></div><div><div>Ở mỗi độ tuổi chúng ta sẽ có cách ăn mặc khác nhau chính vì thế những bộ đồ chúng ta diện trên người cũng khác nhau. Chúng thay đổi theo thời gian, có thể là vì xu hướng của thời đại, vì cân nặng của bạn. Tuy nhiên hãy nhìn những bộ quần áo theo xu hướng thời trang nhanh. Về chất liệu thường thì nó không mặc được lâu, kiểu dáng cũng như vậy. Theo đuổi phong cách của những món đồ thời trang nhanh sẽ khiến cho túi tiền của bạn bị bào mòn rất nhanh. Chưa kể, việc mua quá nhiều đồ cũng ảnh hưởng trầm trọng đến môi trường.</div><div>Đọc sách là một thói quen tốt, bạn đam mê sưu tầm sách cũng không có gì sai nhưng hãy nghĩ đến việc tối giản đồ đạc bằng cách sau khi đọc sách xong bạn chỉ giữ những cuốn bạn cảm thấy cần thiết bên mình. Còn lại những cuốn sách khác bạn nên đi chia sẻ cho mọi người xung quanh làm như vậy không chỉ lan tỏa văn hóa đọc mà còn bảo vệ môi trường.</div></div><div><div>Đã đến lúc chúng ta cần thay đổi suy nghĩ về việc mua đồ mặc, vật dụng trong nhà. Chúng ta cần tối giản những món đồ cần thiết nhất có thể, còn lại bạn nên cân đo đong đếm xem món nào thật sự không cần thiết thì hãy dừng ngay việc mua chúng lại. Nếu không có việc gì làm bạn nên ngủ sớm để giữ tinh thần thoải mái nhất có thể để sáng mai dậy sớm đi làm. Đừng thức đêm chốt đơn những món hàng không bao giờ dùng đến vào lúc đêm khuya nữa, cũng đừng nghe quảng cáo quá nhiều. Bạn nên xem xét thật kỹ lưỡng tất cả mọi thứ trước khi mua nếu không ngôi nhà của bạn nhanh chóng sẽ biến thành một bãi rác vì chứa quá nhiều đồ đạc và tinh thần của bạn cũng sẽ mệt mỏi vì xung quanh đâu đâu cũng là đồ đạc.</div><h3 style=\"margin-right: 0px; margin-bottom: 0px; margin-left: 0px; padding: 12px 0px 8px; overflow: hidden; font-size: 18px; line-height: 28px; color: rgb(17, 17, 17); font-family: Roboto; background-color: rgb(252, 250, 246);\"><strong style=\"margin: 0px; padding: 0px;\">Trích đoạn hay trong sách</strong></h3><div>Hãy chuẩn bị tinh thần là việc tinh gọn nhà cửa sẽ cần nhiều thời gian. Người già thường nghĩ thời gian trôi quá nhanh, nhưng thực tế là do chúng ta trở nên chậm chạp hơn. Thế nên nếu bạn đang ở những năm cuối cuộc đời thì đừng chờ quá lâu…</div><div>Công việc mới này của bạn sẽ không được hoàn thành nhanh hơn nếu bạn chờ đợi, nhưng chắc chắn là bạn sẽ quyết định cách buông bỏ đồ đạc dễ dàng hơn khi dành chút thời gian tập luyện và chuẩn bị. Hãy tin tôi, càng dành nhiều thời gian xem lại các món đồ của mình, bạn sẽ càng dễ quyết định giữ món nào và bỏ món nào. Càng thực hành, bạn sẽ càng ít mất thời gian cho việc này. Thậm chí bạn còn có được phần thưởng bất ngờ là cảm giác tuyệt diệu khi buông bỏ nhiều thứ.</div></div><div><span style=\"color: rgb(17, 17, 17); font-family: Roboto; background-color: rgb(252, 250, 246);\">Hãy bắt đầu bằng việc kiểm tra tầng hầm, gác mái hoặc tủ đồ cạnh cửa chính. Đó là những nơi lý tưởng để “bỏ tạm” mấy món đồ dư thừa.</span><br></div><div><h3 style=\"margin-right: 0px; margin-bottom: 0px; margin-left: 0px; padding: 12px 0px 8px; overflow: hidden; font-size: 18px; line-height: 28px; color: rgb(17, 17, 17); font-family: Roboto; background-color: rgb(252, 250, 246);\"><strong style=\"margin: 0px; padding: 0px;\">Lời kết</strong></h3><div>Cuộc đời ngắn ngủi, vì vậy đừng dành thời gian quá nhiều cho việc dọn dẹp đồ đạc. Hãy tối giản đồ dùng trong cuộc sống của bạn ngay từ hôm nay để vừa tiết kiệm chi tiêu, vừa có lối sống tối giản mà không cần phải lo nghĩ về những thứ xung quanh mình quá nhiều.</div><div>Cảm ơn mọi người đã đọc hết bài review của mình. Hy vọng rằng những chia sẻ của mình có thể giúp ích các bạn trong việc lựa chọn cuốn sách phù hợp với bản thân. Chúc các bạn đọc sách vui vẻ. Thân!</div></div>");
		blog3.setImage(imageThumbnail3);
		blogRepository.save(blog3);
		
		Blog blog4 = new Blog();
		blog4.setTitle("Review Sách Tôi Thấy Hoa Vàng Trên Cỏ Xanh – Một Lần Tìm Về Bầu Trời Tuổi Thơ Êm Đềm, Tĩnh Lặng");
		blog4.setSubTitle("Tôi thấy hoa vàng trên cỏ xanh là tác phẩm đã được chuyển thể thành phim cùng tên của nhà văn Nguyễn Nhật Ánh. Truyện bác Ánh lúc nào cũng dung dị, nhẹ nhàng, lãng mạn và rất tình. Truyện ngắn kể về tuổi thơ của những đứa trẻ lớn lên ở một vùng quê nghèo khó, với những suy nghĩ và rung động tinh tế trong những năm đầu đời.");
		blog4.setContent("<h2 style=\"margin-right: 0px; margin-bottom: 0px; margin-left: 0px; padding: 12px 0px 8px; overflow: hidden; font-size: 18px; line-height: 28px; color: rgb(17, 17, 17); font-family: Roboto; background-color: rgb(252, 250, 246);\"><div><strong style=\"font-weight: bold;\">Giới thiệu tác giả sách Tôi thấy hoa vàng trên cỏ xanh</strong></div><div>Nhà văn Nguyễn Nhật Ánh được biết đến qua nhiều tác phẩm văn học về đề tài tuổi mới lớn. Cốt truyện đơn giản, tình cảm trong sáng, cảnh đẹp làng quê là những nét đặc trưng trong các truyện ngắn của Nguyễn Nhật Ánh. Ngoài Tôi thấy hoa vàng trên cỏ xanh, truyện ngắn&nbsp;<a href=\"https://revisach.com/truyen-ngan-mat-biec-nguyen-nhat-anh/\" target=\"_blank\" rel=\"noopener noreferrer\" style=\"color: rgb(77, 178, 236);\">Mắt Biếc</a>&nbsp;và Cô gái đến từ hôm qua của bác đều đã được chuyển thể thành phim và mang lại tiếng vang lớn.</div></h2><h2 style=\"font-family: Roboto, sans-serif; color: rgb(17, 17, 17); margin: 30px 0px 20px; font-size: 27px; line-height: 38px;\"><strong style=\"font-weight: bold;\">Thông tin cơ bản của cuốn sách&nbsp;</strong><strong style=\"font-weight: bold;\">Tôi thấy hoa vàng trên cỏ xanh</strong><span class=\"ez-toc-section-end\"></span></h2><h2 style=\"margin-right: 0px; margin-bottom: 0px; margin-left: 0px; padding: 12px 0px 8px; overflow: hidden; font-size: 18px; line-height: 28px; color: rgb(17, 17, 17); font-family: Roboto; background-color: rgb(252, 250, 246);\"><table style=\"width: 696px; margin-bottom: 21px; color: rgb(34, 34, 34); font-family: Verdana, Geneva, sans-serif; font-size: 15px; background-color: rgb(255, 255, 255); height: 104px;\"><tbody><tr style=\"height: 26px;\"><td style=\"padding: 2px 8px; border-color: rgb(237, 237, 237); width: 347.5px; height: 26px;\">Công ty phát hành</td><td style=\"padding: 2px 8px; border-color: rgb(237, 237, 237); width: 347.5px; height: 26px;\">NXB Trẻ</td></tr><tr style=\"height: 26px;\"><td style=\"padding: 2px 8px; border-color: rgb(237, 237, 237); width: 347.5px; height: 26px;\">Ngày xuất bản</td><td style=\"padding: 2px 8px; border-color: rgb(237, 237, 237); width: 347.5px; height: 26px;\">12-2018</td></tr><tr style=\"height: 26px;\"><td style=\"padding: 2px 8px; border-color: rgb(237, 237, 237); width: 347.5px; height: 26px;\">Loại bìa</td><td style=\"padding: 2px 8px; border-color: rgb(237, 237, 237); width: 347.5px; height: 26px;\">Bìa mềm</td></tr><tr style=\"height: 26px;\"><td style=\"padding: 2px 8px; border-color: rgb(237, 237, 237); width: 347.5px; height: 26px;\">Số trang</td><td style=\"padding: 2px 8px; border-color: rgb(237, 237, 237); width: 347.5px; height: 26px;\">378</td></tr></tbody></table></h2><h2 style=\"font-family: Roboto, sans-serif; color: rgb(17, 17, 17); margin: 30px 0px 20px; font-size: 27px; line-height: 38px; text-align: justify;\"><span class=\"ez-toc-section\" id=\"Noi_dung_Toi_thay_hoa_vang_tren_co_xanh\"></span><strong style=\"font-weight: bold;\">Nội dung Tôi thấy hoa vàng trên cỏ xanh</strong><span class=\"ez-toc-section-end\"></span></h2><h2 style=\"margin-right: 0px; margin-bottom: 0px; margin-left: 0px; padding: 12px 0px 8px; overflow: hidden; font-size: 18px; line-height: 28px; color: rgb(17, 17, 17); font-family: Roboto; background-color: rgb(252, 250, 246);\"><div>Cuốn Tôi thấy hoa vàng trên cỏ xanh kể về tuổi thơ nghèo khó của hai anh em Thiều và Tường cùng cô bạn thân hàng xóm. Mạch truyện tự nhiên, dẫn dắt người đọc chứng kiến những rung động đầu đời của tụi nhỏ, xen vào đó là những nét đẹp của tình anh em và vài nốt trầm của sự đau đớn khi trưởng thành. Truyện Nguyễn Nhật Ánh thường không nói quá nhiều về trắng đen, thiện ác nhưng trong tác phẩm này, tác giả đã đưa vấn đề đạo đức vào sách và khiến người đọc suy ngẫm.</div></h2><h3 style=\"font-family: Roboto, sans-serif; color: rgb(17, 17, 17); margin: 27px 0px 17px; font-size: 22px; line-height: 30px; text-align: justify;\"><strong style=\"font-weight: bold;\">Tuổi thơ hồn nhiên, mơ mộng</strong><span class=\"ez-toc-section-end\"></span></h3><h2 style=\"margin-right: 0px; margin-bottom: 0px; margin-left: 0px; padding: 12px 0px 8px; overflow: hidden; font-size: 18px; line-height: 28px; color: rgb(17, 17, 17); font-family: Roboto; background-color: rgb(252, 250, 246);\"><div>Sách Tôi thấy hoa vàng trên cỏ xanh gợi nhớ lại một thời tuổi thơ hồn thiên, mơ mộng. Những ngày Tường trở thành chú chim xanh chuyên giao thư qua lại giữa chú Đàn và chị Vinh nhưng phải thật khéo vì nếu cha chị Vinh biết thì cả ba xong đời.</div><div>Hay những lúc nghe chuyện ma, hai anh em sợ đến nỗi bị nhát là sẽ chạy tán loạn, đến khi về nhà còn bị đánh cho một trận vì tội con trai mà lại sợ ma. Dẫu yếu bóng vía là thế nhưng khi nghe kể về xóm Miễu có ma cọp thì hai anh em vẫn nổi máu tò mò và đồi đi khám phá.</div><figure id=\"attachment_687\" aria-describedby=\"caption-attachment-687\" class=\"wp-caption aligncenter\" style=\"margin: 6px auto 0px; text-align: center; max-width: 100%; clear: both; color: rgb(34, 34, 34); font-family: Verdana, Geneva, sans-serif; font-size: 15px; background-color: rgb(255, 255, 255); width: 1000px;\"><img decoding=\"async\" loading=\"lazy\" class=\"size-full wp-image-687\" src=\"https://revisach.com/wp-content/uploads/2020/07/review-sach-toi-thay-hoa-vang-tren-co-xanh-nguyen-nhat-anh-3.png\" alt=\"review-sach-toi-thay-hoa-vang-tren-co-xanh-nguyen-nhat-anh-3\" width=\"1000\" height=\"700\" srcset=\"https://revisach.com/wp-content/uploads/2020/07/review-sach-toi-thay-hoa-vang-tren-co-xanh-nguyen-nhat-anh-3.png 1000w, https://revisach.com/wp-content/uploads/2020/07/review-sach-toi-thay-hoa-vang-tren-co-xanh-nguyen-nhat-anh-3-300x210.png 300w, https://revisach.com/wp-content/uploads/2020/07/review-sach-toi-thay-hoa-vang-tren-co-xanh-nguyen-nhat-anh-3-768x538.png 768w, https://revisach.com/wp-content/uploads/2020/07/review-sach-toi-thay-hoa-vang-tren-co-xanh-nguyen-nhat-anh-3-696x487.png 696w, https://revisach.com/wp-content/uploads/2020/07/review-sach-toi-thay-hoa-vang-tren-co-xanh-nguyen-nhat-anh-3-600x420.png 600w, https://revisach.com/wp-content/uploads/2020/07/review-sach-toi-thay-hoa-vang-tren-co-xanh-nguyen-nhat-anh-3-100x70.png 100w\" sizes=\"(max-width: 1000px) 100vw, 1000px\" style=\"border: 0px; max-width: 100%; height: auto; margin-bottom: 0px; width: 696px; display: block;\"><figcaption id=\"caption-attachment-687\" class=\"wp-caption-text\" style=\"margin: 6px 0px 26px; font-size: 11px; font-style: italic; line-height: 17px; color: rgb(68, 68, 68);\">Sách Tôi thấy hoa vàng trên cỏ xanh</figcaption></figure></h2><h3 style=\"font-family: Roboto, sans-serif; color: rgb(17, 17, 17); margin: 27px 0px 17px; font-size: 22px; line-height: 30px; text-align: justify;\"><span class=\"ez-toc-section\" id=\"Tinh_yeu_dau_doi_ngay_ngo_trong_sang\"></span><strong style=\"font-weight: bold;\">Tình yêu đầu đời ngây ngô, trong sáng</strong><span class=\"ez-toc-section-end\"></span></h3><h2 style=\"margin-right: 0px; margin-bottom: 0px; margin-left: 0px; padding: 12px 0px 8px; overflow: hidden; font-size: 18px; line-height: 28px; color: rgb(17, 17, 17); font-family: Roboto; background-color: rgb(252, 250, 246);\"><div>Tình yêu trong truyện là những rung động ngây ngô, trong sáng đầu đời. Lần đầu tiên biết đến hoa tay, chú bé Thiều đã chạy khắp làng để xem hoa tay cho từng đứa. Rồi đến khi biết đến thư tình, Thiều cũng mượn thơ của chú Đàn để viết vào mảnh giấy:&nbsp;</div><div><strong style=\"font-weight: bold;\"><em>Nắng mưa là bệnh của trời</em></strong><br><strong style=\"font-weight: bold;\"><em>Tương tư là chuyện của tôi yêu nàng.</em></strong></div><div>Cái thời mà smartphone còn chưa xuất hiện, những người yêu nhau sẽ lén trao nhau từng bức thư tay để bày tỏ tâm tình. Thiều thương Mận nhưng không dám nói, viết thư tay còn chưa kịp trao thì đã bị cô giáo bắt được và đọc cho cả lớp nghe. “Những ngày ẩm ương, chưa lớn mà cũng chẳng còn nhỏ, có ai chưa từng dõi mắt nhìn theo một ai”.</div><div class=\"revis-content\" id=\"revis-2113366425\" style=\"color: rgb(34, 34, 34); font-family: Verdana, Geneva, sans-serif; font-size: 15px; background-color: rgb(255, 255, 255);\"><ins class=\"adsbygoogle\" data-ad-client=\"ca-pub-8175304222497765\" data-ad-slot=\"8971687406\" data-ad-format=\"auto\" data-full-width-responsive=\"true\" style=\"background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial; text-decoration-line: none; display: block;\"></ins></div></h2><h3 style=\"font-family: Roboto, sans-serif; color: rgb(17, 17, 17); margin: 27px 0px 17px; font-size: 22px; line-height: 30px; text-align: justify;\"><span class=\"ez-toc-section\" id=\"Tinh_cam_gia_dinh_thieng_lieng_dang_tran_trong\"></span><strong style=\"font-weight: bold;\">Tình cảm gia đình thiêng liêng, đáng trân trọng</strong><span class=\"ez-toc-section-end\"></span></h3><h2 style=\"margin-right: 0px; margin-bottom: 0px; margin-left: 0px; padding: 12px 0px 8px; overflow: hidden; font-size: 18px; line-height: 28px; color: rgb(17, 17, 17); font-family: Roboto; background-color: rgb(252, 250, 246);\"><div>Tình anh em là một trong những chủ đề chính của truyện. Dù đôi lúc anh lớn là Thiếu có vô tình đối xử chưa tốt với em nhưng Tường vẫn luôn thương anh và lo lắng cho anh. Đi chơi về bị ba đánh, Thiều thì vắt chân lên cổ bỏ chạy còn Tường ở lại chịu trận thay cho anh hai. Tường thay anh làm hết các công việc trong nhà chỉ vì nghe mẹ bảo “để yên cho anh hai học bài”.</div><div>Chi tiết cảm động nhất truyện là khi anh hai bị đánh, Tường giúp anh trả thù nhưng lại tự mình chịu đòn vì không để anh bị oan. Ở cái tuổi dở trăng dở đèn, khi nội tâm và tính cách có nhiều biến động, Thiều đã không ít lần để cơn nóng giận bùng cháy và là tổn thương em trai.&nbsp;</div><div><i>Mọi thứ chợt hiện ra trong đầu Thiều và cả nỗi ân hận như dao khắc vào tim Thiều, làm Thiều đứng sững như người mất hồn mà không hay rằng thằng Tường vẫn còn nằm ngửa dưới nền nhà. Thời gian như tiếp tục trôi qua khi thằng Thiều nghe tiếng thằng Tường kêu “Giúp em với, anh Hai!”.</i></div><div><i>Khi Thiều xốc nách nó thì đã rên lên “Đau em!” và thấy nước mắt nó úa ra. Sau khi thấu hiểu sự tình, Thiều liền đi tìm ông Xung&nbsp; cứu Tường nhưng trước đi tường lại kêu anh hai bảo là: “Anh đừng nói với ông Xung là anh đánh em nhé, hãy bảo là em trèo cây bị tuột tay rơi xuống đất.”</i></div><div>Gợi ý sách hay:&nbsp;<a href=\"https://revisach.com/ong-tram-tuoi-treo-qua-cua-so-va-bien-mat/\" target=\"_blank\" rel=\"noopener noreferrer\" style=\"color: rgb(77, 178, 236);\">Ông trăm tuổi trèo qua cửa sổ và biến mất – cuốn sách làm sống dậy khát vọng sống trẻ, sống tự do</a></div></h2><h3 style=\"font-family: Roboto, sans-serif; color: rgb(17, 17, 17); margin: 27px 0px 17px; font-size: 22px; line-height: 30px; text-align: justify;\"><span class=\"ez-toc-section\" id=\"Tinh_nguoi_noi_lang_que_than_thuong\"></span><strong style=\"font-weight: bold;\">Tình người nơi làng quê thân thương</strong><span class=\"ez-toc-section-end\"></span></h3><h2 style=\"margin-right: 0px; margin-bottom: 0px; margin-left: 0px; padding: 12px 0px 8px; overflow: hidden; font-size: 18px; line-height: 28px; color: rgb(17, 17, 17); font-family: Roboto; background-color: rgb(252, 250, 246);\"><div>Tôi thấy hoa vàng trên cỏ xanh cũng đem đến cho ta bài học về tình người nơi làng quê ấm áp. Cha của Mận chịu cảnh tù túng, trốn hàng năm trời trong căn gác bí ẩn xấp xệ cùng căn bệnh phong quái lạ. Mận – cô con gái hiếu thảo và tình cảm đã bỏ cả học bài để chăm sóc cha. Và bố mẹ hai anh em Thiều Tường đã ngỏ ý nhận Mận về nuôi khi nhà cô bé bị cháy, dù sự thật là cả gia đình cũng chẳng khá giả gì.</div><figure id=\"attachment_688\" aria-describedby=\"caption-attachment-688\" class=\"wp-caption aligncenter\" style=\"margin: 6px auto 0px; text-align: center; max-width: 100%; clear: both; color: rgb(34, 34, 34); font-family: Verdana, Geneva, sans-serif; font-size: 15px; background-color: rgb(255, 255, 255); width: 800px;\"><img decoding=\"async\" loading=\"lazy\" class=\"size-full wp-image-688\" src=\"https://revisach.com/wp-content/uploads/2020/07/review-sach-toi-thay-hoa-vang-tren-co-xanh-nguyen-nhat-anh.png\" alt=\"review-sach-toi-thay-hoa-vang-tren-co-xanh-nguyen-nhat-anh\" width=\"800\" height=\"530\" srcset=\"https://revisach.com/wp-content/uploads/2020/07/review-sach-toi-thay-hoa-vang-tren-co-xanh-nguyen-nhat-anh.png 800w, https://revisach.com/wp-content/uploads/2020/07/review-sach-toi-thay-hoa-vang-tren-co-xanh-nguyen-nhat-anh-300x199.png 300w, https://revisach.com/wp-content/uploads/2020/07/review-sach-toi-thay-hoa-vang-tren-co-xanh-nguyen-nhat-anh-768x509.png 768w, https://revisach.com/wp-content/uploads/2020/07/review-sach-toi-thay-hoa-vang-tren-co-xanh-nguyen-nhat-anh-696x461.png 696w, https://revisach.com/wp-content/uploads/2020/07/review-sach-toi-thay-hoa-vang-tren-co-xanh-nguyen-nhat-anh-634x420.png 634w, https://revisach.com/wp-content/uploads/2020/07/review-sach-toi-thay-hoa-vang-tren-co-xanh-nguyen-nhat-anh-600x398.png 600w\" sizes=\"(max-width: 800px) 100vw, 800px\" style=\"border: 0px; max-width: 100%; height: auto; margin-bottom: 0px; width: 696px; display: block;\"><figcaption id=\"caption-attachment-688\" class=\"wp-caption-text\" style=\"margin: 6px 0px 26px; font-size: 11px; font-style: italic; line-height: 17px; color: rgb(68, 68, 68);\">Poster phim Tôi thấy hoa vàng trên cỏ xanh</figcaption></figure><div><i>Nửa khuya, lũ từ trên nguồn tràn về, mực nước từ từ dâng lên mấp mé mặt giường trong ánh mắt lo lắng của mẹ tôi. Cả nhà tôi leo hết lên giường, xách theo mấy chiếc đòn kê để ngồi cho khỏi ướt mông, co ro chờ trời sáng.</i></div></h2><h2 style=\"font-family: Roboto, sans-serif; color: rgb(17, 17, 17); margin: 30px 0px 20px; font-size: 27px; line-height: 38px; text-align: justify;\"><span class=\"ez-toc-section\" id=\"Triet_ly_trong_truyen_Toi_thay_hoa_vang_tren_co_xanh\"></span><strong style=\"font-weight: bold;\">Triết lý trong truyện Tôi thấy hoa vàng trên cỏ xanh</strong><span class=\"ez-toc-section-end\"></span></h2><h2 style=\"margin-right: 0px; margin-bottom: 0px; margin-left: 0px; padding: 12px 0px 8px; overflow: hidden; font-size: 18px; line-height: 28px; color: rgb(17, 17, 17); font-family: Roboto; background-color: rgb(252, 250, 246);\"><div>Điểm khác biệt của Tôi thấy hoa vàng trên cỏ xanh so với những tác phẩm khác của Nguyễn Nhật Ánh là tác giả đã nói về những mặt xấu trong tính cách của cả những nhân vật chính. Ai cũng có những nét méo mó trong tâm hồn vì suy cho cùng thì ranh giới giữa thiện và ác thực ra rất mong manh. Thiều bồng bột, thiếu sót do không kiểm soát được cảm xúc của chính mình nên đôi khi đã cư xử tệ với em trai.&nbsp;</div><div>Tuổi thơ của những nhân vật trong truyện tuy không êm đềm, có gia đình phải trải qua những biến cố nhưng chính những thử thách ấy đã tạo nên con người chúng ta khi trưởng thành.</div></h2><h2 style=\"font-family: Roboto, sans-serif; color: rgb(17, 17, 17); margin: 30px 0px 20px; font-size: 27px; line-height: 38px; text-align: justify;\"><span class=\"ez-toc-section\" id=\"Loi_ket\"></span><strong style=\"font-weight: bold;\">Lời kết</strong><span class=\"ez-toc-section-end\"></span></h2><h2 style=\"margin-right: 0px; margin-bottom: 0px; margin-left: 0px; padding: 12px 0px 8px; overflow: hidden; font-size: 18px; line-height: 28px; color: rgb(17, 17, 17); font-family: Roboto; background-color: rgb(252, 250, 246);\"><div>Truyện Tôi thấy hoa vàng trên cỏ xanh có nhiều nét trong sáng nhưng có những khúc trắc trở hay trầm buồn. Đó chính là cuộc sống. Có nhiều người nghiện truyện Nguyễn Nhật Ánh, có chăng bởi họ nhớ nhung những ngôn ngữ giản dị, gần gũi, tràn đầy hơi thở tuổi thơ trong từng câu chữ. Nếu có một lúc bạn thấy mệt mỏi vì cuộc sống hiện đại, hãy tìm đến Tôi thấy hoa vàng trên cỏ xanh để được thư giãn và trở vể tuổi thơ hồn nhiên, yên bình.</div></h2>");
		blog4.setImage(imageThumbnail4);
		blogRepository.save(blog4);
		
	}

	@Test
	@Order(13)
	public void testCreateFeedBack() {
		
		// Get book
		Book book = bookRepository.findById(1L).get();
		
		// Get user
		User user = userRepository.findById(2L).get();
		
		// Create new feedback
		Feedback feedback = new Feedback();
		feedback.setUser(user);
		feedback.setBook(book);
		feedback.setRating(4);
		feedback.setContent("Quá hay");
	
		feedbackRepository.save(feedback);
	}
	
	@Test
	@Order(14)
	public void testCreateAim() {
		
		Aim aim = new Aim();
		aim.setValue("1000000 4 6 4 20");
		aim.setYear(2022);
		
		aimRepository.save(aim);
	}
	
	
	public void createCustomer() {

		// Create new Cart
		Cart cart = new Cart();
		cart.setCartItems(null);
		cart.setUser(null);
		Cart cartSaved = cartReposiroty.save(cart);

		// Create new Image
		Image imageThumbnail = new Image();
		imageThumbnail.setTitle("2.png");
		imageThumbnail.setUrl("images\\users\\2.png");
		imageThumbnail.setThumbnailName("avtThumbnail.jpg");
		imageThumbnail.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\avtThumbnail.png");
		imageRepository.save(imageThumbnail);

		// get user role
		Role roleUser = roleRepository.findByName(RoleName.USER);

		if (Objects.isNull(roleUser)) {
			log.error(AppConstant.ROLE_NOT_FOUND + "User");
		}

		User user = User.builder().username("kiet").password(passwordEncoder.encode("456"))
				.email("kietle1709@gmail.com").firstName("kiet").lastName("Le Nguyen Tuan")
				.birthday(LocalDate.of(2002, 9, 17)).gender(1) // 1: male, 2: female, 3: Not know
				.phoneNumber("0783511740").enabled(Boolean.TRUE).roles(Arrays.asList(roleUser)).cart(cartSaved)
				.addresses(null).image(imageThumbnail).feedbacks(null).build();
		userRepository.save(user);

	}

	public void createAdmin() {

		// Create new Cart
		Cart cart = new Cart();
		cart.setCartItems(null);
		cart.setUser(null);
		Cart cartSaved = cartReposiroty.save(cart);

		
		// get admin role
		Role roleAdmin = roleRepository.findByName(RoleName.ADMIN);

		if (Objects.isNull(roleAdmin)) {
			log.error(AppConstant.ROLE_NOT_FOUND + " Admin");
		}

		// Create new Image
		Image imageThumbnail = new Image();
		imageThumbnail.setTitle("1.png");
		imageThumbnail.setUrl("images\\users\\1.png");
		imageThumbnail.setThumbnailName("avtThumbnail.png");
		imageThumbnail.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\avtThumbnail.png");
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
				.addresses(null).image(imageThumbnail).cart(cartSaved).feedbacks(null).build();
		userRepository.save(user);

	}

}
