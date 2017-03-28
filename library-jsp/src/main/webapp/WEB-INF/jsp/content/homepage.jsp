<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.csc.booklibrary.internationalization.text" />
<div class="firstColumn">
	<span><fmt:message key="homepage.label.lastAddedBooks" /></span>
	<table>
		<tr>
			<th><fmt:message key="homepage.label.name" /></th>
			<th><fmt:message key="homepage.label.isbn" /></th>
		</tr>
		<tr>
			<c:forEach items="${books}" var="book" begin="0" end="4">
				<tr>
					<td><c:out value="${book.name}" /></td>
					<td><c:out value="${book.isbn}" /></td>
				</tr>
			</c:forEach>
		</tr>
	</table>
</div>

<div class="secondColumn">
	<span><fmt:message key="homepage.label.lastAddedUsers" /></span>
	<table>
		<tr>
			<th><fmt:message key="homepage.label.username" /></th>
			<th><fmt:message key="homepage.label.firstName" /></th>
			<th><fmt:message key="homepage.label.lastName" /></th>
		</tr>
		<tr>
			<c:forEach items="${users}" var="user" begin="0" end="4">
				<tr>
					<td><c:out value="${user.username}" /></td>
					<td><c:out value="${user.firstName}" /></td>
					<td><c:out value="${user.lastName}" /></td>
				</tr>
			</c:forEach>
		</tr>
	</table>
</div>

<div class="thirdColumn">
	<span><fmt:message key="homepage.label.lastAddedRequests" /></span>
	<table>
		<tr>
			<th><fmt:message key="homepage.label.madeBy" /></th>
			<th><fmt:message key="homepage.label.forBook" /></th>
			<th><fmt:message key="homepage.label.type" /></th>
		</tr>
		<tr>
			<c:forEach items="${requests}" var="req" begin="0" end="4">
				<tr>
					<td><c:out value="${req.madeBy}" /></td>
					<td><c:out value="${req.forBook}" /></td>
					<td><c:out value="${req.type}" /></td>
				</tr>
			</c:forEach>
		</tr>
	</table>
</div>