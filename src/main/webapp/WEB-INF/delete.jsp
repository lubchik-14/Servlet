<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Delete</title>
    <link href="<c:url value='/css/style.css' />" rel="stylesheet">
</head>
<body>
<div class="form delete">
    <%@include file="header.jsp" %>
    <h4>Check users bellow to delete</h4>
    <form name="delete-form" action="delete" method="POST">
        <table>
            <c:forEach var="entry"
                       items="${requestScope.users}">
                <tr>
                    <td><c:out value="${entry.key}"/></td>
                    <td><c:out value="${entry.value.userParameters.login}"/></td>
                    <td><c:out value="${entry.value.userParameters.role}"/></td>
                    <td><input type="checkbox" name="deleteId" value="${entry.key}"
                               onchange="document.getElementById('deleteButton').disabled = !this.checked;"
                        <c:set var="isRestricted" value="false"/>
                        <c:forEach var="restrictionsItem" items="${sessionScope.restrictions}">
                            <c:if test="${restrictionsItem eq entry.value.userParameters.role}">
                                <c:set var="isRestricted" value="true"/>
                            </c:if>
                        </c:forEach>
                        <c:out value="${isRestricted ? 'disabled title=Restricted' : ''}"/>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <input id="deleteButton" name="deleteButton" type="submit" value="Delete" disabled>
    </form>
    <%@include file="message.jsp" %>
</div>
</body>
</html>