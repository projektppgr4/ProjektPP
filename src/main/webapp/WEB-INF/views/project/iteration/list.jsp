<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>details</title>
</head>
<body>
Project details i jego iteracje do wgladu

rozne info
<div align="center">
    <table border="1">
        <th>No</th>
        <th>Name</th>
        <th>Description</th>
        <th>Actions</th>
        <c:forEach var="iteration" items="${iterationList}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${iteration.name}</td>
                <td>${iteration.startDate}</td>
                <td>
                    <a href="edit?id=${iteration.id}">Edit</a>
                    &nbsp;
                    <a href="delete?id=${iteration.id}">Delete</a>
                    &nbsp;
                    <a href="/project/iteration/details?id=${iteration.id}">Details</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
