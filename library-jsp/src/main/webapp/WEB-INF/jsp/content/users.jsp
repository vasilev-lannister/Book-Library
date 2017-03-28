<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table>
	<tr>
		<th>ID</th>
		<th>User Name</th>
		<th>First Name</th>
		<th>Last Name</th>
		<th>User Role</th>
		<th>Date Registered</th>
		<th>Date Inactive</th>
		<th>email</th>
		<th>phone</th>
	</tr>

	<c:forEach items="${userList}" var="user">
		<tr>
			<td><c:out value="${user.userId}" /></td>
			<td><c:out value="${user.username}" /></td>
			<td><c:out value="${user.firstName}" /></td>
			<td><c:out value="${user.lastName}" /></td>
			<td><c:out value="${user.userRole}" /></td>
			<td><c:out value="${user.dateRegist}" /></td>
			<td><c:out value="${user.dateInactive}" /></td>
			<td><c:out value="${user.email}" /></td>
			<td><c:out value="${user.phone}" /></td>
		</tr>
	</c:forEach>
</table>

<form action="addUser">
	<input type="submit" value="Add New User" class="button">
</form>