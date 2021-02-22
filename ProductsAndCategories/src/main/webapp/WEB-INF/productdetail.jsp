<!doctype html>
<html lang="en">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<meta charset="UTF-8" />
	<title>Products and Categories</title>
</head>
<body>
	<div class="container mx-auto mt-3" style="width:700px;" >
	<h2><c:out value="${ product.name }"/></h2>
	<div class="container d-flex justify-content-around" >
		<div  class="w-50 p-2">
			<h5 class="mt-4">Categories</h5>
			<ul class="list-group">
			<c:forEach items="${ product.categories }" var="category">
  				<li class="list-group-item"><c:out value="${ category.name }"/></li>
			</c:forEach>
			</ul>
		</div>
		<div  class="w-50 mt-4 p-2">
			<form action="/products/${product.id}/add" method="POST">
				<label for="category_id">Add Category</label>
				<select name="category_id" id="category_id">
				<c:forEach items="${ categories }" var="category">
  				<option class="list-group-item" value="${category.id}"><c:out value="${ category.name }"/></option>
				</c:forEach>
				</select>
				 <div class="row my-2">
	           		<div class="container">
	         	      <button class="btn btn-primary" type="submit">Add</button>
	           		</div>
	           </div>
			</form>
		</div>
	</div>
	</div>
</body>
</html>