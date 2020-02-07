<div class="message">
    <p><c:out value="${empty sessionScope.errorMessage ? '': sessionScope.errorMessage}"/></p>
    <p><c:out value="${empty sessionScope.message ? '': sessionScope.message}"/></p>
</div>