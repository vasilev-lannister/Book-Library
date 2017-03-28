<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.csc.booklibrary.internationalization.text" />
<table>
	<tr>
		<th><fmt:message key="requests.label.id" /></th>
		<th><fmt:message key="requests.label.madeBy" /></th>
		<th><fmt:message key="requests.label.forBook" /></th>
		<th><fmt:message key="requests.label.type" /></th>
		<th><fmt:message key="requests.label.dateMade" /></th>
		<th><fmt:message key="requests.label.admin" /></th>
		<th><fmt:message key="requests.label.comment" /></th>
		<th><fmt:message key="requests.label.rejectReason" /></th>
		<th><fmt:message key="requests.label.dateAnswered" /></th>
	</tr>
	<c:forEach items="${viewModel.bookRequests}" var="bookRequest">
		<tr>
			<td>
				<a href="request?id=${bookRequest.requestId}" class="linkButton">
					 <c:out value="${bookRequest.requestId}" />
				</a>
			</td>
			<td><c:out value="${bookRequest.byUser}" /></td>
			<td><c:out value="${bookRequest.forBook}" /></td>
			<td><c:out value="${bookRequest.type}" /></td>
			<td><c:out value="${bookRequest.dateMade}" /></td>
			<td><c:out value="${bookRequest.admin}" /></td>
			<td><c:out value="${bookRequest.comment}" /></td>
			<td><c:out value="${bookRequest.rejectReason}" /></td>
			<td><c:out value="${bookRequest.dateAnswered}" /></td>
		</tr>
	</c:forEach>
</table>
<div>
	<c:if test="${viewModel.isPending}">
		<a class="button"
			href="<c:url value="borrowBook?requestId=${requestId}"/>">Add
			Borrow</a>
	</c:if>
</div>