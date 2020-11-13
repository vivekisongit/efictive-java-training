INSERT INTO bookstore.author (id,biography,name,photograph) VALUES (1,'An Amazon #1 best-seller epic fiction based on Mahabharata','Vivek Dutta Mishra','the-accursed-god.jpg');
INSERT INTO bookstore.author (id,biography,name,photograph) VALUES (2,'An auto biography of the greatest man of the century','Mahatma Gandhi','my-experiments-with-truth.jpg');


INSERT INTO bookstore.book (isbn,cover,description,tag,title,price,author_id) VALUES ('1111','the-accursed-god.jpg','An Amazon #1 best-seller epic fiction based on Mahabharata','Mahabharata, Epic, Best Seller','The Accursed God',250,1);
INSERT INTO bookstore.book (isbn,cover,description,tag,title,price,author_id) VALUES ('2222','my-experiments-with-truth.jpg','An auto biography of the greatest man of the century','Mahatma Gandhi, autobiography','My Experiments with Truth',350,2);


INSERT INTO bookstore.review (id,rating,review,reviewer,book_id) VALUES (1,5,'A must read book','Rakesh Verma','1111');
INSERT INTO bookstore.review (id,rating,review,reviewer,book_id) VALUES (2,4,'Amazing book','Manish Pal','2222');
