package com.vkr.bookstore.utils;

public class Constants {
	private Constants() {
	}

	public static final String AUTHOR = "author";
	public static final String BOOK = "Book";
	public static final String AUTHOR_ID = "author_id";
	public static final String REVIEW = "review";
	public static final String BOOK_ID ="book_id";
	public static final String BASE_PATH_FOR_AUTHOR_API ="/api/authors";
	public static final String SLASH_ID ="/{id}";
	public static final String SLASH_ID_SLASH_BOOKS ="/{id}/books";
	public static final String APP_JSON ="application/json";
	public static final String ID ="id";
	public static final String SLASH ="/";
	public static final String BASE_PATH_FOR_BOOKS_API ="/api/books";
	public static final String SLASH_ISBN ="/{isbn}";
	public static final String ISBN ="isbn";
	public static final String MIN ="min";
	public static final String MAX ="max";
	public static final String GET_BOOK_BY_PRICE_PATH ="/price/between/{min}/and/{max}";
	public static final String REVIEW_BY_ISBN_PATH ="/{isbn}/reviews";
	public static final String GET_BOOK_BY_AUTHOR_PATH ="/by/{author_name}";
	public static final String AUTHOR_NAME ="author_name";
	public static final String BASE_PATH_FOR_REVIEW_API ="/api/reviews";
	public static final String TEXT ="text";
	public static final String GET_AVERAGE_RATING_PATH ="/{isbn}/average/rating";
	public static final String REVIEW_CONTAINS_PATH ="/containing/{text}";
	public static final String RATING_BETWEEN_RANGE_PATH ="/rating/between/{min}/and/{max}";
	public static final String QUERY_ON_AUTHOR ="Select b.isbn,b.title from book b where b.author_id=:id";
	public static final String QUERY_ON_BOOK ="select * from book b where  exists(select 1 from author a where a.id=b.author_id and a.name =:authorNamePart)";
	public static final String QUERY_REVIEW_BY_ISBN ="SELECT * from review r where r.book_id LIKE %:isbn%";
	public static final String QUERY_REVIEW_IN_RANGE ="SELECT * from review r  where r.rating BETWEEN :min AND :max";
	public static final String REVIEW_Contains_TEXT ="SELECT * from review r  where r.review LIKE %?1%";
	public static final String REVIEW_AVG_RATING ="SELECT avg(r.rating) as anyVariableName from review r  where r.book_id LIKE %:isbn%";
	public static final String QUERY_BOOK_REVIEW_BY_ISBN ="SELECT r.review from review r where r.book_id=:isbn";
	public static final String QUERY_FIND_BOOK_BY_PRICE ="SELECT * from book b where b.price BETWEEN :min AND :max";
}
