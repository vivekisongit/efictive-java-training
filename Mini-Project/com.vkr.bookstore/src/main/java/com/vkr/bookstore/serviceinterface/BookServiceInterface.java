package com.vkr.bookstore.serviceinterface;

import java.util.List;

import com.vkr.bookstore.bookstoremodel.Book;

public interface BookServiceInterface {

	Book addBook(Book book);

	List<Book> getAllBooks();

	Book getBookByIsbn(String isbn);

	Book removeBook(String isbn);

	Book updateBook(Book book, String isbn);
	
	List<Object> ReviewById(String isbn);


	List<Book> getBooksByAuthor(String authorName);

	List<Book> getBooksInPriceRange(int min, int max);	

}