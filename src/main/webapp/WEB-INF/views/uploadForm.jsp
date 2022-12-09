<html xmlns:th="https://www.thymeleaf.org">
<head>
	<!-- Favicon -->
	<link href="resources/assets/img/favicon.ico" rel="shortcut icon"/>

	<!-- Stylesheets -->
	<%@include file="dynamic/css.jspf" %>
</head>

<body>

	<div th:if="${message}">
		<h2 th:text="${message}"/>
	</div>

	<div>
		<form method="POST" enctype="multipart/form-data" action="/upload">
			<table>
				<tr><td>File to upload:</td><td><input type="file" name="file" /></td></tr>
				<tr><td></td><td><input type="submit" value="Upload" /></td></tr>
			</table>
		</form>
	</div>

	<div>
		<ul>
			<li th:each="file : ${files}">
				<a th:href="${file}" th:text="${file}" />
			</li>
		</ul>
	</div>


<%--	<div class="form-group row">--%>
<%--		<form method="POST" enctype="multipart/form-data" action="/upload" target="my-iframe">--%>
<%--			<table>--%>
<%--				<tr><td>File to upload:</td><td><input type="file" name="file" accept="image/png, image/jpg, image/jpeg"/></td></tr>--%>
<%--				<tr><td></td><td><input type="submit" value="Upload" /></td></tr>--%>
<%--			</table>--%>
<%--		</form>--%>
<%--		<iframe name="my-iframe" style="display: none;"></iframe>--%>
<%--	</div>--%>

</body>
</html>
