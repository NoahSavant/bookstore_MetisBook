package com.metis.book.serviceImpl;

import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metis.book.dto.BookForm;
import com.metis.book.model.Author;
import com.metis.book.model.Book;
import com.metis.book.model.Image;
import com.metis.book.model.Inventory;
import com.metis.book.repository.AuthorRepository;
import com.metis.book.repository.BookRepository;
import com.metis.book.repository.CategoryRepository;
import com.metis.book.repository.ImageRepository;
import com.metis.book.repository.InventoryRepository;
import com.metis.book.repository.LanguageRepository;
import com.metis.book.service.IBookService;
import com.metis.book.utils.AppConstant;
import com.metis.book.utils.FileUploadUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookServiceImpl implements IBookService{

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
		book.setCategory(categoryRepository.findById(Long.parseLong(bookForm.getCategory())).get());
		Inventory inventory = new Inventory();
		inventory.setQuantiy(Integer.parseInt(bookForm.getQuantity()));
		inventoryRepository.save(inventory);
		book.setInventory(inventory);
		//log.info(book.toString());
		
		
		Book bookSaved =  bookRepository.save(book);
		if(!Objects.isNull(bookForm.getFile())) {
			Path fileNameAndPath = FileUploadUtils.saveBookImage(bookForm.getFile(),bookSaved.getId());
			Image image = new Image();
			image.setTitle(bookSaved.getId().toString()+".png");
			image.setUrl(fileNameAndPath.toString());
			Image imageSaved = imageRepository.save(image);
			bookSaved.setImage(imageSaved);
			bookRepository.save(bookSaved);
		}

		
	}

	@Override
	public List<Book> getTopFeatured() {
		List<Book> topFeatured = new ArrayList<>();
		List<Book> books = bookRepository.findAll();
		topFeatured.add(books.get(0));
		topFeatured.add(books.get(1));
		return topFeatured;
	}

	@Override
	public List<Book> getBestSeller() {
		List<Book> bestSeller = new ArrayList<>();
		List<Book> books = bookRepository.findAll();
		bestSeller.add(books.get(2));
		bestSeller.add(books.get(3));
		return bestSeller;
	}

}
