package com.metis.book.serviceImpl;

import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import com.metis.book.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metis.book.dto.BookForm;
import com.metis.book.dto.FilterForm;
import com.metis.book.model.Author;
import com.metis.book.model.Book;
import com.metis.book.model.CartItem;
import com.metis.book.model.Image;
import com.metis.book.model.Inventory;
import com.metis.book.model.order.Order;
import com.metis.book.model.order.OrderItem;
import com.metis.book.model.order.OrderTrack;
import com.metis.book.service.IBookService;
import com.metis.book.utils.AppConstant;
import com.metis.book.utils.FileUploadUtils;
import com.metis.book.utils.Swap;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookServiceImpl implements IBookService {

	@Autowired
	AuthorRepository authorRepository;
	@Autowired
	LanguageRepository languageRepository;
	@Autowired
	BookRepository bookRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	InventoryRepository inventoryRepository;
	@Autowired
	ImageRepository imageRepository;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	OrderItemRepository orderItemRepository;
	@Autowired
	UserRepository userRepository;

	@Override
	public void insert(BookForm bookForm) throws ParseException, IOException {
		Book book = new Book();
		List<Author> authors = new ArrayList<>();
		for (String author : bookForm.getAuthors()) {
			authors.add(authorRepository.findById(Long.parseLong(author)).get());
		}

		book.setAuthors(authors);
		book.setLanguage(languageRepository.findById(Long.parseLong(bookForm.getLanguage())).get());
		book.setDescription(bookForm.getDescription());
		book.setPrice(Long.parseLong(bookForm.getPrice()));
		book.setPublisherName(bookForm.getPublisherName());
		book.setTitle(bookForm.getTitle());
		book.setPublicationDate(new SimpleDateFormat("yyyy-MM-dd").parse(bookForm.getPublicationDate()));
		book.setAvailable(true);
		book.setCategory(categoryRepository.findById(bookForm.getCategory().getId()).get());
		Inventory inventory = new Inventory();
		inventory.setQuantiy(Integer.parseInt(bookForm.getQuantity()));
		inventoryRepository.save(inventory);
		book.setInventory(inventory);
		// log.info(book.toString());

		Book bookSaved = bookRepository.save(book);
		if (!bookForm.getFile().isEmpty()) {
			Path fileNameAndPath = FileUploadUtils.saveBookImage(bookForm.getFile(), bookSaved.getId());
			Image image = new Image();
			image.setTitle(bookSaved.getId().toString() + ".png");
			image.setUrl(fileNameAndPath.toString());
			Image imageSaved = imageRepository.save(image);
			bookSaved.setImage(imageSaved);
			bookRepository.save(bookSaved);
		} else {
			// Create thumbnail image 1
			Image imageThumbnail = new Image();
			imageThumbnail.setThumbnailName("BookThumbnail.png");
			imageThumbnail
					.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\BookThumbnail.png");
			imageRepository.save(imageThumbnail);
			bookSaved.setImage(imageThumbnail);
			bookRepository.save(bookSaved);
		}

	}

	@Override
	public List<Book> getTopFeatured() {
		List<Book> topFeatured = new ArrayList<>();
		List<Book> books = bookRepository.findAll();
		if (!books.isEmpty()) {
			topFeatured.add(books.get(0));
			topFeatured.add(books.get(1));
		}
		return topFeatured;
	}

	@Override
	public List<Book> getBestSeller() {
		List<Book> bestSeller = new ArrayList<>();
		List<Book> books = bookRepository.findAll();
		if (!books.isEmpty()) {
			bestSeller.add(books.get(2));
			bestSeller.add(books.get(3));
		}
		return bestSeller;
	}

	@Override
	public List<Book> getAllBooks() {
		List<Book> books = bookRepository.findAll();
		return books;
	}

	@Override
	public List<String> getAllPublishers() {
		List<Book> books = bookRepository.findAll();
		List<String> publishers = new ArrayList<>();
		for (Book book : books) {
			if (!publishers.contains(book.getPublisherName())) {
				publishers.add(book.getPublisherName());
			}
		}
		return publishers;
	}

	@Override
	public Long getMaxPrice() {
		List<Book> books = bookRepository.findAll();
		Long max = 0L;
		for (Book book : books) {
			if (book.getPrice() > max) {
				max = book.getPrice();
			}
		}

		double tempPrice = (double) max;
		while (tempPrice > 10) {
			tempPrice = tempPrice / 10;
		}
		tempPrice = Math.ceil(tempPrice);

		while (tempPrice < max) {
			tempPrice = tempPrice * 10;
		}
		return (long) tempPrice;
	}

	@Override
	public Long getNumAllBooks() {
		List<Book> books = bookRepository.findAll();
		return (long) books.size();
	}

	@Override
	public List<Book> getBooksByCategory(String category) {
		List<Book> books = new ArrayList<>();
		for (Book book : bookRepository.findAll()) {
			if (book.getCategory().getDomain().compareTo(category) == 0) {
				books.add(book);
			}
		}
		return books;
	}

	public List<BookForm> getBookShows() {
		List<Book> books = bookRepository.findAll();
		List<BookForm> bookForms = new ArrayList<>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		for (Book book : books) {
			BookForm bookForm = new BookForm();
			List<String> authorNames = new ArrayList<>();
			for (Author author : book.getAuthors()) {
				authorNames.add(author.getName());
			}
			bookForm.setAuthors(authorNames);
			bookForm.setCategory(book.getCategory());
			bookForm.setDescription(book.getDescription());
			// bookForm.setFile(book.getImage());
			bookForm.setLanguage(book.getLanguage().getName());
			bookForm.setPrice(book.getPrice().toString());
			bookForm.setPublicationDate(formatter.format(book.getPublicationDate()));
			bookForm.setPublisherName(book.getPublisherName());
			bookForm.setQuantity(book.getInventory().getQuantiy().toString());
			bookForm.setTitle(book.getTitle());
			bookForm.setId(book.getId().toString());
			bookForm.setAvailable(book.getAvailable() == true ? "Còn bán" : "Ngưng bán");
			if (book.getCreateBy()!=null){
				bookForm.setCreateBy(userRepository.findById(book.getCreateBy()).get().getUsername());
			} else {
				bookForm.setCreateBy("");
			}
			if (book.getUpdateBy()!=null){
				bookForm.setLastUpdateBy(userRepository.findById(book.getUpdateBy()).get().getUsername());
			} else {
				bookForm.setLastUpdateDate("");
			}
			bookForm.setCreateDate(formatter.format(book.getCreatedAt()));
			bookForm.setLastUpdateDate(formatter.format(book.getUpdatedAt()));
			bookForms.add(bookForm);
		}
		return bookForms;
	}

	@Override
	public void deleteById(Long bookId) {
		bookRepository.deleteById(bookId);

	}

	@Override
	public BookForm getById(Long bookId) {
		Book book = bookRepository.findById(bookId).get();
		if (Objects.isNull(book)) {
			log.error(AppConstant.BOOK_NOT_FOUND + bookId.toString());
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		BookForm bookForm = new BookForm();
		List<String> authorNames = new ArrayList<>();
		if (book.getAuthors().size() > 0) {
			for (Author author : book.getAuthors()) {
				authorNames.add(author.getName());
			}
		}
		bookForm.setAuthors(authorNames);
		bookForm.setCategory(book.getCategory());
		bookForm.setDescription(book.getDescription());
		// bookForm.setFile(book.getImage());
		if (!Objects.isNull(book.getImage())) {
			bookForm.setImageName(book.getImage().getTitle());
			bookForm.setThumbnailName(book.getImage().getThumbnailName());
		}
		bookForm.setId(bookId.toString());
		bookForm.setLanguage(book.getLanguage().getName());
		bookForm.setPrice(book.getPrice().toString());
		bookForm.setPublicationDate(formatter.format(book.getPublicationDate()));
		log.info(bookForm.getPublicationDate());
		bookForm.setPublisherName(book.getPublisherName());
		bookForm.setQuantity(book.getInventory().getQuantiy().toString());
		bookForm.setTitle(book.getTitle());
		bookForm.setAvailable(book.getAvailable() == true ? "Còn bán" : "Ngưng bán");
		return bookForm;
	}

	@Override
	public void updateBook(BookForm bookForm) throws ParseException, IOException {
		Book book = bookRepository.findById(Long.parseLong(bookForm.getId())).get();
		if (Objects.isNull(book)) {
			log.error(AppConstant.BOOK_NOT_FOUND + bookForm.getId());
		} else {
			
			List<Author> authors = new ArrayList<>();
			for (String author : bookForm.getAuthors()) {
				authors.add(authorRepository.findByName(author));
			}
			book.setAuthors(authors);
			
			try {
				book.setLanguage(languageRepository.findById(Long.parseLong(bookForm.getLanguage())).get());
			} catch (Exception e) {
				System.out.println(bookForm.getLanguage());
			}
			
			book.setDescription(bookForm.getDescription());
			book.setPrice(Long.parseLong(bookForm.getPrice()));
			book.setPublisherName(bookForm.getPublisherName());
			book.setTitle(bookForm.getTitle());
			book.setPublicationDate(new SimpleDateFormat("yyyy-MM-dd").parse(bookForm.getPublicationDate()));
			book.setAvailable(bookForm.getAvailable().equals("Còn bán") ? true : false);
			book.setCategory(categoryRepository.findById(bookForm.getCategory().getId()).get());
			Inventory inventory = new Inventory();
			inventory.setQuantiy(Integer.parseInt(bookForm.getQuantity()));
			inventoryRepository.save(inventory);
			book.setInventory(inventory);
			// log.info(book.toString());

			Book bookSaved = bookRepository.save(book);
			if (!bookForm.getFile().isEmpty()) {
				Path fileNameAndPath = FileUploadUtils.saveBookImage(bookForm.getFile(), bookSaved.getId());
				Image image = new Image();
				image.setTitle(bookSaved.getId().toString() + ".png");
				image.setUrl(fileNameAndPath.toString());
				Image imageSaved = imageRepository.save(image);
				bookSaved.setImage(imageSaved);
				bookRepository.save(bookSaved);
			}
		}
		

	}

	@Override
	public List<Book> filter(List<Book> books, FilterForm filterForm) {
		List<Book> filterBooks = new ArrayList<>();
		for (Book book : books) {
			if (book.getPrice() >= filterForm.getMinPrice() && book.getPrice() <= filterForm.getMaxPrice()) {
				filterBooks.add(book);
			}
		}
		if (filterForm.getPublisherName().compareTo("Tất cả") != 0) {
			books.clear();
			books.addAll(filterBooks);

			filterBooks.clear();
			for (Book book : books) {
				if (book.getPublisherName().compareTo(filterForm.getPublisherName()) == 0) {
					filterBooks.add(book);
				}
			}
		}
		if (filterBooks.size() < 2) {
			return filterBooks;
		} else if (filterForm.getSort() != "none") {
			int min;
			int n = filterBooks.size();
			for (int i = 0; i < n - 1; i++) {
				min = i;
				for (int j = i + 1; j < n; j++) {
					if (filterBooks.get(j).getPrice() < filterBooks.get(min).getPrice())
						min = j;
				}
				swap(i, min, filterBooks);
			}

			if (filterForm.getSort() == "decre") {
				Collections.reverse(filterBooks);
			}
		}
		
		if(filterForm.getTextSearch() != "") {
			books.clear();
			books.addAll(filterBooks);
			filterBooks.clear();
			String keyword = filterForm.getTextSearch();
			for (Book book : books) {
				if(book.keywordInTitle(keyword)) filterBooks.add(book);
			}
		}
		return filterBooks;
	}

	private List<Book> swap(int index1, int index2, List<Book> list) {
		Book temp = list.get(index1);
		list.set(index1, list.get(index2));
		list.set(index2, temp);
		return list;
	}

	@Override
	public int getSoldNumberById(Long bookId) {
		int sold = 0;
		Book book = bookRepository.findById(bookId).get();
		if (Objects.isNull(book)) {
			log.error(AppConstant.BOOK_NOT_FOUND + bookId);
			return -1;
		}
		List<Order> orders = orderRepository.findAll();
		for (Order order : orders) {
			if (!order.getOrderTrack().getStatus().equals("Chờ thanh toán")) {
				for (OrderItem orderItem : order.getOrderItems()) {
					if (orderItem.getBook().getId() == bookId) {
						sold = sold + orderItem.getQuantity();
					}

				}
			}
		}

		return sold;
	}

}
