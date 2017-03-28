<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.csc.booklibrary.internationalization.text" />
<footer class="footerLine">
	<div class="homepage">
		<span class="copyright"> CSC &copy; 2017 &#9679; <fmt:message key="footer.label.privacyPolicy" />
		</span>
	</div>
</footer>