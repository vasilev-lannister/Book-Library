<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form action="borrowBook" method="POST">

	<div class="entry">
		<div
			<c:if test="${viewModel.wrongDate == true}">class="wrongInput"</c:if>>
			Final Date in format *: (2017-12-03)</div>
	</div>
	<div class="finalDateFieldEntry">
		<input type="text" name="finalDate" value="${viewModel.finalDate}" />
	</div>
	<input type="hidden" name="requestId" value="${viewModel.requestId}">
	<input type="submit" value="Submit" class="button">
	<c:if test="${not empty viewModel.errorMessage}">
		<div>
			<c:out value="${viewModel.errorMessage}" />
		</div>
	</c:if>
</form>