<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.csc.booklibrary.internationalization.text" />
<div class="homepage">
	<header>
		<div class="headerLine">
			<a href="homepage" class="logo"></a> <span><a href="logout"><fmt:message key="header.label.logout" /></a></span>
		</div>

		<div class="languageLine">
			<form>
				<select name="language" onchange="submit()">
					<option value="en" ${language == 'en' ? 'selected' : ''}><fmt:message key="language.label.english" /></option>
					<option value="fr" ${language == 'fr' ? 'selected' : ''}><fmt:message key="language.label.french" /></option>
					<option value="ja" ${language == 'ja' ? 'selected' : ''}><fmt:message key="language.label.japanese" /></option>
					<option value="bg" ${language == 'bg' ? 'selected' : ''}><fmt:message key="language.label.bulgarian" /></option>
				</select>
				<input type="hidden" name="id" value="${param.id}">
			</form>
		</div>

		<div class="search">
			<span><fmt:message key="header.label.search" /><input type="text" name="search">
			</span>
		</div>

		<div class="slogan">
			<span><fmt:message key="header.label.yourJourney" /></span>
		</div>

		<ul class="nav">
			<li class="books"><a href="books"><fmt:message key="header.label.books" /></a></li>
			<li class="messages"><a href="#"><fmt:message key="header.label.messages" /></a>
				<ul>
					<li><a href="sendMessage"><fmt:message key="header.label.sendMessage" /></a></li>
					<li><a href="messages"><fmt:message key="header.label.inbox" /></a></li>
					<li><a href="messagesOut"><fmt:message key="header.label.outbox" /></a></li>
				</ul></li>
			<c:if test="${sessionScope.User.getUserRole().getUserRoleName() eq 'admin'}">
				<li class="administration"><a href="#"><fmt:message key="header.label.administration" /></a>
					<ul>
						<li><a href="addBook"><fmt:message key="header.label.addBook" /></a></li>
						<li><a href="addUser"><fmt:message key="header.label.addUser" /></a></li>
						<li><a href="users"><fmt:message key="header.label.allUsers" /></a></li>
						<li><a href="requests"><fmt:message key="header.label.allRequests" /></a></li>
						<li><a href="pendingRequests"><fmt:message key="header.label.pendingRequests" /></a></li>
					</ul></li>
			</c:if>
			<li class="yourAccount"><a href="#"><fmt:message key="header.label.yourAccount" /></a>
				<ul>
					<li><a href="#"><fmt:message key="header.label.settings" /></a></li>
					<li><a href="#"><fmt:message key="header.label.yourRequests" /></a></li>
					<li><a href="#"><fmt:message key="header.label.yourReviews" /></a></li>
					<li><a href="logout"><fmt:message key="header.label.logout" /></a></li>
				</ul></li>
		</ul>

	</header>
</div>