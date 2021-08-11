<%-- 
    Document   : register
    Created on : June 7, 2021, 10:36:45 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <div class="container">
            <%@include file="Header.jsp" %>
            <div class="main">
                <div class="content">
                    <div class="error-msg">${msg}</div>
                    <div class="top-content">${success}</div>
                    </br>
                    <div class="title top-content">Registration Form</div>

                    <form class="register bottom-content" action="register" method="post">
                        <table class="register">
                            <tr>
                                <td class="left">User Name:</td>
                                <td><input type="text" name="userName" value="${userName}" maxlength="20" required/></td>
                            </tr>
                            <tr>
                                <td class="left">Password:</td>
                                <td><input type="password" name="password" value="${password}" maxlength="20" required/></td>
                            </tr>
                            <tr>
                                <td class="left">User Type:</td>
                                <td><select name="userType">
                                        <option value="1" <c:if test="${userType == 1}"> selected</c:if> >Teacher</option>
                                        <option value="0" <c:if test="${userType == 0}"> selected</c:if> >Student</option>
                                    </select></td>
                            </tr>
                            <tr>
                                <td class="left">Email:</td>
                                <td><input type="email" name="email" required maxlength="30"/></td>
                            </tr>
                            <tr>
                                <td class="left"></td>
                                <td>
                                    <button type="submit">Register</button>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>

