package com.vkr.bookstore.bookstoremodel;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vkr.bookstore.utils.Constants;
/**
* @author  Vivek Kumar
* @version 1.0
* @Date 9/11/2020
*/
@Entity
@Table(name = Constants.BOOK)
public class Book {

	@Id
	private String isbn;
	private String title;
	@ManyToOne
	@JoinColumn(name = Constants.AUTHOR_ID)
	private Author author;
	private String description;
	private String tag;
	private String cover;
	private double price;
	@OneToMany( mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Review> reviews;

	public Book() {
		super();
	}

	public Book(String isbn, String title, Author author, String description, String tag, String cover,
			List<Review> reviews) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.description = description;
		this.tag = tag;
		this.cover = cover;		
		this.reviews = reviews;
	}

	@JsonManagedReference
	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	@JsonBackReference
	public Author getAuthor() {
		return author;
	}	

	public String getIsbn() {
		return isbn;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}
}