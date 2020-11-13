package com.vkr.bookstore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vkr.bookstore.bookstoremodel.Author;
import com.vkr.bookstore.repository.AuthorRepository;
import com.vkr.bookstore.repository.BookRepository;
import com.vkr.bookstore.serviceinterface.AuthorServiceInterface;


@Service
public class AuthorService implements AuthorServiceInterface {

	@Autowired
	AuthorRepository authors;
	@Autowired
	BookRepository books;

	@Override
	public Author addAuthor(Author author) {
		try {
			authors.save(author);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return author;
	}

	@Override
	public List<Author> getAllAuthors() {
		return authors.findAll();
	}

	@Override
	public Author getAuthorById(int id) {		
		return authors.findById(id).orElse(null);
	}

	@Override
	public Author deleteAuthor(int id) {
		Author author = null;
		try {
			author = authors.findById(id).orElse(null);
			if (author != null) {
				authors.deleteById(id);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return author;
	}

	@Override
	public Author updateAuthor(Author author, int id) {
		try {
		Author existing = getAuthorById(id);
		if (existing == null)
			return author;		
		addAuthor(author);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return author;
	}

	@Override
	public List<Object> getAuthorBooks(int id) {
		return authors.getAuthorBooks(id);
	}
}
