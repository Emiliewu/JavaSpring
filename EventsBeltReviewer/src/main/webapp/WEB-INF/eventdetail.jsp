<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
	<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
    <meta charset="UTF-8">
<title>Events Reviewer</title>
</head>
<body>
	<div class="container mx-auto" style="width:800px;">
		<div class="d-flex justify-content-between">
			<span class="mt-3"> <h2><c:out value="${ thisevent.name }"/></h2></span><a href="/events">Go Back</a><a href="/logout">Logout</a>
		</div>
		<div class="container d-flex justfiy-content-around">
		<div class="container my-2 p-3">
			<p>Host:&nbsp;<c:out value="${ thisevent.getHost().firstName} ${ thisevent.getHost().lastName }"/> </p>
			<p>Date: &nbsp;<fmt:formatDate type="date" value="${thisevent.eventDate}" /> </p>
			<p>Location:&nbsp;<c:out value="${ thisevent.location }, ${ thisevent.state }"/> </p>
			<p>People who are attending this event:&nbsp; <c:out value="${ thisevent.getAttendees().size() }"/></p>
			<table class="table table-bordered table-striped">
				 <thead>
				    <tr>
				      <th scope="col">Name</th>
				      <th scope="col">Location</th>
				    </tr>
				  </thead>
				  <tbody>
				  <c:forEach items="${thisevent.getAttendees()}" var="attendee">
				    <tr>
				      <td scope="row"><c:out value="${attendee.firstName} ${attendee.lastName}"/></td>
				      <td scope="row"><c:out value="${attendee.location}"/></td>
				    </tr>
				  </c:forEach>
			</table>
		</div>
		
		<div class="container my-2 p-3">
		    <p>Messages:</p>
		    <div id="action">
		    	<c:forEach items="${messages}" var="message">
		          <p class="my-4"><c:out value="${message.getUser().firstName} "/>says: <c:out value=" ${message.comment}"/></p>
		          <p>*************************************</p>
		      	</c:forEach>
		    </div>
		<h5>Add a Comment</h5>
		    <p class="text-danger"><form:errors path="comment.*"/></p>
		    <p class="text-danger"><c:out value="${error}" /></p>
		    
		    <form:form method="POST" action="/events/${ thisevent.id }/comment" modelAttribute="newcomment" >
		        <div class="row my-2">
		            <form:label path="comment" class="col-4">Comment:</form:label>
		            <form:input type="text" path="comment" class="col-5" name="comment"/>
		        </div>
		       
		         <div class="row my-2">
		         	<div class="container">
		       	      <button class="btn btn-primary" type="submit">Submit</button>
		         	</div>
		         </div>
		    </form:form>
		</div>
		</div>
		
	</div>
</body>
</html>