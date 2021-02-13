<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
	<title>Counter</title>
</head>
<body>
	<div id="wrapper">
	<h3><c:out value="${ message }"/> <a href="/">Localhost:8080/</a>&nbsp <c:out value="${ counter }"/>&nbsp times</h3>
	<div id="links">
	<a href="/">Test Another Visited?</a>
	<a id="button" href="/reset">Reset</a>
	</div>
	</div>
</body>
</html>