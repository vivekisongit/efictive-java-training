package com.vkr.bookstore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vkr.bookstore.bookstoremodel.Book;
import com.vkr.bookstore.repository.AuthorRepository;
import com.vkr.bookstore.repository.BookRepository;
import com.vkr.bookstore.serviceinterface.BookServiceInterface;

@Service
public class BookService implements BookServiceInterface {
	@Autowired
	BookRepository books;

	@Autowired
	AuthorRepository authors;

	@Override
	public Book addBook(Book book) {
		books.save(book);
		return book;
	}

	@Override
	public List<Book> getAllBooks() {
		return books.findAll();
	}

	@Override
	public Book getBookByIsbn(String isbn) {
		return books.findById(isbn).orElse(null);
	}

	@Override
	public Book removeBook(String isbn) {
		Book book = books.findById(isbn).orElse(null);
		if (book != null) {
			books.deleteById(isbn);
		}
		return book;
	}

	@Override
	public Book updateBook(Book book, String isbn) {
		Book existing = getBookByIsbn(book.getIsbn());
		if (existing == null)
			return existing;

		return addBook(book);

	}

	@Override
	public List<Book> getBooksByAuthor(String authorName) {
		return books.findByAuthorContainingIgnoreCase(authorName);
	}

	public void deleteBook(String isbn) {
		books.deleteById(isbn);
	}

	public List<Object> ReviewById(String isbn) {
		return books.reviewbyIsbn(isbn);
	}

	@Override
	public List<Book> getBooksInPriceRange(int min, int max) {
		return books.findbookByPrice(min, max);
	}
}
