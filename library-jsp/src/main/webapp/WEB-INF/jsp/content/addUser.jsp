<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.csc.booklibrary.internationalization.text" />
<form action="addUser" method="POST">
	<div class="entry">
		<div
			<c:if test="${viewModel.errorField == 'username'}">class="wrongInput"</c:if>>
			<fmt:message key="addUser.label.username" />*
			<input type="text" name="username" value="${viewModel.username}">
		</div>
	</div>

	<div class="entry">
		<div
			<c:if test="${viewModel.errorField == 'password'}">class="wrongInput"</c:if>>
			<fmt:message key="addUser.label.password" />*
			<input type="password" name="password">
		</div>
	</div>

	<div class="entry">
		<div
			<c:if test="${viewModel.errorField == 'passwordConfirm'}">class="wrongInput"</c:if>>
			<fmt:message key="addUser.label.passwordConfirm" />*
			<input type="password" name="passwordConfirm">
		</div>
	</div>

	<div class="entry">
		
		<fmt:message key="addUser.label.userRole" />*
		<select name="userRole">
			<c:forEach items="${requestScope.viewModel.userRoleList}"
						var="role">
						<option value="${role.id}"
							<c:if test="${role.isSelected == 'true'}">
							selected</c:if>><fmt:message key="addUser.label.userRole.${role.name}" /> </option>
					</c:forEach>
		</select>
		
	</div>

	<div class="entry">
		<div
			<c:if test="${viewModel.errorField == 'firstName'}">class="wrongInput"</c:if>>
			<fmt:message key="addUser.label.firstName" />*
			<input type="text" name="firstName" value="${viewModel.firstName}">
		</div>
	</div>

	<div class="entry">
		<div
			<c:if test="${viewModel.errorField == 'lastName'}">class="wrongInput"</c:if>>
			<fmt:message key="addUser.label.lastName" />*
			<input type="text" name="lastName" value="${viewModel.lastName}">
		</div>
	</div>

	<div class="entry">
		<div
			<c:if test="${viewModel.errorField == 'email'}">class="wrongInput"</c:if>>
			<fmt:message key="addUser.label.email" />*
			<input type="text" name="email" value="${viewModel.email}">
		</div>
	</div>

	<div class="entry">
		<div
			<c:if test="${viewModel.errorField == 'phoneNumber'}">class="wrongInput"</c:if>>
			<fmt:message key="addUser.label.phoneNumber" />
			<input type="text" name="phoneNumber"
				value="${viewModel.phoneNumber}">
		</div>
	</div>

	<fmt:message key="addUser.button.submit" var="buttonValue" />
	<input type="submit" value="${buttonValue}" class="button">
</form>
<c:if test="${viewModel.errorMessage ne null}">
	<p>
		<fmt:message key="${viewModel.errorMessage}" />
	</p>
</c:if>