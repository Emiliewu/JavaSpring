<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
	<script src="<c:url value="/resources/js/time.js" />"></script>
	<title>Display Date</title>
</head>
<body>
	<div id="wrapper">
	<h2 id="time"><c:out value="${ time }"/></h2>
	
	</div>
</body>
</html>