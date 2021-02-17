<!doctype html>
<html lang="en">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<meta charset="UTF-8" />
	<title>Lookify</title>
</head>
<body>
	<div class="container mt-2">
		<div class="row text-right"> <a href="/dashboard">Dashboard</a></div>
		<div class="row my-2">
		<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
    	<form:form action="/songs/new" method="POST" modelAttribute="song">
           <div class="row my-2">
               <form:label path="title" class="col-2">Title</form:label>
               <form:input path="title" class="col-5" type="text"/>
               <form:errors path="title" class="col-2 text-danger"/>
           </div>
           <div class="row my-2">
               <form:label path="artist" class="col-2">Artist</form:label>
               <form:input path="artist" class="col-5" type="text" />
               <form:errors path="artist" class="col-2 text-danger"/>
           </div>
           <div class="row my-2">
               <form:label path="rating" class="col-2">Rating</form:label>
               <form:input path="rating" type="number" name="rating" class="col-1" min="0" max="10" value="10" />
               <form:errors path="rating" class="col-2 text-danger"/>
           </div>
           <div class="container">
               <button class="btn btn-primary" type="submit">Submit</button>
           </div>
       </form:form>
		</div>
	</div>
	
</body>
</html>