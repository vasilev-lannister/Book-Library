<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../template.jsp">
	<jsp:param name="content" value="content/messages" />
	<jsp:param name="title" value="${viewModel.title}" />
</jsp:include>