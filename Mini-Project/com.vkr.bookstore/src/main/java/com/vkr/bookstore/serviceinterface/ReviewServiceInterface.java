package com.vkr.bookstore.serviceinterface;

import java.util.List;

import com.vkr.bookstore.bookstoremodel.Review;

public interface ReviewServiceInterface {
	Review save(Review review,String isbn);

	List<Review> getAllReviews();

	List<Review> findReviewById(String isbn);

	List<Review> getReviewInRange(int min, int max);

	List<Review> getReviewContainsText(String text);

	int getAverageRating(String isbn);
}
