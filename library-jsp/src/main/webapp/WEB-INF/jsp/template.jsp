<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="https://fonts.googleapis.com/css?family=Abril+Fatface|Indie+Flower"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Acme|Architects+Daughter|Exo|Raleway"
	rel="stylesheet">
<link href="styles/style.css" rel="stylesheet" type="text/css">
<link href="styles/headerAndFooter.css" rel="stylesheet" type="text/css">
<title>${param.title}</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<jsp:include page="${param.content}.jsp" />
	<jsp:include page="footer.jsp" />
</body>
</html>