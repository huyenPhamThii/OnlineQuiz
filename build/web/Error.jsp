<%-- 
    Document   : Error
    Created on : Jul 23, 2021, 9:59:36 AM
    Author     : HuyenPT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/header.css" rel="stylesheet" type="text/css"/>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <title>Error Page</title>
    </head>
    <body>
        <div class="container">
            <jsp:include page="Header.jsp"/>
            <div class="main">
                <div class="content">
                    <div class="top-content" style="font-weight: bold; font-size: 20px">${error}</div>
                </div>
            </div>
        </div>
    </body>
</html>