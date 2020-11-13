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

import com.vkr.bookstore.bookstoremodel.Author;
import com.vkr.bookstore.repository.AuthorRepository;
import com.vkr.bookstore.services.AuthorService;
import com.vkr.bookstore.utils.Constants;
/**
* @author  Vivek Kumar
* @version 1.0
* @Date 9/11/2020
*/
@RestController
@RequestMapping(Constants.BASE_PATH_FOR_AUTHOR_API)
public class AuthorController {

	@Autowired
	AuthorService service;
	@Autowired
	AuthorRepository authorRepository;

	@GetMapping(Constants.SLASH)
	public List<Author> getAllAuthors() {
		return service.getAllAuthors();
	}

	@GetMapping(Constants.SLASH_ID)
	public Author getAuthorById(@PathVariable(Constants.ID) int id) {
		return service.getAuthorById(id);
	}

	@GetMapping(Constants.SLASH_ID_SLASH_BOOKS)
	public List<Object> getAuthorBooks(@PathVariable(Constants.ID) int id) {
		return service.getAuthorBooks(id);
	}

	@PostMapping(value = Constants.SLASH, consumes = Constants.APP_JSON)
	public Author addAuthor(@RequestBody Author author) {
		return service.addAuthor(author);

	}

	@PutMapping(value = Constants.SLASH_ID, consumes = Constants.APP_JSON)
	public Author updateAuthor(@RequestBody Author author, @PathVariable(Constants.ID) int id) {
		return service.updateAuthor(author, id);

	}
	
	@DeleteMapping(Constants.SLASH_ID)
	public Author deleteAuthor(@PathVariable(Constants.ID) int id) {
		return service.deleteAuthor(id);
	}
}