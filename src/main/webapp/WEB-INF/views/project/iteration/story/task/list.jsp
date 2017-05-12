<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Lista Taskow</title>
</head>
<body>

Story details i jego taski do wgladu

<div align="center">
    <h1>Task List</h1>
    <h2><a href="task/newTask?storyId=${storyId}">New Task</a></h2>

    <table border="1">
        <th>No</th>
        <th>Name</th>
        <th>Duration</th>
        <th>Actions</th>
        <c:forEach var="task" items="${taskList}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${task.name}</td>
                <td>${task.duration}</td>
                <td>
                    <a href="task/edit?id=${task.id}">Edit</a>
                    &nbsp;
                    <a href="task/delete?id=${task.id}">Delete</a>
                    &nbsp;
                    <a href="task/assignToTask?id=${task.id}">Assign worker</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<a href="/">Home</a>
</body>
</html>
