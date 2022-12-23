<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%--<%@ page contentType="text/html; charset=UTF-8" %>--%>
<!DOCTYPE html>
<html lang="pl-PL">
<head>
    <title>KKGames - News, Reviews, GAMES!</title>
    <meta charset="UTF-8">
    <meta name="description" content="KKGames - News, Reviews, GAMES!">
    <meta name="keywords" content="gaming, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Favicon -->

    <!-- Google font -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,300i,400,400i,500,500i,700,700i&display=swap"
          rel="stylesheet">

    <!-- Stylesheets -->
    <%@include file="dynamic/css.jspf" %>

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

<!-- Hero section -->
<section class="hero-section">
    <div class="hero-slider owl-carousel">
        <div class="hero-item set-bg" data-setbg="resources/assets/img/slider/1.jpg">
            <div class="container">
                <div class="row">
                    <div class="col-lg-10 offset-lg-1">
                        <h2>KKGames</h2>
                        <p>Najlepsze recenzje!</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="hero-item set-bg" data-setbg="resources/assets/img/slider/2.jpg">
            <div class="container">
                <div class="row">
                    <div class="col-lg-10 offset-lg-1">
                        <h2>KKGames</h2>
                        <p>Najświeższe newsy!</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Hero section end -->

<!-- Blog section -->
<section class="blog-section spad-index">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 blog-posts">
                <div class="blog-post featured-post">
                    <h3>O mnie</h3>
                    <p>Cześć! Jestem Krystian. Stawiam pierwsze kroki w świecie programowania! Zainspirowany nową pasją,
                        postanowiłem stworzyć swój pierwszy projekt, w którym ćwiczę i utrwalam zdobytą wiedzę.
                        <br><br>
                        Strona internetowa, którą teraz przeglądasz przyjęła formę witryny z informacjami ze świata
                        gamingu, wraz z dodatkiem moich autorskich recenzji. Zastrzegam, że zamieszczone treści mogą być
                        nieaktualne, gdyż głównym zamysłem projektu było stworzenie funkcjonalnej witryny,
                        przetestowaniu jej działania i udostępnienia poza mój lokalny obszar roboczy.
                        Poszukuję swojej pierwszej pracy, jako programista Java, dlatego zdecydowałem się do
                        zaprezentowania swoich umiejętności udostępniając ten projekt dla szerszego grona osób.
                        <br><br>
                        Wszelki feedback mile widziany, zapraszam do kontaktu ze mną. Wszelkie informacje i
                        dane pod którymi można mnie znaleźć, zawarte są w stopce strony.
                    </p>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Footer section -->
<%@include file="dynamic/footer.jspf" %>
<!-- Footer section end -->

<!--====== Javascripts & Jquery ======-->
<%@include file="dynamic/javaScript.jspf" %>

</body>
</html>
