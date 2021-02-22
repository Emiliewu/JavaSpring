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
	<title>Student Roster</title>
</head>
<body>
	<div class="container">
		<div class="container mt-3 mx-auto" style="width:600px;">
			<a href="">Add a Student</a>&nbsp;|&nbsp;
			<a href="">Add Contact</a>&nbsp;|&nbsp;
			<a href="">Add Dorm</a>
		</div>
		<h2><c:out value="${ thisdorm.name }"/>&nbsp;Dormitory</h2>
	</div>
	<div class="container mx-auto mt-4" style="width: 600px;">
		<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
    	<form action="/dorms/${thisdorm.id}/add" method="POST" >
            <div class="row my-2">
    	   <label path="student" class="col-2">Students:</label>
    	   <select path="student" class="col-5" type="text" name="student">
    	   <c:forEach items="${ students }" var="student">
    	   		<option value="${ student.id }"><c:out value="${ student.firstName } ${ student.lastName }"/></option>
    	   </c:forEach>
    	   </select>
    	   </div>
           <div class="row my-2">
           		<div class="container">
         	      <button class="btn btn-primary" type="submit">Add</button>
           		</div>
           </div>
       </form>
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th scope="col">Name</th>
			      	<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ thisdorm.students }" var="s">
				<tr>
			  	<td scope="row"><c:out value="${ s.firstName } ${ s.lastName }"/></td>
				<td scope="row">
				<a href="/dorms/${ thisdorm.id }/remove?student=${s.id}">remove</a>
				</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>	
			
			
	</div>
</body>
</html>