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
<section class="page-top-section set-bg" data-setbg="resources/assets/img/header-bg/1.jpg">
    <div class="container">
        <h2>Wiadomości</h2>
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
                        <c:forEach items="${news}" var="firstNews" begin="0" end="0">
                            <img src="/resources/assets/img/post/${firstNews.file}" alt="${firstNews.newsTitle}">
                            <div class="post-date">${firstNews.newsTime}, ${firstNews.newsDate}</div>
                            <h3>${firstNews.newsTitle}</h3>
                            <div class="post-metas">
                                <div class="post-meta">od: ${firstNews.newsAuthor}</div>
                                <div class="post-meta">w <a href="/news">Wiadomości</a></div>
                                <div class="post-meta">${comments.getCommentsQuantityToTheNews(firstNews.id)} <i
                                        class="fa fa-comment"></i></div>
                            </div>
                            <div class="game-description">
                                <p>${firstNews.description}</p>
                            </div>
                            <a href='<c:url value="/news/${firstNews.id}"/>' class="site-btn">Czytaj więcej</a>
                        </c:forEach>
                    </c:if>
                </div>
                <div class="row">
                    <c:forEach items="${news}" var="news" begin="${1 + pageNumber * 4}" end="${4 + pageNumber * 4}">
                        <div class="col-md-6">
                            <div class="blog-post">
                                <img src="/resources/assets/img/post/${news.file}" alt="${news.newsTitle}">
                                <div class="post-date">${news.newsTime}, ${news.newsDate}</div>
                                <h4>${news.newsTitle}</h4>
                                <div class="post-metas">
                                    <div class="post-meta">od ${news.newsAuthor}</div>
                                    <div class="post-meta">w <a href="/games">Recenzje</a></div>
                                    <div class="post-meta">${comments.getCommentsQuantityToTheNews(news.id)}
                                        <i class="fa fa-comment"></i>
                                    </div>
                                </div>
                                <div class="game-description">
                                    <p>${news.description}</p>
                                </div>
                                <a href='<c:url value="/news/${news.id}"/>' class="read-more">Czytaj więcej</a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="site-pagination">
                    <c:if test="${pageNumber > 0}">
                        <a href="?page=${pageNumber-1}" class="sp-prev"><</a>
                    </c:if>
                    <c:if test="${news.size() > 0 }">
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
                        <li><a href="/games">Recenzje<span>${gamesSize}</span></a></li>
                        <li><a href="/news">Wiadomości<span>${news.size()}</span></a></li>
                    </ul>
                </div>
                <div class="sb-widget">
                    <h2 class="sb-title">Najnowsze recenzje</h2>
                    <div class="latest-news-widget">
                        <c:forEach items="${games}" var="newestGames" begin="0" end="2">
                            <div class="ln-item">
                                <a href="/games/${newestGames.id}">
                                    <img src="/resources/assets/img/post/${newestGames.file}"
                                         alt="${newestGames.gameTitle}">
                                </a>
                                <div class="ln-text">
                                    <div class="ln-date">${newestGames.reviewTime}, ${newestGames.reviewDate}</div>
                                    <h6>${newestGames.gameTitle}</h6>
                                    <div class="ln-metas">
                                        <div class="ln-meta">od ${newestGames.reviewAuthor}</div>
                                        <div class="ln-meta">w <a href="/games">Recenzje</a></div>
                                        <div class="ln-meta">${comments.getCommentsQuantityToTheGamePost(newestGames.id)}
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
                    <h2 class="sb-title">Najnowsze komentarze do wiadomości</h2>
                    <div class="latest-comments-widget">
                        <c:forEach items="${commentsList}" var="commentsList" begin="0" end="4">
                            <div class="lc-item">
                                <img src="/resources/assets/img/post/${service.findUserFileByUsername(commentsList.username)}"
                                     alt="${commentsList.username}">
                                <div class="lc-text">
                                    <h6>${commentsList.username}<span> w </span><a
                                            href="/post/${commentsList.newsId}">${newsTitleById.getNewsPostById(commentsList.newsId).getNewsTitle()}</a>
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
