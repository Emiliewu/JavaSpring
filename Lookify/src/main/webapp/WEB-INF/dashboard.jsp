<!doctype html>
<html lang="en">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<meta charset="UTF-8" />
	<title>Lookify</title>
</head>
<body>
	<div class="container">
		<div class="row my-2 justify-content-center">
			<div class="col-2">
            	<a href="/songs/new">Add New</a>
            </div>
            <div class="col-2">
                <a href="/search/topTen">Top Songs</a>
            </div>
            <div class="col-4">
                <form action="/search" method="post">
                 <input type="text" name="searchString">
                 <button type="submit" class="btn btn-primary">New Search</button>
                </form>
            </div>
		</div>
		<div class="row my-2">
            <table class="table table-bordered table-striped">
                <thead>
	                <th>Name</th>
	                <th>Rating</th>
	                <th>Actions</th>
                </thead>
                <tbody>
                	<c:forEach items="${songs}" var="song">
                	<tr>
                    <td><a href="/songs/${song.id}"><c:out value="${song.title}"/></a></td>
                    <td><c:out value="${song.rating}"/></td>
                    <td>
                    	<form action="/songs/${song.id}" method="POST">
                    		<input type="hidden" name="_method" value="delete">
                    		<button type="submit" class="btn btn-link">Delete</button>
                    	</form>
                    </td>
                     </tr>
                	</c:forEach>
                </tbody>
            </table>
    	</div>
	</div>
	
</body>
</html>