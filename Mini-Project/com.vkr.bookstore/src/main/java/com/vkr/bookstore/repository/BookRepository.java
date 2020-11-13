
package com.vkr.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vkr.bookstore.bookstoremodel.Book;
import com.vkr.bookstore.utils.Constants;
/**
* @author  Vivek Kumar
* @version 1.0
* @Date 9/11/2020
*/
public interface BookRepository extends JpaRepository<Book, String> {

	@Query(value =Constants.QUERY_ON_BOOK, nativeQuery = true)
	List<Book> findByAuthorContainingIgnoreCase(String authorNamePart);


	@Query(value = Constants.QUERY_BOOK_REVIEW_BY_ISBN, nativeQuery = true)
	List<Object> reviewbyIsbn(String isbn);

	@Query(value = Constants.QUERY_FIND_BOOK_BY_PRICE, nativeQuery = true)
	List<Book> findbookByPrice(int min, int max);
	




}
