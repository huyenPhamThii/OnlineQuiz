<%-- 
    Document   : Login
    Created on : June 7, 2021, 9:55:35 AM
    Author     : HuyenPT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <div class="container">
            <%@include file="Header.jsp" %>
            <div class="main">
                <div class="content">
                    
                        <c:if test="${sessionScope.account==null}">
                            <div class="title top-content">Login Form</div>
                                <br/>
                                <div class="error-msg">${msg}</div>
                            <form class="bottom-content" action="login" method="POST">
                                <table>
                                    <tr>
                                        <td class="label">User Name:</td>
                                        <td class="txt"><input type="text" name="userName" value="${userName}" maxlength="20" required/></td>
                                    </tr>
                                    <tr>
                                        <td class="label">Password:</td>
                                        <td class="txt"><input type="password" name="password" value="${password}" maxlength="20" required/></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td>
                                            <button type="submit">Sign in</button>
                                            <a href="register">Register</a>
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </c:if>
                        <c:if test="${sessionScope.account!=null}">
                            <div class="top-content">Welcome <span class="title">${sessionScope.account.getUserName()}</span></div>
                        </c:if>
                        
                </div>
            </div>
        </div>
    </body>
</html>
