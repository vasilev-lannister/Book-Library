<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.csc.booklibrary.internationalization.text" />
<div class="messageForm">
	<h2><fmt:message key="messages.label.newMessage"/></h2>

	<form action="sendMessage" method="post">

		<div class="toUser">
			<label for="receiver"><fmt:message key="messages.label.to"/>: </label> <input type="text" id="receiver"
				name="receiver" class="userNameField" value="${viewModel.receiver}">
		</div>

		<div class="messageText">
			<textarea id="messageTextarea" name="messageText" class="messageArea"><c:if
			test="${viewModel.message ne null}"><c:out value="${viewModel.message}"/></c:if></textarea>
		</div>
		
		<div class="sendButton">
			<fmt:message key="messages.button.sendMessage" var="buttonValue" />
			<input type="submit" name="action" value="${buttonValue}"
				class="button" />
		</div>

		<c:if test="${viewModel.notification ne null}">
			<fmt:message key="${viewModel.notification}" var="notification" />
			<p class="notification">
				<c:out value="${notification}" />
			<p>
		</c:if>

	</form>

</div>