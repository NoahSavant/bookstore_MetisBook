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

		// Create new Thumbnail for Book1
		Image imageThumbnail1 = new Image();
		imageThumbnail1.setThumbnailName("BookThumbnail.png");
		imageThumbnail1.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\BookThumbnail.png");
		imageRepository.save(imageThumbnail1);

		// Create new Thumbnail for Book2
		Image imageThumbnail2 = new Image();
		imageThumbnail2.setThumbnailName("BookThumbnail.png");
		imageThumbnail2.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\BookThumbnail.png");
		imageRepository.save(imageThumbnail2);

		// Create new Thumbnail for Book3
		Image imageThumbnail3 = new Image();
		imageThumbnail3.setThumbnailName("BookThumbnail.png");
		imageThumbnail3.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\BookThumbnail.png");
		imageRepository.save(imageThumbnail3);

		// Create new Thumbnail for Book4
		Image imageThumbnail4 = new Image();
		imageThumbnail4.setThumbnailName("BookThumbnail.png");
		imageThumbnail4.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\BookThumbnail.png");
		imageRepository.save(imageThumbnail4);

		// Create Book 1
		Book book1 = Book.builder().title("Tôi thấy hoa vàng trên cỏ xanh").available(Boolean.TRUE).price(50000L)
				.category(categoryTieuThuyet).description("Một cuốn tiểu thuyết giành cho giới trẻ").language(language)
				.publicationDate(new Date()).publisherName("Kim Đồng").inventory(inventorySaved1)
				.authors(Arrays.asList(author)).image(imageThumbnail1).build();
		bookRepository.save(book1);

		// Create Book 2

		Book book2 = Book.builder().title("Mắt biếc").available(Boolean.TRUE).category(categoryTrinhTham).price(45000L)
				.description("Một cuốn tiểu thuyết chốn đồng quê").language(language).publicationDate(new Date())
				.publisherName("Kim Đồng").inventory(inventorySaved2).authors(Arrays.asList(author))
				.image(imageThumbnail2).build();
		bookRepository.save(book2);

		// Create Book 3

		Book book3 = Book.builder().title("Game of throne").available(Boolean.TRUE).category(categoryCungDau)
				.price(58000L).description("Một cuốn sách về cung đấu").language(language).publicationDate(new Date())
				.publisherName("Phụ nữ").inventory(inventorySaved3).authors(Arrays.asList(author))
				.image(imageThumbnail3).build();
		bookRepository.save(book3);

		// Create Book 4

		Book book4 = Book.builder().title("Your Name").available(Boolean.TRUE).category(categoryNgonTinh).price(26000L)
				.description("Một cuốn sách ngôn tình").language(language).publicationDate(new Date())
				.publisherName("Kadokawa").inventory(inventorySaved4).authors(Arrays.asList(author))
				.image(imageThumbnail4).build();
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
		address.setRecievePhoneNumber(userAdmin.getPhoneNumber());
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
		addressCustomer.setRecievePhoneNumber(userCustomer.getPhoneNumber());
		addressCustomer.setUser(userCustomer);
		addressRepository.save(addressCustomer);
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
		order1.setPaymentMethod("paypal");
		order1.setUser(user);
		orderRepository.save(order1);

		com.metis.book.model.order.Order order2 = new com.metis.book.model.order.Order();
		order2.setOrderDate(new Date());
		order2.setOrderTrack(trackDelivering);
		order2.setPaymentMethod("momo");
		order2.setUser(user);
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
		imageThumbnail1.setThumbnailName("BlogThumbnail.png");
		imageThumbnail1.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\BlogThumbnail.png");
		imageRepository.save(imageThumbnail1);

		// Create thumbnail image 2
		Image imageThumbnail2 = new Image();
		imageThumbnail2.setThumbnailName("BlogThumbnail.png");
		imageThumbnail2.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\BlogThumbnail.png");
		imageRepository.save(imageThumbnail2);

		// Create thumbnail image 3
		Image imageThumbnail3 = new Image();
		imageThumbnail3.setThumbnailName("BlogThumbnail.png");
		imageThumbnail3.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\BlogThumbnail.png");
		imageRepository.save(imageThumbnail3);

		Blog blog1 = new Blog();
		blog1.setTitle("Đại dương đen: Ánh sáng giữa đại dương tăm tối");
		blog1.setSubTitle("Dữ dội, loang lổ nhưng vỗ về và nhân văn");
		blog1.setContent(
				"<span style=\"color: rgb(34, 34, 34); font-family: Verdana, BlinkMacSystemFont, -apple-system, &quot;Segoe UI&quot;, Roboto, Oxygen, Ubuntu, Cantarell, &quot;Open Sans&quot;, &quot;Helvetica Neue&quot;, sans-serif; font-size: 15px; text-align: justify;\">Tiếp nối thành công từ những cuốn sách trước đó như&nbsp;</span><span style=\"color: rgb(34, 34, 34); font-family: Verdana, BlinkMacSystemFont, -apple-system, &quot;Segoe UI&quot;, Roboto, Oxygen, Ubuntu, Cantarell, &quot;Open Sans&quot;, &quot;Helvetica Neue&quot;, sans-serif; font-size: 15px; text-align: justify;\">“<a href=\"https://reviewsach.net/buc-xuc-khong-lam-ta-vo-can/\" data-wpel-link=\"internal\" rel=\"nofollow noopener noreferrer\" style=\"color: rgb(130, 113, 39);\">Bức xúc không làm ta vô can</a>”, “<a href=\"https://reviewsach.net/thien-ac-va-smartphone/\" data-wpel-link=\"internal\" rel=\"nofollow noopener noreferrer\" style=\"color: rgb(130, 113, 39);\">Thiện – ác và smartphone</a>”, “<a href=\"https://reviewsach.net/tim-minh-trong-the-gioi-hau-tuoi-tho/\" data-wpel-link=\"internal\" rel=\"nofollow noopener noreferrer\" style=\"color: rgb(130, 113, 39);\">Tìm mình trong thế giới hậu tuổi thơ</a>”</span><span style=\"color: rgb(34, 34, 34); font-family: Verdana, BlinkMacSystemFont, -apple-system, &quot;Segoe UI&quot;, Roboto, Oxygen, Ubuntu, Cantarell, &quot;Open Sans&quot;, &quot;Helvetica Neue&quot;, sans-serif; font-size: 15px; text-align: justify;\">, Tiến sĩ Đặng Hoàng Giang công bố cuốn sách mang tên&nbsp;</span><span style=\"color: rgb(34, 34, 34); font-family: Verdana, BlinkMacSystemFont, -apple-system, &quot;Segoe UI&quot;, Roboto, Oxygen, Ubuntu, Cantarell, &quot;Open Sans&quot;, &quot;Helvetica Neue&quot;, sans-serif; font-size: 15px; text-align: justify;\">“Đại dương đen”</span><span style=\"color: rgb(34, 34, 34); font-family: Verdana, BlinkMacSystemFont, -apple-system, &quot;Segoe UI&quot;, Roboto, Oxygen, Ubuntu, Cantarell, &quot;Open Sans&quot;, &quot;Helvetica Neue&quot;, sans-serif; font-size: 15px; text-align: justify;\">&nbsp;– kết tinh hành trình đầy nhọc nhằn và nhẫn nại của tác giả với người trầm cảm trong suốt hai năm ròng. Cuốn sách ghi lại tiếng nói đầy xót xa, ám ảnh của những người đang ngấp ngoải trong “đại dương” trầm cảm, đồng thời đem đến cho người đọc góc nhìn đầy khoa học khi cung cấp những kiến thức cơ bản về trầm cảm từ nguyên nhân, hậu quả, phương thức trị liệu, đến cách đối mặt với trầm cảm.&nbsp;</span><div style=\"text-align: left;\"><span style=\"color: rgb(34, 34, 34); font-family: Verdana, BlinkMacSystemFont, -apple-system, &quot;Segoe UI&quot;, Roboto, Oxygen, Ubuntu, Cantarell, &quot;Open Sans&quot;, &quot;Helvetica Neue&quot;, sans-serif; font-size: 15px; text-align: justify;\">Sách được chia làm 2 phần. Phần đầu là 12 câu chuyện của những nhân vật có thật cùng những chứng bệnh: trầm cảm, rối loạn lưỡng cực, rối loạn lo âu… Phần thứ 2 cung cấp kiến thức, phương pháp trị liệu và những hiểu biết về trầm cảm mà ai cũng nên biết.&nbsp;</span></div><div style=\"text-align: left;\"><div style=\"text-align:center;\"><img src=\"https://reviewsach.net/wp-content/uploads/2022/03/dai-duong-den-dang-hoang-giang.jpeg\"></div><br></div><div style=\"text-align: left;\"><div style=\"text-align: justify;\"><font color=\"#222222\" face=\"Verdana, BlinkMacSystemFont, -apple-system, Segoe UI, Roboto, Oxygen, Ubuntu, Cantarell, Open Sans, Helvetica Neue, sans-serif\"><span style=\"font-size: 15px;\"><br></span></font></div><div style=\"text-align: justify;\"><br></div></div><div><div style=\"text-align: justify;\"><span style=\"font-size: 1rem; text-align: left;\">Cuốn sách trước hết ghi lại những trải nghiệm thực tế của những người mắc bệnh trầm cảm, cách họ sống và làm việc, yêu đương và cống hiến với căn bệnh quái ác nhưng thiếu sự hiểu biết và đồng cảm từ người thân, gia đình, xã hội. Nhân vật chính trong những câu chuyện không bó hẹp với bất cứ ai ở bất kì giới tính, độ tuổi; nghề nghiệp hay địa vị xã hội.&nbsp;</span><br></div></div><div><div>Như tác giả Đặng Hoàng Giang đã tóm tắt:&nbsp;<i>“Nó không chỉ có ở trong giới trẻ, “vì chúng vốn thất thường trong cảm xúc.” Không chỉ ở trong giới văn nghệ sĩ, “vì họ quá nhạy cảm”. Không chỉ ở người có kinh tế đầy đủ, “bởi người nghèo lo kiếm sống thì lấy đâu ra thời gian mà trầm cảm”. Trầm cảm phổ biến như thế nào? Nếu bạn có 1000 người bạn Facebook, thì trong năm qua, bảy mươi người trong số đó mắc trầm cảm.”</i></div><div>Từng câu từng chữ như đan bện vào nhau tạo ra bức màn tăm tối ngăn cách những bệnh nhân trầm cảm được sống, được cống hiến, được yêu thương và hạnh phúc. Những câu chuyện u ám, ảm đạm, ngạt thở, dữ dội, không lối thoát có thể sẽ gây kích động tâm lí cho người đọc:&nbsp;</div><div>Ta được gặp Uyên, 21 tuổi, sinh viên ngành Kinh tế và cách đối chọi với căn bệnh đầy cực đoan: tự làm đau cơ thể. Ham muốn làm đau bản thân dữ dội và thường trực, Uyên luôn thuyết phục bạn bè “đưa cho mình vật gì nhọn để tự hại”. Chỉ khi đó, Uyên mới có thể “<i>trở lại bình thường và sự căng thẳng dịu xuống”.</i></div><div>Hay người mẹ mới sinh bị cô lập trong sự ghẻ lạnh, thờ ơ của cả gia đình bố mẹ đẻ và gia đình nhà chồng đến độ có những suy nghĩ làm hại chính đứa con mình sinh ra:&nbsp;<i>“Tôi biết là mình đang bị trầm cảm. Đây không phải là lần đầu tôi muốn làm cho H đau. Tôi yêu nó vô cùng, tôi đã suýt mất mạng khi sinh nó ra, nhưng tôi vẫn có cảm giác muốn đày đọa nó.”</i></div><div>Căn bệnh trầm cảm dường như cũng “lây lan” như dịch bệnh, căn bệnh mà mầm mống của nó dường như không thể bị dập tắt. Đời ông/bà, đời cha/mẹ, đời con, đời cháu,… cứ thế duy trì những bất ổn trong tinh thần. Rồi từ một thành viên trong gia đình, trầm cảm hủy hoại cuộc sống của tất cả những người xung quanh. Tựa như một giọt dầu loang trên mặt nước, trầm cảm cứ thế nuốt chửng lấy những ánh sáng thoi thóp của ngày tàn:&nbsp;</div><div>Thành, 29 tuổi, là một nhân viên văn phòng, anh chống chọi với căn bệnh trầm cảm trong nhiều năm. Có lẽ nhiều người không thể hiểu được cảm giác mà Thành và gia đình phải chịu đựng.&nbsp;Khi đi tìm nguồn cơn của căn bệnh, Thành nhận ra căn bệnh đang giày vò anh hàng ngày hàng giờ đã từng giày vò ông, cha mình.&nbsp;</div><div>Thành nhớ về những gì được biết về người ông.&nbsp;Bố Thành như bản sao của ông nội và Thành cũng đau đớn nhận ra, anh là người lưu giữ “vật chất di truyền” đầy trái ngang ấy. Không chỉ ông nội và cha, Thành cũng có một người mẹ chịu nhiều thương tổn, chỉ khác là mẹ của Thành có lẽ chưa phải một người trầm cảm hay có các vấn đề về tâm thần khác.</div><div><i>“Mẹ anh lớn lên với nhiều đòn roi từ bà ngoại mà lại ít sự chỉ bảo, và trở thành một kẻ phụ thuộc và nhu nhược. Đến khi đi lấy chồng, mẹ anh vẫn không phân biệt được các loại thịt. Cái điều khiển ti vi mẹ cũng chỉ biết duy nhất cái nút tắt bật và tiến lùi, và tới giờ bà vẫn không biết đi xe máy.</i></div></div><div><i><br></i></div><div><i><img src=\"https://reviewsach.net/wp-content/uploads/2022/03/reviewsach.net-dang-hoang-giang-dai-duong-den.jpeg\" align=\"left\"><br></i></div><div><i><br></i></div><div><i><br></i></div><div><i><br></i></div><div><i><br></i></div><div><i><br></i></div><div><i><br></i></div><div><i><br></i></div><div><i><br></i></div><div><i><br></i></div><div><i><br></i></div><div><i><br></i></div><div><i><br></i></div><div><i><br></i></div><div><i><br></i></div><div><i><br></i></div><div><i><br></i></div><div><i><br></i></div><div><i><br></i></div><div><i><br></i></div><div><i><br></i></div><div><i><br></i></div><div><i><br></i></div><div><div>Trầm cảm không phải bẩm sinh, những nạn nhân trầm cảm cũng không đối diện với nó theo một mô thức giống nhau. Nhưng kì lạ thay, giữa những con người có những phản ứng trái chiều đối với tổn thương trong quá khứ lại nảy sinh sự hòa hợp đầy “bạo lực và phá hủy”. Bố Thành đập phá cửa nhà, mẹ Thành làm như không rồi hàn gắn những đổ vỡ. Bố Thành “điên loạn”, phá phách, mẹ Thành nhẫn nhục, chịu đựng. Nỗi đau của bệnh nhân trầm cảm là một, nỗi đau của những người thân, gia đình và xã hội là mười.&nbsp;</div><div>Những đoạn văn đầy tăm tối có thể khiến bạn đọc bị kích động mạnh. Nhưng có lẽ, đó là cách duy nhất để mỗi chúng ta có cái nhìn thực tế, khách quan, toàn diện hơn về thế giới của người trầm cảm. Tác giả làm tất cả điều đó với mong muốn kêu gọi mọi người có cái nhìn chân thật, có sự đồng cảm với hoàn cảnh của họ.&nbsp;</div><div>Không chỉ đưa ra những câu chuyện thực tế, Đặng Hoàng Giang còn dày công nghiên cứu, tổng hợp kiến thức từ nhiều nguồn để xây dựng phần hai với những kiến thức cơ bản, thực tiễn, cần thiết cho tất cả mọi người. Suy cho cùng, xã hội đang chung sống với căn bệnh trầm cảm – bạn trung thành của Thần Chết. Trầm cảm không miễn trừ cho bất cứ ai và khi nó đến sẽ như một cơn bão vô hình, hủy diệt và cuốn trôi tất cả.&nbsp;</div><div>Tựa như một bác sĩ với con dao lam sắc nhọn trong tay, Đặng Hoàng Giang dũng cảm mổ xẻ những vấn đề gai góc nhất, khó nhằn nhất của xã hội bằng ngôn từ đầy khoa học, khách quan mà dễ hiểu.&nbsp;</div><div>Tôi không có nhiều kiến thức về tâm lí học, cũng không có nhiều trải nghiệm với bệnh nhân trầm cảm, nhưng tôi tiếp nhận cuốn sách với tất cả sự cảm thông và trân trọng đối với những bệnh nhân, người nhà của họ, và đặc biệt là tác giả cùng đội ngũ của ông. Trầm cảm nguy hại đến sức khỏe tinh thần, đe dọa trực tiếp đến tính mạng của bệnh nhân và những người xung quanh.</div></div><div><br></div><div><img src=\"https://reviewsach.net/wp-content/uploads/2022/03/reviewsach.net-dai-duong-den-by-dang-hoang-giang.jpeg\" align=\"left\"><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><br></div><div><div>Ông vì thế viết nên cuốn sách này bằng giọng văn vừa sâu sắc, cảm thương, vừa khoa học, chính xác, có gì đó khác với các tác phẩm trước đó – phần nhiều là bình luận, phê phán, lên án những hiện tượng, sự kiện, con người xã hội. Thật cảm phục cho sự nhẫn nại, khoan dung của tác giả khi lắng tai nghe mọi thanh âm của cuộc sống, dũng cảm cất tiếng nói thay những người yếu thế, cảnh tỉnh xã hội về một căn bệnh sinh ra bởi chính nó. Người đọc buộc phải nhìn rất thẳng, rất sâu vào vấn đề được đặt ra, vì đối mặt và chống lại trầm cảm là nghĩa vụ của tất cả chúng ta.&nbsp;</div><div>Tiến sĩ Đặng Hoàng Giang cũng là người khởi xướng đường dây nóng Ngày Mai, một hoạt động phi lợi nhuận nhằm cung cấp miển phí tham vấn tâm lý cho người trầm cảm, và nâng cao nhận thức của cộng đồng về sức khỏe tâm thần. Cuốn sách vì thế có giá trị thực tiễn rất cao.&nbsp;</div><div>Có thể một số người có đủ sức vượt qua trầm cảm và sống một cuộc sống bình thản, an yên, hạnh phúc. Nhưng ai cũng biết điều đó là rất khó, phần nhiều, cuộc sống của những bệnh nhân trầm cảm sẽ mãi chìm trong đại dương tăm tối nếu không có ai đó tìm đến và lắng nghe câu chuyện của họ, cố gắng cứu lấy họ khi đang chấp chới. Sự ra đời của cuốn sách và sự đón nhận nhiệt liệt của độc giả có lẽ là tín hiệu đáng mừng, thể hiện sự quan tâm của mọi người đến căn bệnh trầm cảm. Dù chỉ le lói, ánh sáng của tình yêu thương, lòng vị tha hẳn vẫn tồn tại trong xã hội hối hả mà sự bận rộn của cuộc sống khiến chúng ta ít khi nói về nó.&nbsp;&nbsp;</div><div>Không ai biết được cái kết của những nhân vật trong sách, cũng không ai có thể đảm bảo rằng, những phương pháp trị liệu được gợi ý sẽ có tác động thực tế hay không, đường dây nóng Ngày Mai sẽ giúp ích cho bao nhiêu người. Nhưng có một điều là chắc chắn: Chúng ta có thể ngăn chặn và khắc phục những hậu quả kinh hoàng của trầm cảm bằng nhiều cách khác nhau. Thiết nghĩ, cách tốt nhất là thể hiện sự quan tâm, yêu thương của mình đến mọi người xung quanh, đặc biệt là những người thân trong gia đình. Căn bệnh nào cũng cần được chữa trị. Ai ai cũng cần được lắng nghe, được yêu thương và giúp đỡ.&nbsp;</div></div>");
		blog1.setImage(imageThumbnail1);
		blogRepository.save(blog1);

		Blog blog2 = new Blog();
		blog2.setTitle("Cho em 9 điểm nha cô");
		blog2.setSubTitle("Nhaaaaaaaaaaaaaaaaaaaa");
		blog2.setContent("Cho em 9 điểm đi mà");
		blog2.setImage(imageThumbnail2);
		blogRepository.save(blog2);

		Blog blog3 = new Blog();
		blog3.setTitle("Cho em 8 điểm nha cô");
		blog3.setSubTitle("Nhaaaaaaaaaaaaaaaaaaaaaa");
		blog3.setContent("Cho em 8 điểm đi mà");
		blog3.setImage(imageThumbnail3);
		blogRepository.save(blog3);
	}

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
				.phoneNumber("0783511740").enabled(Boolean.TRUE).roles(Arrays.asList(roleUser)).cart(cartSaved)
				.addresses(null).image(imageThumbnail).build();
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
				.addresses(null).image(imageThumbnail).cart(cartSaved).build();
		userRepository.save(user);

	}

}
