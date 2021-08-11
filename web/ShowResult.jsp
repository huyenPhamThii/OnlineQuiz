<%-- 
    Document   : ShowResult
    Created on : Jul 23, 2021, 10:56:11 AM
    Author     : HuyenPT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result Page</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <div class="container">
            <%@include file="Header.jsp"%>
            <div class="main">
                <div class="content">
                    <form action="takeQuiz" method="get">
                        <div class="top-content">Your score:
                            <span class="title">${correctPoint} (${percent}) - ${resultStatus}</span>
                        </div>
                        </br>
                        <div class="top-content">
                            Take another test   
                            <button type="submit"> Start</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
