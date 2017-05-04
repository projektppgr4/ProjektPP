<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
Users

<table border="1">
    <th>No</th>
    <th>Name</th>
    <th>Surname</th>
    <th>Actions</th>
    <c:forEach var="user" items="${userList}" varStatus="status">
        <tr>
            <td>${status.index + 1}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>
                //TODO
                <a href="edit?id=${task.id}">Edit</a>
                &nbsp;
                <a href="delete?id=${task.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
