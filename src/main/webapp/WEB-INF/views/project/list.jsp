<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Lista Projektow</title>
</head>
<body>


Lista porojektów użytkownika

<div align="center">
    <h1>Project List</h1>
    <h2><a href="/project/newProject?userId=${userId}">New Project</a></h2>
    <table border="1">
        <th>No</th>
        <th>Name</th>
        <th>Description</th>
        <th>Start Date</th>
        <th>Completion Date</th>
        <th>Actions</th>
        <c:forEach var="project" items="${projects}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${project.name}</td>
                <td>${project.description}</td>
                <td>${project.startDate}</td>
                <td>${project.completionDate}</td>
                <td>
                    <a href="edit?id=${project.id}">Edit</a>
                    &nbsp;
                    <a href="delete?id=${project.id}">Delete</a>
                    &nbsp;
                    <a href="details?id=${project.id}">Details</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<a href="/">Home</a>
</body>
</html>
