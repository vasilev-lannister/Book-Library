<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table>
	<tr>
		<th>Review ID</th>
		<th>Author</th>
		<th>Book</th>
		<th>Parent Review</th>
		<th>Review Date</th>
		<th>Review Content</th>
	</tr>
	<tr>
		<c:forEach items="${reviews}" var="review">
			<tr>
				<td><c:out value="${review.reviewId}" /></td>
				<td><c:out value="${review.authorName}" /></td>
				<td><c:out value="${review.book}" /></td>
				<td><c:out value="${review.parentReview}" /></td>
				<td><c:out value="${review.reviewDate}" /></td>
				<td><c:out value="${review.reviewContent}" /></td>
			</tr>
		</c:forEach>
	</tr>
</table>
<a href="writeReview?id=<c:out value="${param.id}"/>" class="button">Write
	Review</a>