<!doctype html>
<html lang="en">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
	<meta charset="UTF-8" />
	<title>Dojo Overflow</title>
</head>
<body>
	<div class="container mx-auto mt-3" style="width:800px;">
		<h2 class="mb-3">What is Your Question?</h2>
		<div class="container my-2">
		<form action="/questions/create" method="post">
			<div id="error">
				<c:out value="${error}"/>
			</div>
			<div class="row my-2">
				<label for="question" class="col-3">Question:</label>
	            <input class="col-5" type="text" name="question"/>
	        </div>
	        <div class="row my-2">
	            <label class="col-3" for="tag">Tag:</label>
	            <input class="col-5" type="text" name="tag" >
	        </div>
	        <div class="row my-2">
	        	<div class="container">
	      	      <button class="btn btn-primary" type="submit">Submit</button>
	        	</div>
	        </div>
		</form>
		</div>
	</div>
</body>
</html>