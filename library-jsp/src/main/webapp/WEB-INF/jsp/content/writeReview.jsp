<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.csc.booklibrary.internationalization.text" />

<form action="writeReview?id=<c:out value="${param.id}"/>" method="post">
	<div class="entry">
		<div
			<c:if test="${not empty viewModel.error}">class="wrongInput"</c:if>>
			<fmt:message key="writeReview.review" />*:
		</div>
	</div>
	<textarea name="comment" class="reviewTextArea"
		placeholder="${viewModel.placeholder}"></textarea>
	<button type="submit" class="button">
		<fmt:message key="addUser.button.submit" />
	</button>
</form>
<form action="reviews" method="get">
	<input type="hidden" name="id" value="${param.id}">
	<button type="submit" class="button">
		<fmt:message key="writeReview.cancel" />
	</button>
</form>
<c:if test="${not empty viewModel.error}">
	<p>
		<fmt:message key="${viewModel.error}" />
	</p>
</c:if>