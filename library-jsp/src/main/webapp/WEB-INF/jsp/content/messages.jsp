<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.csc.booklibrary.internationalization.text" />
	<table>
		<tr>
			<th><fmt:message key="messages.label.messageID"/></th>
			
			<fmt:message key="${viewModel.user}" var="user" />
			<th><c:out value="${user}"/></th>
			<th><fmt:message key="messages.label.text"/></th>
			<th><fmt:message key="messages.label.dateSent"/></th>
			<th><fmt:message key="messages.label.dateRead"/></th>
		</tr>
		<tr>
			<c:forEach items="${viewModel.messages}" var="message">
				<tr>
					<td><c:out value="${message.id}" /></td>
					
					<td>
					<c:choose>
    				<c:when test="${viewModel.isInbox}"> <c:out value="${message.sender}" />
    				</c:when>    
    				<c:otherwise><c:out value="${message.receiver}" />
    				</c:otherwise>
					</c:choose>
					</td>
					
					<td><c:out value="${message.text}" /></td>
					<td><c:out value="${message.dateSent}" /></td>
					<td><c:out value="${message.dateRead}" /></td>
				</tr>
			</c:forEach>
		</tr>
	</table>
	<form action="sendMessage">
		<fmt:message key="messages.button.sendMessage" var="buttonValue" />
		<input type="submit" value= "${buttonValue}" class="button">
	</form>