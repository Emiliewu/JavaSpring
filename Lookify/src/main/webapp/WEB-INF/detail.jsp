<!doctype html>
<html lang="en">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<meta charset="UTF-8" />
	<title>Lookify - detail</title>
</head>
<body>
	<div class="container">
		<div class="row my-2 justify-content-center">
			<div class="col-2">
            	<a href="/dashboard">Dashboard</a>
            </div>
    
		</div>
		<div class="row my-2">
            <table class="table">
                <thead>
                </thead>
                <tbody>
                	<tr>
                	<td>Name: </td>
                    <td><c:out value="${song.title}" /></td>
                    </tr>
                    <tr>
                	<td>Artist: </td>
                    <td><c:out value="${song.artist}" /></td>
                    </tr>
                    <tr>
                    <td>Rating:</td>
                    <td><c:out value="${song.rating}" /></td>
                    </tr>
                    <tr>
                    <td>
                    	<form action="/songs/${song.id}" method="POST">
                    		<input type="hidden" name="_method" value="delete">
                    		<button type="submit" class="btn btn-link">Delete</button>
                    	</form>
                    </td>
                    </tr>
                </tbody>
            </table>
    	</div>
	</div>
</body>
</html>