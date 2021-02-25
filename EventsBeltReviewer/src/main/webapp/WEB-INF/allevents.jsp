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
			<span class="mt-3"> <h2>Welcome, <c:out value="${username}"/></h2></span><a href="/logout">Logout</a>
		</div>
		<div class="container my-2 p-3">
			<p>Here are some of the events in your state</p>
			<table class="table table-bordered table-striped">
				 <thead>
				    <tr>
				      <th scope="col">Name</th>
				      <th scope="col">Date</th>
				      <th scope="col">Location</th>
				      <th scope="col">Host</th>
				      <th scope="col">Action/Status</th>
				    </tr>
				  </thead>
				  <tbody>
				  <c:forEach items="${instateEvents}" var="event">
				    <tr>
				      <td scope="row"><a href="/events/${ event.id }"><c:out value="${event.name}"/></a></td>
				      <td scope="row"><fmt:formatDate type="date" value="${event.eventDate}" /></td>
				      <td scope="row"><c:out value="${event.location}"/></td>
				      <td scope="row"><c:out value="${event.host.firstName}"/></td>
				      <td scope="row">
				      	<c:choose>
						    <c:when test="${event.getHost().getId()==user.getId()}">
						        <a class="mr-2" href="/events/${event.id}/edit">edit</a> |
						        <a class="ml-2" href="/events/${event.id}/delete">delete</a> 
						    </c:when>
						    <c:otherwise>
						      	<c:if test="${ user.getShows().contains(event) == false}">
								    <a href="/events/${event.id}/join">Join</a> 
								</c:if>	
						    	<c:forEach items="${event.getAttendees()}" var="attendee">
						    		<c:choose>
						    			<c:when test="${attendee.getId()==user.getId()}">
						    				<span class="mr-2">Joining</span> |
						        			<a class="ml-2" href="/events/${event.id}/cancel">Cancel</a>
						    			</c:when>
						    		</c:choose>
						    	</c:forEach>
						    </c:otherwise>     
						</c:choose>
				      </td>
				    </tr>
				  </c:forEach>
			</table>
		</div>
		<div class="container my-2 p-3">
		<p>Here are some of the events in other states</p>
			<table class="table table-bordered table-striped">
				 <thead>
				    <tr>
				      <th scope="col">Name</th>
				      <th scope="col">Date</th>
				      <th scope="col">Location</th>
				      <th scope="col">State</th>
				      <th scope="col">Host</th>
				      <th scope="col">Action/Status</th>
				    </tr>
				  </thead>
				  <tbody>
				  <c:forEach items="${outstateEvents}" var="event">
				    <tr>
				      <td scope="row"><a href="/events/${ event.id }"><c:out value="${event.name}"/></a></td>
				      <td scope="row"><fmt:formatDate type="date" value="${event.eventDate}" /></td>
				      <td scope="row"><c:out value="${event.location}"/></td>
				      <td scope="row"><c:out value="${event.state}"/></td>
				      <td scope="row"><c:out value="${event.host.firstName}"/></td>
				      <td scope="row">
				      	<c:choose>
						    <c:when test="${event.getHost().getId()==user.getId()}">
						        <a href="/events/${event.id}/edit">edit</a> |
						        <a href="/events/${event.id}/delete">delete</a> 
						    </c:when>
						    <c:otherwise>
						        <c:if test="${ user.getShows().contains(event) == false}">
								    <a href="/events/${event.id}/join">Join</a> 
								</c:if>	

						    	<c:forEach items="${event.getAttendees()}" var="attendee">
						    		<c:choose>
						    			<c:when test="${attendee.getId()==user.getId()}">
						    				<span>Joining</span> |
						        			<a href="/events/${event.id}/cancel">Cancel</a>
						    			</c:when>
						    		</c:choose>
						    	</c:forEach>
						    </c:otherwise>     
						</c:choose>
									         
				      </td>
				    </tr>
				  </c:forEach>
			</table>
		</div>
		<div class="container my-2 p-3">
		<h5>Add a New Event</h5>
		    <p class="text-danger"><form:errors path="event.*"/></p>
		    <p class="text-danger"><c:out value="${error}" /></p>
		    
		    <form:form method="POST" action="/events/create" modelAttribute="event" >
		        <div class="row my-2">
		            <form:label path="name" class="col-4">Name:</form:label>
		            <form:input type="text" path="name" class="col-5" name="name"/>		            
		        </div>
		       	<div class="row my-2">
		            <form:label path="eventDate" class="col-4">Date:</form:label>
		            <form:input type="date" path="eventDate" class="col-5" name="eventDate" min="${todaydate}"/>
		        </div>
		       
		        <div class="row my-2 nowrap">
		            <form:label path="location" class="col-4">Location:</form:label>
		            <form:input type="text" path="location" class="col-5" name="location"/>
		            <form:errors path="location" class="col-2 text-danger"/>
		            <form:select path="state" class="col-3" type="text" name="state">
		            	<form:option value="CA">CA</form:option>
		            	<form:option value="BC">BC</form:option>
		            	<form:option value="WA">WA</form:option>
		            </form:select>
		        </div>
		         <div class="row my-2">
		         	<div class="container">
		       	      <button class="btn btn-primary" type="submit">Submit</button>
		         	</div>
		         </div>
		    </form:form>
		</div>
		
	</div>
</body>
</html>