<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="pl-PL">
<head>
    <title>KKGames - News, Reviews, GAMES!</title>
    <meta charset="UTF-8">
    <meta name="description" content="KKGames - News, Reviews, GAMES!">
    <meta name="keywords" content="gaming, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Favicon -->
    <link href="img/favicon.ico" rel="shortcut icon"/>

    <!-- Google font -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,300i,400,400i,500,500i,700,700i&display=swap"
          rel="stylesheet">

    <!-- Stylesheets -->
    <%@include file="../dynamic/css.jspf" %>

</head>
<body>


<!-- Page top section -->
<form method="post" action='<c:url value="/editGame/${id}"/>'>
    <!-- Content Row -->
    <div class="row">
        <div class="col-xl-12 col-md-12 mb-12">
            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <div class="form-group row">
                        <label class="col-2 col-form-label">Tytuł</label>
                        <div class="col-10">
                            <input class="form-control" type="text" name="gameTitle" value="${editGamePost.gameTitle}"
                                   placeholder="Tytuł opisywanej gry">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-2 col-form-label">Opis</label>
                        <div class="col-10">
                            <textarea placeholder="Opis recenzowanej gry" name="description" rows="20"
                                      cols="100">${editGamePost.description}</textarea>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-2 col-form-label">Ocena</label>
                        <div class="col-10">
                            <input class="form-control" type="text" name="rating"
                                   value="${editGamePost.rating}" placeholder="Ocena gry z przedziału 1-100">
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-2">
                            <input class="form-control" type="hidden" name="reviewAuthor"
                                   value="${editGamePost.reviewAuthor}">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-2 col-form-label">Nazwa obrazka do posta</label>
                        <div class="col-10">
                            <select name="file">
                                <option selected="${editGamePost.file}">${editGamePost.file}</option>
                                <c:forEach items="${allFiles}" var="imageTitle">
                                    <option>${imageTitle}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <input class="btn btn-success pull-left" type="submit" value="Wyślij" id="searchButton">
</form>
<input class="btn btn-success pull-left" type="submit" value="Anuluj" onclick="window.location.href='../games'">


<!--====== Javascripts & Jquery ======-->
<%@include file="../dynamic/javaScript.jspf" %>

</body>
</html>
