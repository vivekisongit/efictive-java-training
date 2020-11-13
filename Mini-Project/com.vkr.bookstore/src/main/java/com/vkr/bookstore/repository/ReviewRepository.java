package com.vkr.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vkr.bookstore.bookstoremodel.Review;
import com.vkr.bookstore.utils.Constants;
/**
* @author  Vivek Kumar
* @version 1.0
* @Date 9/11/2020
*/
@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

	@Query(value = Constants.QUERY_REVIEW_BY_ISBN, nativeQuery = true)
	List<Review> getReviewByIsbn(String isbn);

	@Query(value = Constants.QUERY_REVIEW_IN_RANGE, nativeQuery = true)
	List<Review> getReviewInRange(int min, int max);

	@Query(value = Constants.REVIEW_Contains_TEXT, nativeQuery = true)
	List<Review> getReviewContainsText(String text);

	@Query(value = Constants.REVIEW_AVG_RATING, nativeQuery = true)
	int getAverageRating(String isbn);
}