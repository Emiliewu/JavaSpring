<!doctype html>
<html lang="en">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<meta charset="UTF-8" />
	<title>Languages</title>
</head>
<body>
	<div class="container .mt-3 .w-80">
	  <div class="row justify-content-md-center my-2">
	  <div class="col-1">
	 	<a href="/languages">Dashboard</a>
	 	</div>

	  	<p><c:out value="${ language.name }"/></p>
	  	<p><c:out value="${ language.creator }"/></p>
	  	<p><c:out value="${ language.version }"/></p>
	  	<p>
	  		<a href="/languages/${language.id}/edit">Edit</a>&nbsp; |&nbsp; 
			<a href="/languages/delete/${language.id }">Delete</a>
	  	</p>
	

	  </div>
	</div>
</body>
</html>