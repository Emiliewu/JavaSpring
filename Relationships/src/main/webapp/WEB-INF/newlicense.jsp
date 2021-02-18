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
				<a href="/persons/new">Add a person</a>
			</div>
			<h2>New lisense</h2>
			</div>
			<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
    	<form:form action="/licenses/new" method="POST" modelAttribute="license">
           <div class="row my-2">
               <form:label path="person" class="col-2">Person</form:label>
               <form:select path="person" class="col-3" type="text" name="person">
               <c:forEach items="${persons}" var="person" >
               <form:option value="${person.id}"><c:out value="${person.firstName }  ${person.lastName }"/></form:option>
               </c:forEach>
               </form:select>
           </div>
           <div class="row my-2">
               <form:label path="state" class="col-2">State</form:label>
               <form:input path="state" class="col-5" type="text" name="state"/>
               <form:errors path="state" class="col-2 text-danger"/>
           </div>
           
           <div class="row my-2">
               <form:label path="expirationDate" class="col-2">Expiration Date</form:label>
               <form:input path="expirationDate" class="col-2" type="date" name="expirationDate" />
               <form:errors path="expirationDate" class="col-2 text-danger"/>
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