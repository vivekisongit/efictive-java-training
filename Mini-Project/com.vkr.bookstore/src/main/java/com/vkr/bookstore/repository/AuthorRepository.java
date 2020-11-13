package com.vkr.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vkr.bookstore.bookstoremodel.Author;
import com.vkr.bookstore.utils.Constants;

/**
* @author  Vivek Kumar
* @version 1.0
* @Date 9/11/2020
*/
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    @Query(value=Constants.QUERY_ON_AUTHOR,nativeQuery=true)
     List<Object> getAuthorBooks(int id);
}
