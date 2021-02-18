<!doctype html>
<html lang="en">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<meta charset="UTF-8" />
	<title>Driver's License</title>
</head>
<body>
	<div class="container mt-2">

		<div class="row my-2">
			<div class="container text-center">
			<div>
				<a href="/licenses/new">Add a License</a>
			</div>
			<h2>New Person</h2>
			</div>
			<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
    	<form:form action="/persons/new" method="POST" modelAttribute="person">
           <div class="row my-2">
               <form:label path="firstName" class="col-5">First Name</form:label>
               <form:input path="firstName" class="col-5" type="text" name="firstName"/>
               <form:errors path="firstName" class="col-2 text-danger"/>
           </div>
           <div class="row my-2">
               <form:label path="lastName" class="col-5">Last Name</form:label>
               <form:input path="lastName" class="col-5" type="text" name="lastName"/>
               <form:errors path="lastName" class="col-2 text-danger"/>
           </div>
           
           <div class="row my-2">
           		<div class="container text-center">
               	<button class="btn btn-primary" type="submit">Create</button>
           		</div>
           </div>
       </form:form>
		</div>
	</div>
</body>
</html>