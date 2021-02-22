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
	<title>Dojo Overflows</title>
</head>
<body>
	<div class="container mx-auto mt-3" style="width:800px;">
		<h2 class="mb-3"><c:out value="${ question.question }"/></h2>
		<div><span>Tags:  &nbsp;</span>
			<c:forEach items="${ question.tags }" var="t">
			<button type="button" class="btn btn-outline-info"><c:out value="${ t.tag }"/></button>
			</c:forEach>
		</div>
		<div class="container d-flex justify-content-around">
			<div class="container w-50 p-3">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<th scope="col">Answers</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${ question.answers }" var="a">
					<tr>
					  	<td scope="row"><c:out value="${ a.answer }"/></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
			<div class="container w-50 p-3">
				
		    	<form action="/questions/${ question.id }/answer" method="POST" >
			    	<div id="error">
					<c:out value="${error}"/>
					</div>
		           <div class="row my-2">
		               <label class="col-2 px-1">Answer</label>
		               <input class="col-5" type="text" name="answer"/>
		           </div>
		           <div class="row my-2">
		           		<div class="container">
		         	      <button class="btn btn-primary" type="submit">Answer It</button>
		           		</div>
		           </div>
		       </form>
			</div>
		</div>
	</div>
</body>
</html>