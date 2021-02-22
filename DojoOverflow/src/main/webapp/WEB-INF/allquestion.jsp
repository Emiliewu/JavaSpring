<!doctype html>
<html lang="en">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<meta charset="UTF-8" />
	<title>Dojo Overflows</title>
</head>
<body>
	<div class="container mx-auto mt-3" style="width:800px;">
	<h2 class="mb-3">Question Dashboard</h2>
	<table class="table table-striped table-bordered">
		<thead>
			<tr>
				<th scope="col">Questions</th>
		      	<th scope="col">Tags</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${ questions }" var="q">
			<tr>
			  	<td scope="row"><c:out value="${ q.question }"/></td>
				<td scope="row">
				<c:forEach items="${ q.tags }" var="t" varStatus="loop">
					<span><c:out value="${ t.tag }"/></span> 
						<c:if test="${!loop.last}"><span>,</span></c:if>
				</c:forEach>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="text-center">
		<a href="/questions/new">New Question</a>
	</div>
	</div>
</body>
</html>