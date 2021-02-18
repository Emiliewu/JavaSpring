<!doctype html>
<html lang="en">
<head>
	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<meta charset="UTF-8" />
	<title>Dojos Ninjas</title>
</head>
<body>
	<div class="container">
		<div class="container mt-3 mx-auto" style="width: 600px;">
			<div>
				<a href="/dojos/new">Add a Dojo</a>
			</div>
			<h3>New Ninja</h3>
		</div>
		<div class="container mx-auto mt-4" style="width: 600px;">
			<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
		    	<form:form action="/ninjas/create" method="POST" modelAttribute="ninja">
		    	   <div class="row my-2">
		    	   <form:label path="dojo" class="col-2">Dojos:</form:label>
		    	   <form:select path="dojo" class="col-5" type="text">
		    	   <c:forEach items="${ dojos }" var="dojo">
		    	   		<form:option value="${ dojo.id }"><c:out value="${ dojo.name }"/></form:option>
		    	   </c:forEach>
		    	   </form:select>
		    	   </div>
		           <div class="row my-2">
		               <form:label path="firstName" class="col-2">First Name:</form:label>
		               <form:input path="firstName" class="col-5" type="text"/>
		               <form:errors path="firstName" class="col-2 text-danger"/>
		           </div>
		           <div class="row my-2">
		               <form:label path="lastName" class="col-2">Last Name:</form:label>
		               <form:input path="lastName" class="col-5" type="text" name="lastName"/>
		               <form:errors path="lastName" class="col-2 text-danger"/>
		           </div>
		           <div class="row my-2">
		               <form:label path="age" class="col-2">Age:</form:label>
		               <form:input path="age" class="col-5" type="number" name="age" min="18"/>
		               <form:errors path="age" class="col-2 text-danger"/>
		           </div>
		           <div class="row my-2">
		           		<div class="container">
		         	      <button class="btn btn-primary" type="submit">Submit</button>
		           		</div>
		           </div>
		       </form:form>
			</div>
	</div>
</body>
</html>