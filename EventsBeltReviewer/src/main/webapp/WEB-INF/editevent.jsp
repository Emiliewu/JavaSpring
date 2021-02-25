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
			<span class="mt-3"> <h2><c:out value="${ thisevent.name }"/></h2></span></span><a href="/events">Go Back</a><a href="/logout">Logout</a>
		</div>
		
		<div class="container my-2 p-3">
		<h5>Edit Event</h5>
		    <p class="text-danger"><form:errors path="event.*"/></p>
		    <p class="text-danger"><c:out value="${error}" /></p>
		    
		    <form:form method="PUT" action="/events/${ thisevent.id }/update" modelAttribute="event" >
		        <div class="row my-2">
		            <form:label path="name" class="col-4">Name:</form:label>
		            <form:input type="text" path="name" class="col-5" name="name" value="${ thisevent.name }"/>
		        </div>
		       	<div class="row my-2">
		            <form:label path="eventDate" class="col-4">Date:</form:label>
		            <form:input type="date" path="eventDate" class="col-5" name="eventDate" min="${todaydate}" value="${ thisdate }"/>
		        </div>
		       
		        <div class="row my-2 nowrap">
		            <form:label path="location" class="col-4">Location:</form:label>
		            <form:input type="text" path="location" class="col-5" name="location" value="${ thisevent.location }"/>
		            <form:errors path="location" class="col-2 text-danger"/>
		            <form:select path="state" class="col-3" type="text" name="state">
						<c:forEach items="${allstates}" var="st" >
		            		<c:choose>
			            		<c:when test="${thisevent.state.equals(st)}">
			            			<option value="${st}" selected><c:out value="${st}"/></option>
			            		</c:when>
			            		<c:otherwise>
			            			<option value="${st}"><c:out value="${st}"/></option>
			            		</c:otherwise>
			            	</c:choose>
			            </c:forEach>
		            </form:select>
		        </div>
		         <div class="row my-2">
		         	<div class="container">
		       	      <button class="btn btn-primary" type="submit">Update</button>
		         	</div>
		         </div>
		    </form:form>
		</div>
		
	</div>
</body>
</html>