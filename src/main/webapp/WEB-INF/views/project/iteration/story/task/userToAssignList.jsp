<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>User to add</title>
</head>
<body>

Lista Userow ktorym można dać taska

<div align="center">
    <h1>Task List</h1>
    <table border="1">
        <th>No</th>
        <th>Name</th>
        <th>Duration</th>
        <th>Actions</th>
        <c:forEach var="user" items="${userList}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>
                    <a href="/project/iteration/story/task/assign?id=${user.id}">Assign worker</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<a href="/">Home</a>
</body>
</html>
