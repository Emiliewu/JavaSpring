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
	<div class="container">
		<div class="container">
			<div class="container text-center">
				<div class="container">
					<a href="/licenses/new">Add a License</a>&nbsp;|&nbsp;<a href="/persons/new">Add a Person</a>
				</div>
				<div class="container">
					<h2><c:out value="${person.firstName } ${person.lastName }"/></h2>
					<table class="table">
						<thead>
						</thead>
						<tbody>

							<tr>
							<td>License Number:</td>
							<td><c:out value="${person.license.number }"/></td>
							</tr>
							<tr>
							<td>State:</td>
							<td><c:out value="${person.license.state }"/></td>
							</tr>
							<tr>
							<td>Expiration Date:</td>
							<td><c:out value="${person.license.expirationDate }"/></td>
							</tr>
						
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>