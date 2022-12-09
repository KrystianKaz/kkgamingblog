<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pl-PL">
<head>
    <title>KKGames - News, Reviews, GAMES!</title>
    <meta charset="UTF-8">
    <meta name="description" content="KKGames - News, Reviews, GAMES!">
    <meta name="keywords" content="gaming, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <%@include file="../dynamic/css.jspf" %>

</head>
<body class="bg-gradient-primary">

<div class="container">
    <div class="card o-hidden border-0 shadow-lg my-5">
        <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
                <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                <div class="col-lg-7">
                    <div class="p-5">
                        <div class="text-center">
                            <h1 class="h4 text-gray-900 mb-4">Utwórz konto!</h1>
                        </div>
                        <form class="user" method="post" action='<c:url value="/addUser"/>'>
                            <div class="form-group row">
                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <input type="text" class="form-control form-control-user" name="username"
                                           id="username" placeholder="Nick użytkownika">
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <input type="email" class="form-control form-control-user" name="email" id="email"
                                           placeholder="adres Email">
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <input type="password" class="form-control form-control-user" name="password"
                                           placeholder="Hasło">
                                </div>
                            </div>
                            <input class="btn btn-primary btn-user btn-block" type="submit" value="Rejestruj">
                        </form>
                        <div class="text-center">
                            <a class="small" href="/login">Masz już konto? Zaloguj się!</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>


<%@include file="../dynamic/javaScript.jspf" %>

</body>

</html>
