<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Create</title>
    <link href="<c:url value='/css/style.css' />" rel="stylesheet">
</head>
<body>
<div class="form create">
    <%@include file="header.jsp"%>
    <h4>Fill the fields bellow to create a new user</h4>
    <form name="creation-form" action="create" method="POST">
        <%@include file="formFields.jsp"%>
        <input type="submit" value="Create">
    </form>
    <%@include file="message.jsp"%>
</div>
</body>
</html>