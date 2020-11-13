
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book Store API Home Page</title>
</head>
<body>

<h1>Welcome to the VKR Book Store</h1>

<h2>Below are the available services for Author</h2>
<ul>
	<c:forEach items="${authorServices}" var="authorService">
		<li><a href="${authorService.url }">${authorService.service }</a></li>
	</c:forEach>

</ul>


<h2>Below are the available services for Book</h2>
<ul>
	<c:forEach items="${bookServices}" var="bookService">
		<li><a href="${bookService.url }">${bookService.service }</a></li>
	</c:forEach>

</ul>

<h2>Below are the available services for Review</h2>
<ul>
	<c:forEach items="${reviewServices}" var="reviewService">
		<li><a href="${reviewService.url }">${reviewService.service }</a></li>
	</c:forEach>

</ul>
</body>
</html>