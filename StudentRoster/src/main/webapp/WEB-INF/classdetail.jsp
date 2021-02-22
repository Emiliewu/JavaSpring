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
		<div class="container mt-3 mx-auto" style="width: 600px;">
			<div>
				<a href="/classes/new">Add class</a>&nbsp;|&nbsp;
				<a href="/students/new">Add student</a>
			</div>
			<h3><c:out value="${ theclass.name }"/></h3>
		</div>
		<div class="container mx-auto mt-4" style="width: 600px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<th scope="col">Name</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ students }" var="student">
					<tr>
					  	<td scope="row"><c:out value="${ student.firstName } ${ student.lastName }"/></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>			
		</div>
	</div>
</body>
</html>