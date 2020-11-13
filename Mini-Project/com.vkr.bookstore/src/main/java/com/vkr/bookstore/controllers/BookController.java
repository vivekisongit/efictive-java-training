
package com.vkr.bookstore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vkr.bookstore.bookstoremodel.Book;
import com.vkr.bookstore.repository.AuthorRepository;
import com.vkr.bookstore.repository.BookRepository;
import com.vkr.bookstore.services.BookService;
import com.vkr.bookstore.utils.Constants;
/**
* @author  Vivek Kumar
* @version 1.0
* @Date 9/11/2020
*/
@RestController()
@RequestMapping(Constants.BASE_PATH_FOR_BOOKS_API)
public class BookController {

	@Autowired
	BookService service;

	@Autowired
	BookRepository bookRepository;

	@Autowired
	AuthorRepository authorRepository;
	
	@GetMapping(Constants.SLASH)
	public List<Book> getAllBooks() {
		return service.getAllBooks();
	}
	
	
	@GetMapping(Constants.SLASH_ISBN)
	public Book getBookById(@PathVariable(Constants.ISBN) String isbn) {
		return service.getBookByIsbn(isbn);
	}

	
	@PutMapping(value = Constants.SLASH_ISBN, consumes = Constants.APP_JSON)
	public Book updateAuthor(@RequestBody Book book, @PathVariable(Constants.ISBN) String isbn) {
		
		return service.updateBook(book, isbn);

	}

	
	@PostMapping(value = Constants.SLASH, consumes = Constants.APP_JSON)
	public Book addBook(@RequestBody Book book) {

		return service.addBook(book);
	}

	
	@DeleteMapping(Constants.SLASH_ISBN)
	public Book deleteBook(@PathVariable(Constants.ISBN) String isbn) {
		return service.removeBook(isbn);
	}

	
	@GetMapping(Constants.GET_BOOK_BY_AUTHOR_PATH)
	public List<Book> getBooksByAuthor(@PathVariable(Constants.AUTHOR_NAME) String author_name) {
		return service.getBooksByAuthor(author_name);
	}

	
	@GetMapping(Constants.REVIEW_BY_ISBN_PATH)
	public List<Object> ReviewsByIsbn(@PathVariable(Constants.ISBN) String isbn) {
		return service.ReviewById(isbn);
	}

	
	@GetMapping(Constants.GET_BOOK_BY_PRICE_PATH)
	public List<Book> getBooksByPrice(@PathVariable(Constants.MIN) int min, @PathVariable(Constants.MAX) int max) {
		return service.getBooksInPriceRange(min, max);
	}
	
	

}
