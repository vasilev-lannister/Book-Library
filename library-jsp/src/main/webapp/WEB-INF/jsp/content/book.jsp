<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table>
	<tr>
		<th>ID</th>
		<th>Name</th>
		<th>Authors</th>
		<th>Year Published</th>
		<th>ISBN</th>
		<th>Edition</th>
		<th>Publisher</th>
		<th>Category</th>
		<th>Language</th>
		<th>Quantity</th>
		<th>Registered By</th>

	</tr>

	<tr>
		<td><c:out value="${viewModel.id}" /></td>
		<td><c:out value="${viewModel.name}" /></td>
		<td><c:out value="${viewModel.authors}" /></td>
		<td><c:out value="${viewModel.yearPublished}" /></td>
		<td><c:out value="${viewModel.isbn}" /></td>
		<td><c:out value="${viewModel.edition}" /></td>
		<td><c:out value="${viewModel.publisherName}" /></td>
		<td><c:out value="${viewModel.category}" /></td>
		<td><c:out value="${viewModel.language}" /></td>
		<td><c:out value="${viewModel.quantity}" /></td>
		<td><c:out value="${viewModel.registeredByName}" /></td>

	</tr>

</table>

<c:if test="${viewModel.id ne null}">
	<form action="requestBook" method="GET">
		<input type="hidden" name="bookId" value="${viewModel.id}"> <input
			type="submit" value="Request" class="button">
	</form>
</c:if>