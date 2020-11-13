package com.vkr.bookstore.serviceinterface;

import java.util.List;

import com.vkr.bookstore.bookstoremodel.Author;

public interface AuthorServiceInterface {
	Author addAuthor(Author author);

	List<Author> getAllAuthors();

	Author getAuthorById(int id);

	List<Object> getAuthorBooks(int id);

	Author deleteAuthor(int id);

	Author updateAuthor(Author author, int id);

}
