<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Lista Zadan</title>
</head>
<body>


Lista zadań przydzielonych do użytkownika

<div align="center">
    <h1>Task List</h1>
    <table border="1">
        <th>No</th>
        <th>Name</th>
        <th>Duration</th>
        <th>Task Status</th>
        <c:forEach var="task" items="${taskList}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${task.name}</td>
                <td>${task.duration}</td>
                <td>${task.taskStatus}</td>
                <td>
                    <a href="work?id=${task.id}">Work</a>
                    &nbsp;
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<a href="/">Home</a>
</body>
</html>
