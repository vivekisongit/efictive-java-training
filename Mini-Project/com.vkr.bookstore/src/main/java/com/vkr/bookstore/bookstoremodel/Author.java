package com.vkr.bookstore.bookstoremodel;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vkr.bookstore.utils.Constants;
/**
* @author  Vivek Kumar
* @version 1.0
* @Date 9/11/2020
*/
@Entity
@Table(name = Constants.AUTHOR)
public class Author {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String biography;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = Constants.AUTHOR, fetch = FetchType.LAZY)
	private List<Book> books;
	private String photograph;

	@JsonManagedReference
	public List<Book> getBooks() {
		return books;
	}

	public Author() {
		super();

	}

	public Author(int id, String name, String biography, List<Book> books, String photograph) {
		super();
		this.id = id;
		this.name = name;
		this.biography = biography;
		this.books = books;
		this.photograph = photograph;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public String getPhotograph() {
		return photograph;
	}

	public void setPhotograph(String photograph) {
		this.photograph = photograph;
	}

}
