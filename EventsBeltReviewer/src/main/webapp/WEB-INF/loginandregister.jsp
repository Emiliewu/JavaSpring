<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
	<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
    <meta charset="UTF-8">
    <title>Login and Registration - Events Reviewer</title>
</head>
<body>
	<div class="container mx-auto " style="width:800px;">
    <h3 class="text-center mt-2">Login and Registration</h3>

    <div class="container d-flex justfiy-content-around">
    <div class="container p-3 w-50">
    <h5>Registration</h5>
    <p class="text-danger"><form:errors path="user.*"/></p>
    <p class="text-danger"><c:out value="${error}" /></p>
    
    <form:form method="POST" action="/registration" modelAttribute="user" >
        <div class="row my-2">
            <form:label path="firstName" class="col-4">First Name:</form:label>
            <form:input type="text" path="firstName" class="col-5" name="firstName"/>
        </div>
        <div class="row my-2">
            <form:label path="lastName" class="col-4">Last Name:</form:label>
            <form:input type="text" path="lastName" class="col-5" name="lastName"/>
        </div>
        <div class="row my-2">
	        <form:label path="email" class="col-4">Email:</form:label>
	        <form:input type="email" path="email" class="col-5" name="email"/>
        </div>
        <div class="row my-2 nowrap">
            <form:label path="location" class="col-4">Location:</form:label>
            <form:input type="text" path="location" class="col-5" name="location"/>
            <form:select path="state" class="col-3" type="text" name="state">
            	<form:option value="CA">CA</form:option>
            	<form:option value="BC">BC</form:option>
            	<form:option value="WA">WA</form:option>
            </form:select>
        </div>
        <div class="row my-2">
            <form:label path="password" class="col-4">Password:</form:label>
            <form:password path="password" class="col-5"/>
        </div>
        <div class="row my-2">
            <form:label path="passwordConfirmation" class="col-4">CF Password:</form:label>
            <form:password path="passwordConfirmation" class="col-5"/>
        </div>
         <div class="row my-2">
         	<div class="container">
       	      <button class="btn btn-primary" type="submit" value="Register!">Submit</button>
         	</div>
         </div>
    </form:form>
    </div>
    <div class="container p-3 w-50 ml-3">
    <h5>Login</h5>
    <p class="text-danger"><c:out value="${loginerror}" /></p>
    <form method="post" action="/login">
         <div class="row my-2">
            <label for="email" class="col-3">Email</label>
            <input type="text" id="email" name="email" class="col-5"/>
        </div>
         <div class="row my-2">
            <label for="password" class="col-3">Password</label>
            <input type="password" id="password" name="password" class="col-5"/>
        </div>
        <div class="row my-2">
         	<div class="container">
       	      <button class="btn btn-primary" type="submit" value="Login!">Submit</button>
         	</div>
         </div>

    </form>   
    </div>
	</div>
    </div>
    
</body>
</html>