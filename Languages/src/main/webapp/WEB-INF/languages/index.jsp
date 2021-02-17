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
	  <table class="table table-striped table-bordered .my-2">
		  <thead>
		    <tr>
		    <th scope="col"></th>
		      <th scope="col">Name</th>
		      <th scope="col">Creator</th>
		      <th scope="col">Version</th>
		      <th scope="col">Action</th>
		    </tr>
		  </thead>
		  <tbody>
		  <c:forEach items="${ languages }" var="language">
			  <tr>
			  	<th scope="row"></th>
			  	<td><a href="/languages/${language.id }"><c:out value="${ language.name }"/></a></td>
			  	<td><c:out value="${ language.creator }"/></td>
			  	<td><c:out value="${ language.version }"/></td>
			  	<td>
			  		<a href="/languages/${language.id}/edit">Edit</a>&nbsp; |&nbsp; 
					<a href="/languages/delete/${language.id }">Delete</a>
			  	</td>
			  </tr>
		  </c:forEach>
		  </tbody>
	  </table>
		<div id="error" class=".mb-2">
			<c:out value="${error}"/>
		</div>
		<div class="w-60 .align-middle">
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
    	<form:form action="/languages/add" method="POST" modelAttribute="lang">
           <div class="row my-2">
               <form:label path="name" class="col-2">Name</form:label>
               <form:input path="name" class="col-5" type="text"/>
               <form:errors path="name" class="col-2 text-danger"/>
           </div>
           <div class="row my-2">
               <form:label path="creator" class="col-2">Creator</form:label>
               <form:input path="creator" class="col-5" type="text" name="creator"/>
               <form:errors path="creator" class="col-2 text-danger"/>
           </div>
           <div class="row my-2">
               <form:label path="version" class="col-2">Version</form:label>
               <form:input path="version" class="col-5" type="text" name="version"/>
               <form:errors path="version" class="col-2 text-danger"/>
           </div>
           <div class="row my-2 col-1 ">
               <button class="btn btn-primary align-right" type="submit">Submit</button>
           </div>
       </form:form>
		</div>
	  </div>
	</div>
</body>
</html>