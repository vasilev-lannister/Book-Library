<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>
	<c:out value="Request for ${requestScope.viewModel.bookName}" />
	<c:out value="by ${requestScope.viewModel.authors}" />
</h1>
<form action="requestBook" method="POST">
	<input type="hidden" name="bookId" value="${param.bookId}">
	<div class="entry">
		Type of Request <select name="requestType">
			<c:forEach items="${requestScope.viewModel.typeList}" var="type">
				<option value="${type.id}"
					<c:if test="${type.isActive == 'false'}">
							<c:out value="disabled"/></c:if>>${type.name}</option>
			</c:forEach>
		</select>
	</div>
	<div class="requestComment">
		<textarea rows="4" cols="50" name="comment"
			placeholder="Enter request comment here..."></textarea>
	</div>
	<c:if test="${requestScope.viewModel.errorMessage ne null}">
		<p>
			<c:out value="${requestScope.viewModel.errorMessage}" />
		</p>
	</c:if>
	<div>
		<input type="submit" value="Submit Request" class="button"
			<c:if test="${requestScope.viewModel.disableSubmit == 'true'}"><c:out value="disabled"/></c:if>>
	</div>
</form>