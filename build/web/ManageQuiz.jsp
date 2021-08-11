<%-- 
    Document   : manageQuiz
    Created on : June 7, 2021, 10:25:47 AM
    Author     : DELL
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Quiz Page</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <div class="container">
            <%@include file="Header.jsp" %>
            <div class="main">
                <div class="content">

                    <!--get number from sessionScope-->
                    <div class="top-content">Number of question: <span>${totalQuestions}</span></div>
                    <div class="bottom-content list">
                        <div class="each-row"> 
                            <div class="th left">Question</div>
                            <div class="th right">Date Created</div>
                        </div>

                        <div class="all-question">
                            <c:forEach items="${questionList}" var="ques">
                                <div class="each-row">
                                    <div class="left">${ques.getContent()}</div>
                                    <div class="right">${ques.getDateFormat()}</div>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="paging">
                            <c:forEach begin="1" end="${numberOfPage}" var="i">
                                <a class="${i==pageIndex?"active":""}" href="manageQuiz?pageIndex=${i}">${i}</a>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>


