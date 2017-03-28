<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.csc.booklibrary.internationalization.text" />
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="styles/style.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Cabin" rel="stylesheet">
<title> <fmt:message key="login.title" /> </title>
</head>
<header>
	<div class="languageLine">
		<form>
			<select name="language" onchange="submit()">
				<option value="en" ${language == 'en' ? 'selected' : ''}><fmt:message key="language.label.english" /></option>
				<option value="fr" ${language == 'fr' ? 'selected' : ''}><fmt:message key="language.label.french" /></option>
				<option value="ja" ${language == 'ja' ? 'selected' : ''}><fmt:message key="language.label.japanese" /></option>
				<option value="bg" ${language == 'bg' ? 'selected' : ''}><fmt:message key="language.label.bulgarian" /></option>
			</select>
		</form>
	</div>
</header>
<body>
	<form action="login" method="POST">
		<div class="entry">
			<fmt:message key="addUser.label.username" />: 
			<input type="text" name="username" value="${viewModel.username}">
		</div>
		<div class="entry">
			<fmt:message key="addUser.label.password" />: 
			<input type="password" name="password">
		</div>
		<input type="submit" value="<fmt:message key="login.title" />" class="button">
	</form>
	<c:if test="${viewModel.errorMessage ne null}">
		<p>
			<fmt:message key="${viewModel.errorMessage}" />
		</p>
	</c:if>
</body>
</html>