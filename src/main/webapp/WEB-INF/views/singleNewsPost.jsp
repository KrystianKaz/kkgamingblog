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

    <!-- Favicon -->
    <link href="/resources/assets/img/favicon.ico" rel="shortcut icon"/>

    <!-- Google font -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,300i,400,400i,500,500i,700,700i&display=swap"
          rel="stylesheet">

    <!-- Stylesheets -->
    <%@include file="dynamic/css.jspf" %>


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
<%@include file="dynamic/navigationMain.jspf" %>
<!-- Header section end -->

<!-- Page top section -->
<%--<section class="page-top-section set-bg" data-setbg="../resources/assets/img/header-bg/1.jpg">--%>
<%--    <div class="container">--%>
<%--        <h2>Wiadomość</h2>--%>
<%--    </div>--%>
<%--</section>--%>
<!-- Page top section end -->

<!-- Blog section -->
<section class="blog-section spad">
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <div class="blog-post single-post">
                    <img src="/resources/assets/img/post/${news.file}">
                    <div class="post-date">${news.newsTime}, ${news.newsDate}</div>
                    <h3>${news.newsTitle}</h3>
                    <div class="post-metas">
                        <div class="post-meta">od ${news.newsAuthor}</div>
                        <div class="post-meta">w <a href="/news">Wiadomości</a></div>
                        <div class="post-meta">${size} <i class="fa fa-comment"></i></div>
                    </div>
                    <p>${news.description}</p>
                    <div class="comments">
                        <h5>Komentarzy (${data.size()})</h5>
                        <ul class="comments-list">
                            <c:forEach items="${com}" var="comments">
                                <li>
                                    <img src="/resources/assets/img/post/${service.findUserFileByUsername(comments.username)}"
                                         alt="${comments.username}">
                                    <div class="comment-text">
                                        <h6>${comments.username}</h6>
                                        <div class="comment-date">${comments.commentTime}, ${comments.commentDate}</div>
                                        <p>${comments.commentContent}</p>
                                    </div>
                                </li>
                            </c:forEach>

                            <div class="site-pagination">
                                <c:if test="${pageNumber > 0}">
                                    <a href="?page=${pageNumber-1}" class="sp-prev"> < </a>
                                </c:if>
                                <c:if test="${data.size() > 0 }">
                                    <c:forEach begin="0" end="${totalPages-1}" var="pages">
                                        <c:choose>
                                            <c:when test="${pageNumber == pages}">
                                                <a href="?page=${pages}" class="active">0${pages+1}.</a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="?page=${pages}"> 0${pages+1}. </a>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                    <c:if test="${pageNumber < totalPages-1}">
                                        <a href="?page=${pageNumber+1}" class="sp-next"> > </a>
                                    </c:if>
                                </c:if>
                            </div>

                        </ul>
                        <h5>Zostaw komentarz</h5>
                        <c:choose>
                            <c:when test="${user != null}">
                                <form method="post" action='<c:url value="/addCommentToTheNews"/>' class="comment-form">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <textarea placeholder="Treść" name="commentContent"></textarea>
                                            <input class="btn btn-success pull-left" type="submit" value="Wyślij">
                                            <input type="hidden" name="newsId" value="${news.id}">
                                        </div>
                                    </div>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <p>Musisz się <a href="/login">zalogować</a>, aby móc dodawać komentarze!</p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Blog section end -->

<!-- Footer section -->
<%@include file="dynamic/footer.jspf" %>
<!-- Footer section end -->

<!--====== Javascripts & Jquery ======-->
<%@include file="dynamic/javaScript.jspf" %>

</body>
</html>
