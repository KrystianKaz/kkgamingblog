<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pl-PL">
<head>
    <title>KKGames - News, Reviews, GAMES!</title>
    <meta charset="UTF-8">
    <meta name="description" content="KKGames - News, Reviews, GAMES!">
    <meta name="keywords" content="gaming, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

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
<section class="page-top-section set-bg" data-setbg="../resources/assets/img/header-bg/1.jpg">
    <div class="container">
        <h2>Zarządzanie kontem</h2>
    </div>
</section>
<!-- Page top section end -->

<!-- Blog section -->
<section class="blog-section spad">
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <div class="blog-post single-post">
                    <h3>Rola: ${user.userRoles}</h3>
                    <h3>Użytkownik: <security:authentication property="principal.username"/></h3>
                    <h3>Avatar: </h3>
                        <ul class="comments-list">
                            <a href="/account/${user.id}"><img src="/resources/assets/img/post/${user.file}"></a>
                        </ul><br><br>
                    <c:if test="${user.userRoles == 'ADMIN'}">
                        <h3><a href="/upload">Dodaj plik/obraz</a></h3><br>
                        <h3><a href="/gamesList">Lista recenzji</a></h3>
                        <h3><a href="/addGame">Dodaj recenzję</a></h3><br>
                        <h3><a href="/newsList">Lista news'ów</a></h3>
                        <h3><a href="/addNews">Dodaj news'a</a></h3><br>
                        <h3><a href="/userList">Lista użytkowników</a></h3>
                    </c:if>
                </div>
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
