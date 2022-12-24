<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pl-PL">
<head>
    <title>TheQuest - Gaming Magazine Template</title>
    <meta charset="UTF-8">
    <meta name="description" content="TheQuest Gaming Magazine Template">
    <meta name="keywords" content="gaming, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Favicon -->
    <link href="resources/assets/img/favicon.ico" rel="shortcut icon"/>

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
<section class="page-top-section set-bg" data-setbg="resources/assets/img/header-bg/2.jpg">
    <div class="container">
        <h2>Recenzje</h2>
    </div>
</section>
<!-- Page top section end -->

<!-- Blog section -->

<section class="blog-section spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-8">
                <div class="blog-post featured-post">
                    <c:if test="${pageNumber == 0}">
                        <c:forEach items="${game}" var="firstGame" begin="0" end="0">
                            <img src="/resources/assets/img/post/${firstGame.file}" alt="${firstGame.gameTitle}">
                            <div class="post-date">${firstGame.reviewTime}, ${firstGame.reviewDate}</div>
                            <h3>${firstGame.gameTitle}</h3>
                            <div class="post-metas">
                                <div class="post-meta">od: ${firstGame.reviewAuthor}</div>
                                <div class="post-meta">w <a href="/games">Recenzje</a></div>
                                <div class="post-meta">${comments.getCommentsQuantityToTheGamePost(firstGame.id)} <i
                                        class="fa fa-comment"></i></div>
                            </div>
                            <div class="game-description">
                                <p>${firstGame.description}</p>
                            </div>
                            <a href='<c:url value="/post/${firstGame.id}"/>' class="site-btn">Czytaj więcej</a>
                        </c:forEach>
                    </c:if>
                </div>
                <div class="row">
                    <c:forEach items="${game}" var="news" begin="${1 + pageNumber * 4}" end="${4 + pageNumber * 4}">
                        <div class="col-md-6">
                            <div class="blog-post">
                                <img src="/resources/assets/img/post/${news.file}" alt="${news.gameTitle}">
                                <div class="post-date">${news.reviewTime}, ${news.reviewDate}</div>
                                <h4>${news.gameTitle}</h4>
                                <div class="post-metas">
                                    <div class="post-meta">od ${news.reviewAuthor}</div>
                                    <div class="post-meta">w <a href="/games">Recenzje</a></div>
                                    <div class="post-meta">${comments.getCommentsQuantityToTheGamePost(news.id)}
                                        <i class="fa fa-comment"></i>
                                    </div>
                                </div>
                                <div class="game-description">
                                    <p>${news.description}</p>
                                </div>
                                <a href='<c:url value="/post/${news.id}"/>' class="read-more">Czytaj więcej</a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="site-pagination">
                    <c:if test="${pageNumber > 0}">
                        <a href="?page=${pageNumber-1}" class="sp-prev"><</a>
                    </c:if>
                    <c:if test="${game.size() > 0 }">
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
                            <a href="?page=${pageNumber+1}" class="sp-next">></a>
                        </c:if>
                    </c:if>
                </div>
            </div>
            <div class="col-lg-4 sidebar">
                <div class="sb-widget">
                    <h2 class="sb-title">Kategorie</h2>
                    <ul class="sb-cata-list">
                        <li><a href="/games">Recenzje<span>${game.size()}</span></a></li>
                        <li><a href="/news">Wiadomości<span>${newsSize}</span></a></li>

                    </ul>
                </div>
                <div class="sb-widget">
                    <h2 class="sb-title">Najnowsze wiadomości</h2>
                    <div class="latest-news-widget">
                        <c:forEach items="${news}" var="newest" begin="0" end="2">
                            <div class="ln-item">
                                <a href="/news/${newest.id}">
                                    <img src="/resources/assets/img/post/${newest.file}" alt="${newest.newsTitle}">
                                </a>
                                <div class="ln-text">
                                    <div class="ln-date">${newest.newsTime}, ${newest.newsDate}</div>
                                    <h6>${newest.newsTitle}</h6>
                                    <div class="ln-metas">
                                        <div class="ln-meta">od ${newsAuthor}</div>
                                        <div class="ln-meta">w <a href="/news">Wiadomości</a></div>
                                        <div class="ln-meta">${comments.getCommentsQuantityToTheNews(newest.id)}
                                            <i class="fa fa-comment"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="sb-widget">
                    <a href="#" class="add">
                        <img src="/resources/assets/img/add-2.jpg" alt="">
                    </a>
                </div>
                <div class="sb-widget">
                    <h2 class="sb-title">Najnowsze komentarze do recenzji</h2>
                    <div class="latest-comments-widget">
                        <c:forEach items="${commentsList}" var="commentsList" begin="0" end="4">
                            <div class="lc-item">
                                <img src="/resources/assets/img/post/${service.findUserFileByUsername(commentsList.username)}"
                                     alt="${commentsList.username}">
                                <div class="lc-text">
                                    <h6>${commentsList.username}<span> w </span><a
                                            href="/post/${commentsList.gameId}">${gameTitleById.getPostById(commentsList.gameId).getGameTitle()}</a>
                                    </h6>
                                    <div class="lc-date">${commentsList.commentTime}, ${commentsList.commentDate}</div>
                                </div>
                            </div>
                        </c:forEach>
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
