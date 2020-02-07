<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Home</title>
    <link href="<c:url value='/css/style.css' />" rel="stylesheet">
</head>
<body>
<div class="form home">
    <%@include file="header.jsp" %>
    <h1>Hello, ${sessionScope.userName}!</h1>
    <form name="login-form" action="home" method="POST"
    <c:out value="${empty sessionScope.userId ? '' : 'style=display:none'}"/>>
        <%@include file="formFields.jsp"%>
        <input type="submit" value="Login">
    </form>
    <%@include file="message.jsp"%>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>