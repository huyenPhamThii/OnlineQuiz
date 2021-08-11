<%-- 
    Document   : MakeQuiz
    Created on : June 7, 2021, 10:14:15 AM
    Author     : HuyenPT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Make Quiz Page</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <div class="container">
            <%@include file="Header.jsp" %>
            <div class="main">
                <div class="content">

                    <!--accessible for teacher only-->
                    <c:choose>
                        <c:when test="${sessionScope.account.getType() == 1}">

                            <div class="error-msg">${msg}</div>

                            <%--<c:if test="${success ne null}">--%>
                                ${success}
                            <%--</c:if>--%>
                            <form class="bottom-content" action="makeQuiz" method="post">
                                <table>
                                    <tr>
                                        <td class="tdlabel">
                                            Question:
                                        </td>
                                        <td>
                                            <textarea name="txtContent" rows="6" maxlength="150" required>${question.content}</textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="tdlabel">
                                            Option 1:
                                        </td>
                                        <td>
                                            <textarea name="txtOption1" rows="3" maxlength="150" required>${question.optList.get(0)}</textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="tdlabel">
                                            Option 2:
                                        </td>
                                        <td>
                                            <textarea name="txtOption2" rows="3" maxlength="150" required>${question.optList.get(1)}</textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="tdlabel">
                                            Option 3:
                                        </td>
                                        <td>
                                            <textarea name="txtOption3" rows="3" maxlength="150" required>${question.optList.get(2)}</textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="tdlabel">
                                            Option 4:
                                        </td>
                                        <td>
                                            <textarea name="txtOption4" rows="3" maxlength="150" required>${question.optList.get(3)}</textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="tdlabel">
                                            Answer(s):
                                        </td>
                                        <td>
                                            <input type="checkbox" id="cbOption1" name="answer" value="1">
                                            <label for="cbOption1"> Option 1 </label>
                                            <input type="checkbox" id="cbOption2" name="answer" value="2">
                                            <label for="cbOption2"> Option 2 </label>
                                            <input type="checkbox" id="cbOption3" name="answer" value="3">
                                            <label for="cbOption3"> Option 3 </label>
                                            <input type="checkbox" id="cbOption4" name="answer" value="4">
                                            <label for="cbOption4"> Option 4 </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td>
                                            <button type="submit">Save</button>
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </c:when>
                        <c:otherwise>
                            For teacher only!
                        </c:otherwise>
                    </c:choose>

                </div>
            </div>
        </div>
    </body>
</html>
