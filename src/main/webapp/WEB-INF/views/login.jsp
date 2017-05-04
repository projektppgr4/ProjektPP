<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>

    <title>Login page</title>

</head>

<body>
<div align="center">
    <c:url var="loginUrl" value="/login"/>
    <form action="${loginUrl}" method="post">
        <c:if test="${param.error != null}">
            <p>Invalid username and password.</p>

        </c:if>
        <c:if test="${param.logout != null}">

            <p>You have been logged out successfully.</p>
        </c:if>

        <input type="text" id="username" name="ssoId" placeholder="Enter Username" required>
        <input type="password" id="password" name="password" placeholder="Enter Password" required>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <div class="form-actions">
            <input type="submit" value="Log in">
        </div>
    </form>
</div>
</body>
</html>