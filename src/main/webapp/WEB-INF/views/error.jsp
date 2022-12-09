<html xmlns:th="https://www.thymeleaf.org">
<head>
	<!-- Favicon -->
	<link href="resources/assets/img/favicon.ico" rel="shortcut icon"/>

	<!-- Stylesheets -->
	<%@include file="dynamic/css.jspf" %>
</head>

	<div th:if="${message}">
		<h2 th:text="${message}"/>
	</div>

</body>
</html>
