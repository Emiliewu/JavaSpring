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
		<div class="row my-2 justify-content-center">
			<div class="col-2">
            	<a href="/students/new">Add New Student</a>
            </div>
            <div class="col-2">
                <a href="/contact/new">Add Contact</a>
            </div>
		</div>
		<div class="row my-2">
            <table class="table table-bordered table-striped">
                <thead>
	                <th>Name</th>
	                <th>Age</th>
	                <th>Address</th>
	                <th>City</th>
	                <th>State</th>
                </thead>
                <tbody>
                	<c:forEach items="${ students }" var="student">
                	<tr>
                    <td><c:out value="${ student.firstName } ${ student.lastName }"/></td>
                    <td><c:out value="${ student.age }"/></td>
                    <td><c:out value="${ student.contact.address }"/></td>
                    <td><c:out value="${ student.contact.city }"/></td>
                    <td><c:out value="${ student.contact.state }"/></td>
                     </tr>
                	</c:forEach>
                </tbody>
            </table>
    	</div>
	</div>
</body>
</html>