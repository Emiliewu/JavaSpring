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
			<a href="/students/new">Add a Student</a>&nbsp;|&nbsp;
			<a href="/contacts/new">Add Contact</a>&nbsp;|&nbsp;
			<a href="/dorms/new">Add Dorm</a>
		</div>
		<h2 class="mx-auto" style="width:580px;"><c:out value="${ student.firstName } ${ student.lastName }"/></h2>
	</div>
	<div class="container mx-auto mt-4" style="width: 600px;">
		<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
    	<form action="/students/${student.id}/add" method="POST" >
            <div class="row my-2">
    	   <label class="col-2" for="class">Class:</label>
    	   <select class="col-5" name="class">
    	   <c:forEach items="${ otherclasses }" var="theclass">
    	   		<option value="${ theclass.id }"><c:out value="${ theclass.name }"/></option>
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
					<th scope="col">Class Name</th>
			      	<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ student.classes }" var="c">
				<tr>
			  	<td scope="row"><c:out value="${ c.name } "/></td>
				<td scope="row">
				<a href="/students/${ student.id }/drop?theclass=${c.id}">Drop</a>
				</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>	
			
			
	</div>
</body>
</html>