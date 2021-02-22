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
	<div class="row my-2 text-center ">
	  	<div class="class=col-2">
			<a href="/categories/new">Add Category</a> &nbsp; &nbsp;		
		</div>
		<div class="container my-2">
			<h3>New Product</h3>
		</div>
		<div class="container">
		<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
	    	<form:form action="/products/create" method="POST" modelAttribute="product" class="d-flex flex-column align-items-center">
	   			<div class="row my-2">
	               <form:label path="name" class="col-5">Name</form:label>
	               <form:input path="name" class="col-5" type="text" name="name"/>
	               <form:errors path="name" class="col-5 text-danger"/>
	           </div>
	           <div class="row my-2">
	               <form:label path="description" class="col-5">Description</form:label>
	               <form:input path="description" class="col-5" type="text" name="description"/>
	               <form:errors path="description" class="col-5 text-danger"/>
	           </div>
	           <div class="row my-2">
	               <form:label path="price" class="col-5">price</form:label>
	               <form:input path="price" class="col-5" type="number" name="price" step="0.01"/>
	               <form:errors path="price" class="col-5 text-danger"/>
	           </div>
	           <div class="row my-2">
	           		<div class="container">
	         	      <button class="btn btn-primary" type="submit">Create</button>
	           		</div>
	           </div>
	       </form:form>
		</div>
	</div>
</body>
</html>