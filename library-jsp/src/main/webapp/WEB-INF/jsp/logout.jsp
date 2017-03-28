<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="styles/style.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Cabin" rel="stylesheet">
<title>Log in</title>
</head>
<body>
	<div class="entry">
		You are currently logged in as <c:out value="${viewModel.username}"/>.
	</div>
	<form action="logout" method="GET">
		<input type="submit" value="Log out" class="button">
	</form>
</body>
</html>