<!doctype html>
<html lang="en">
<head>	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
	<meta charset="UTF-8" />
	<title>ninja</title>
</head>
<body>
	<div id="ninjas">
    <h1>Ninjas</h1>

    <c:forEach begin="1" end="${totalPages}" var="index">
        <a href="/pages/${index}">${index}</a>
    </c:forEach>
    <table class="table">
        <thead>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
        </tr>
        </thead>
        <tbody>

            <c:forEach var="ninja" items="${ninjas.content}">                 
            <tr>
                <td><c:out value="${ninja.firstName}"></c:out></td>
                <td><c:out value="${ninja.lastName}"></c:out></td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
    <table class="table">
        <thead>
        <tr>
            <th>Dojo Name</th>
            <th>Ninja First Name</th>
            <th>Ninja Last Name</th>
        </tr>
        </thead>
        <tbody>

            <c:forEach var="ninja" items="${ninjadojos.content}" >                 
            <tr>
                <td><c:out value="${ninjadojos[1].name }"></c:out></td>
                <td><c:out value="${ninjadojos[0].firstName}"></c:out></td>
                   <td><c:out value=" ${ninjadojos[0].lastName}"></c:out></td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>