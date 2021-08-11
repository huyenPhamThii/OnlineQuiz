<%-- 
    Document   : DoQuiz
    Created on : Jul 23, 2021, 10:00:33 AM
    Author     : HuyenPT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Do Quiz Page</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <div class="container">
            <%@include file="Header.jsp" %>
            <div class="main">
                <div class="content">
                    <c:if test="${sessionScope.account!=null and randomQuestionList != null}">
                        <input id="secs" type="hidden" value="${totalTime}"/>
                        <div class="do-quiz">
                            <table>
                                <tr>
                                    <td class="left">
                                        <div class="top-content">Welcome <span class="title">${sessionScope.account.getUserName()}</span></div>
                                    </td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td class="left"></td>
                                    <td class="middle"> Time remaining: <span id="timer"></span></td>
                                </tr>
                            </table>
                            <div style="margin-top: 24px">
                                <form id="doQuiz" action="showResult" method="post">
                                    <c:forEach items="${randomQuestionList}" var="ques" varStatus="status">
                                        <%--<c:forEach items="${listOpt}" var="opt" >--%>
                                            <div class="each-question">
                                                <div class="question-infor">
                                                    <div class="question">${ques.getContent()}</div>
                                                    <div class="ans">
                                                        <input type="checkbox" id="op1" name="${status.count}Answer" value="1">
                                                        <label for="op1">${ques.getOptList().get(0)}</label>
                                                    </div>
                                                    <div class="ans">
                                                        <input type="checkbox" id="op2" name="${status.count}Answer" value="2">
                                                        <label for="op2">${ques.getOptList().get(1)}</label>
                                                    </div>
                                                    <div class="ans">
                                                        <input type="checkbox" id="op3" name="${status.count}Answer" value="3">
                                                        <label for="op3">${ques.getOptList().get(2)}</label>
                                                    </div>
                                                    <div class="ans">
                                                        <input type="checkbox" id="op4" name="${status.count}Answer" value="4">
                                                        <label for="op4">${ques.getOptList().get(3)}</label>
                                                    </div>
                                                </div>
                                                <div class="right">
                                                    <c:if test="${status.count != randomQuestionList.size()}">
                                                        <button type="button" onclick="plusQues(1)">Next </button>
                                                    </c:if>
                                                    <c:if test="${status.count == randomQuestionList.size()}">
                                                        <button type="submit">Submit</button>
                                                    </c:if>
                                                </div>
                                            </div>
                                        <%--</c:forEach>--%>
                                    </c:forEach>
                                </form>
                            </div>
                        </div>

                    </c:if>

                </div>
            </div>
        </div>
        <script src="js/showQues.js" type="text/javascript"></script>
        <script src="js/timing.js" type="text/javascript"></script>
    </body>
</html>