<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pl-PL">
<head>
    <title>KKGames - News, Reviews, GAMES!</title>
    <meta charset="UTF-8">
    <meta name="description" content="KKGames - News, Reviews, GAMES!">
    <meta name="keywords" content="gaming, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Favicon -->
    <link href="/resources/assets/img/favicon.ico" rel="shortcut icon"/>

    <!-- Google font -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,300i,400,400i,500,500i,700,700i&display=swap"
          rel="stylesheet">

    <!-- Stylesheets -->
    <%@include file="../dynamic/css.jspf" %>


    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>

<!-- Page Preloder -->
<div id="preloder">
    <div class="loader"></div>
</div>
<!-- Page Preloder end -->

<!-- Header section -->
<%@include file="../dynamic/navigationMain.jspf" %>
<!-- Header section end -->

<!-- Page top section -->
<%--<section class="page-top-section set-bg" data-setbg="../resources/assets/img/header-bg/1.jpg">--%>
<%--    <div class="container">--%>
<%--        <h2>Wiadomość</h2>--%>
<%--    </div>--%>
<%--</section>--%>
<!-- Page top section end -->

<!-- Blog section -->
<section class="blog-section-list spad">
    <div class="container">
        <div class="content">
            <div class="row">
                <table class="table-userlist">
                    <tr>
                        <th>Id</th>
                        <th>Tytuł</th>
                        <th>Autor</th>
                        <th>Data</th>
                        <th>Godzina</th>
                        <th>Obrazek</th>
                        <th>Edycja</th>
                        <th>Usuń</th>

                    </tr>

                    <c:forEach items="${news}" var="news">
                        <tr>
                            <td>${news.id}</td>
                            <td class="news-list">${news.newsTitle}</td>
                            <td>${news.newsAuthor}</td>
                            <td>${news.newsDate}</td>
                            <td>${news.newsTime}</td>
                            <td>
                                <div class="news-list"><img src="/resources/assets/img/post/${news.file}"></div>
                            </td>
                            <td><a href='<c:url value="/editNews/${news.id}"/>'>
                                <button type="submit" class="btn btn-info">Edytuj</button>
                            </a></td>
                            <td>
                                <form class="form" method="post" action='<c:url value="/deleteNews/${news.id}"/>'>
                                    <button class="btn-right btn btn-danger" role="button"
                                            onclick="if (!(confirm('WARNING!! After submitting, all comments to this' +
                                             ' current news post will be permamently DELETED. Are you sure you want to proceed?')))
                                                return false">Usuń
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</section>
<!-- Blog section end -->

<!-- Footer section -->
<%@include file="../dynamic/footer.jspf" %>
<!-- Footer section end -->

<!--====== Javascripts & Jquery ======-->
<%@include file="../dynamic/javaScript.jspf" %>

</body>
</html>
