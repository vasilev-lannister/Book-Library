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
		<c:forEach items="${books}" var="book">
			<tr>
				<td><c:out value="${book.id}" /></td>
				<td>
					<a href="book?id=<c:out value="${book.id}"/>" class="linkButton">
						<c:out value="${book.name}" />
					</a>
				</td>
				<td><c:out value="${book.authors}" /></td>
				<td><c:out value="${book.yearPublished}" /></td>
				<td><c:out value="${book.isbn}" /></td>
				<td><c:out value="${book.edition}" /></td>
				<td><c:out value="${book.publisherName}" /></td>
				<td><c:out value="${book.category}" /></td>
				<td><c:out value="${book.language}" /></td>
				<td><c:out value="${book.quantity}" /></td>
				<td><c:out value="${book.registeredByName}" /></td>
			</tr>
		</c:forEach>
	</tr>
</table>

<br />
<form action="addBook">
	<input type="submit" value="Add New Book" class="button">
</form>