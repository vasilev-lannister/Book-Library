<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.csc.booklibrary.internationalization.text" />

<form action="addBook" method="POST">
	<div class="entry">
		<div
			<c:if test="${viewModel.errorField == 'name'}">class="wrongInput"</c:if>>
			<fmt:message key="addBook.label.name" />* <input type="text" name="name" value="${viewModel.name}">
		</div>
	</div>
	<div class="entry">
		<div
			<c:if test="${viewModel.errorField == 'yearPublished'}">class="wrongInput"</c:if>>
			<fmt:message key="addBook.label.yearPublished" />* <input type="text" name="yearPublished"
				value="${viewModel.yearPublished}">
		</div>
	</div>
	<div class="entry">
		<div
			<c:if test="${viewModel.errorField == 'isbn'}">class="wrongInput"</c:if>>
			ISBN* <input type="text" name="isbn" value="${viewModel.isbn}">
		</div>
	</div>
	<div class="entry">
		<div
			<c:if test="${viewModel.errorField == 'edition'}">class="wrongInput"</c:if>>
			<fmt:message key="addBook.label.edition"/>* <input type="text" name="edition"
				value="${viewModel.edition}">
		</div>
	</div>
	<div class="entry">
		<fmt:message key="addBook.label.publisher"/><input type="text" name="publisher"
			value="${viewModel.publisher}">
	</div>
	<div class="entry">
		<div
			<c:if test="${viewModel.errorField == 'authors'}">class="wrongInput"</c:if>>
			<fmt:message key="addBook.label.authors"/>* <input type="text" name="authors" 
				value="${viewModel.authors}">
		</div>
	</div>
	<div class="entry">
		<div
			<c:if test="${viewModel.errorField == 'category'}">class="wrongInput"</c:if>>
			<fmt:message key="addBook.label.category"/>* <select name="categoryType">
				<c:forEach items="${requestScope.viewModel.categoryList}"
					var="category">
					<option value="${category.id}"
						<c:if test="${category.isSelected == 'true'}">
							selected</c:if>><fmt:message key="addBook.label.category.${category.name}" /></option>
				</c:forEach>
			</select>
		</div>
	</div>
	<div class="entry">
		<div
			<c:if test="${viewModel.errorField == 'language'}">class="wrongInput"</c:if>>
			<fmt:message key="addBook.label.language"/>* <select name="languageType">
				<c:forEach items="${requestScope.viewModel.languageList}"
					var="language">
					<option value="${language.id}"
						<c:if test="${language.isSelected == 'true'}">
							selected</c:if>><fmt:message key="addBook.label.language.${language.name}" /></option>
				</c:forEach>
			</select>
		</div>
	</div>
	<div class="entry">
		<div
			<c:if test="${viewModel.errorField == 'quantity'}">class="wrongInput"</c:if>>
			<fmt:message key="addBook.label.quantity"/>* <input type="text" name="quantity"
				value="${viewModel.quantity}">
		</div>
	</div>
	<input type="submit" value="<fmt:message key="addBook.button.submit"/>" class="button"> <input
		type="reset" value="<fmt:message key="addBook.button.reset"/>" class="button">
</form>

<c:if test="${viewModel.errorMessage ne null}">
	<p>
		<fmt:message key="${viewModel.errorMessage}" />
	</p>
</c:if>