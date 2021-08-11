<%-- 
    Document   : TakeQuiz
    Created on : Jul 22, 2021, 11:05:51 PM
    Author     : HuyenPT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Take Quiz Page</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <div class="container">
            <%@include file="Header.jsp" %>
            <div class="main">
                <div class="content">
                    <div class="top-content">Welcome <span class="title">${sessionScope.account.getUserName()}</span></div>
                    </br>
                    <div class="error-msg">${msg}</div>
                    <div class="bottom-content">
                        <form action="takeQuiz" method="post">
                            Enter number of questions:<br/>
                            <input id="inputNumber" name="numOfQuestion" type="number" required min="1" max="${totalQuestion}"/>
                            <div class="button">
                                <button type="submit">Start</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
