<ul>
    <li><input name="login" type="text" placeholder=" Login" required></li>
    <li><input name="password" type="password" placeholder=" Password" required></li>
</ul>
<select name="role" required>
    <option value="" disabled selected>Choose a role</option>
    <c:forEach var="roleItem" items='${applicationScope.roleList}'>
        <option value='<c:out value="${roleItem}" />'><c:out value="${roleItem}"/></option>
    </c:forEach>
</select>