<!doctype html>
<html lang="en">
<head>
	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<meta charset="UTF-8" />
	<title>Student Roster</title>
</head>
<body>
	<div class="container">
		<div class="row my-2 text-center">
			<div class="class="container mt-2">
				<a href="/students/new">Add Student</a> &nbsp;| &nbsp;		
				<a href="/students">student dashboard</a>
			</div>
			<div class="container mt-2">
				<h3>New Contact</h3>
			</div>
			<div class="container mt-2">
			<div class="container">
			<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
		    	<form:form action="/contacts/create" method="POST" modelAttribute="contact" class="d-flex flex-column align-items-center">
		   			<div class="row my-2">
		   				<form:label path="student" class="col-5">Student</form:label>
		   				<form:select path="student" class="col-5" type="text" name="student">
		   					<c:forEach items="${ students }" var="student">
		   						<form:option value="${ student.id }"><c:out value="${ student.firstName } ${ student.lastName }" /></form:option>
		   					</c:forEach>
		   				</form:select>
		   				<form:errors path="student" class="col-5 text-danger"/>
		   			</div>
		   			<div class="row my-2">
		               <form:label path="address" class="col-5">Address</form:label>
		               <form:input path="address" class="col-5" type="text"/>
		               <form:errors path="address" class="col-5 text-danger"/>
		           </div>
		           <div class="row my-2">
		               <form:label path="city" class="col-5">City</form:label>
		               <form:input path="city" class="col-5" type="text" name="city"/>
		               <form:errors path="city" class="col-5 text-danger"/>
		           </div>
		           <div class="row my-2">
		               <form:label path="state" class="col-5">State</form:label>
		               <form:input path="state" class="col-5" type="text" name="state" />
		               <form:errors path="state" class="col-5 text-danger"/>
		           </div>
		           <div class="row my-2">
		           		<div class="container">
		         	      <button class="btn btn-primary" type="submit">Submit</button>
		           		</div>
		           </div>
		       </form:form>
			</div>
			</div>
		</div>
	</div>
</body>
</html>