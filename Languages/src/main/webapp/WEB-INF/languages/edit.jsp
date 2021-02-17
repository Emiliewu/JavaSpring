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
	<div class="container w-60">
	 <div class="row my-2 justify-content-end">
	  	<div class="col-1">
	 		<a href="/languages/delete/${language.id }">Delete</a>
	 	</div>
	 	 <div class="col-1">
	 	<a href="/languages">Dashboard</a>
	 	</div>
	 </div>
	<div class="container w-60  align-middle">
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
    	<form:form action="/languages/edit/${language.id}" method="PUT" modelAttribute="lang">
           <div class="row my-2">
               <form:label path="name" class="col-2">Name</form:label>
               <form:input path="name" class="col-5" type="text" value="${ language.name }"/>
               <form:errors path="name" class="col-2 text-danger"/>
           </div>
           <div class="row my-2">
               <form:label path="creator" class="col-2">Creator</form:label>
               <form:input path="creator" class="col-5" type="text" value="${ language.creator }"/>
               <form:errors path="creator" class="col-2 text-danger"/>
           </div>
           <div class="row my-2">
               <form:label path="version" class="col-2">Version</form:label>
               <form:input path="version" class="col-5" type="text" value="${ language.version }"/>
               <form:errors path="version" class="col-2 text-danger"/>
           </div>
           <div class="row my-2 col-1">
               <button class="btn btn-primary" type="submit">Submit</button>
           </div>
       </form:form>
		</div>
	</div>
</body>
</html>