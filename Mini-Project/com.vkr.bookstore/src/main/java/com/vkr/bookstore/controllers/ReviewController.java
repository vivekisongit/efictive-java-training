package com.vkr.bookstore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vkr.bookstore.bookstoremodel.Review;
import com.vkr.bookstore.services.ReviewService;
import com.vkr.bookstore.utils.Constants;
/**
* @author  Vivek Kumar
* @version 1.0
* @Date 9/11/2020
*/
@RestController
@RequestMapping(Constants.BASE_PATH_FOR_REVIEW_API)
@CrossOrigin
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	
	@PostMapping(Constants.SLASH_ISBN)
	public Review addReview(@RequestBody Review review,@PathVariable(Constants.ISBN) String isbn) {
		return reviewService.save(review,isbn);
	}

	
	@GetMapping(Constants.SLASH)
	public List<Review> getAllReviews() {
		return reviewService.getAllReviews();
	}

	
	@RequestMapping(value = Constants.SLASH_ISBN, method = RequestMethod.GET)
	public List<Review> getReviewById(@PathVariable String isbn) {
		return reviewService.findReviewById(isbn);
	}

	
	@GetMapping(Constants.RATING_BETWEEN_RANGE_PATH)
	public List<Review> getReviewInRange(@PathVariable(Constants.MIN) int min, @PathVariable(Constants.MAX) int max) {
		return reviewService.getReviewInRange(min, max);
	}

	
	@GetMapping(Constants.REVIEW_CONTAINS_PATH)
	public List<Review> getReviewContainsText(@PathVariable(Constants.TEXT) String text) {
		return reviewService.getReviewContainsText(text);
	}

	
	@GetMapping(Constants.GET_AVERAGE_RATING_PATH)
	public int getAverageRating(@PathVariable(Constants.ISBN) String isbn) {
		return reviewService.getAverageRating(isbn);
	}
}
