<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Lista Iteracji</title>
</head>
<body>
Project details i jego iteracje do wgladu

rozne info
<div align="center">
    <h1>Iteration List</h1>
    <h2><a href="/project/newIteration?projectId=${projectId}">New Iteration</a></h2>
    <table border="1">
        <th>No</th>
        <th>Name</th>
        <th>Start Date</th>
        <th>End Date</th>
        <th>Actions</th>
        <c:forEach var="iteration" items="${iterationList}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${iteration.name}</td>
                <td>${iteration.startDate}</td>
                <td>${iteration.endDate}</td>
                <td>
                    <a href="/project/iteration/edit?id=${iteration.id}">Edit</a>
                    &nbsp;
                    <a href="/project/iteration/delete?id=${iteration.id}">Delete</a>
                    &nbsp;
                    <a href="/project/iteration/details?id=${iteration.id}">Details</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<a href="/">Home</a>
</body>
</html>
