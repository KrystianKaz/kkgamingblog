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
                        <th>Nazwa</th>
                        <th>Avatar</th>
                    </tr>
                    <tr>
                        <td>${editUser.username}</td>
                        <td>
                            <div class="game-list"><img alt="Avatar" src="/resources/assets/img/post/${editUser.file}">
                            </div>
                        </td>
                        <td>
                            <form method="post" enctype="multipart/form-data"
                                  action='<c:url value="/uploadUserImage/${editUser.id}"/>'>
                                <table>
                                    <tr>
                                        <td>File to upload:</td>
                                        <td><input type="file" accept="image/png, image/jpeg, image/jpg" name="file"/></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td><input type="submit" value="Upload"/></td>
                                    </tr>
                                </table>
                            </form>
                        </td>
                        <td>
                            <form method="post" action='<c:url value="/userImage/${editUser.id}"/>'>
                                <button type="submit" class="btn btn-warning" name="file">
                                    Domyślny avatar
                                </button>
                            </form>
                        </td>
                    </tr>
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
