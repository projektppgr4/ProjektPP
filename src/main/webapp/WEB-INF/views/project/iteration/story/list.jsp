<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Iteration details i jego story do wgladu

<div align="center">
    <h1>Story List</h1>
    <h2><a href="/project/iteration/newStory?iterationId=${iterationId}">New Story</a></h2>
    <table border="1">
        <th>No</th>
        <th>Name</th>
        <th>Actions</th>
        <c:forEach var="story" items="${storyList}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${story.name}</td>
                <td>
                    <a href="/project/iteration/story/edit?id=${story.id}">Edit</a>
                    &nbsp;
                    <a href="/project/iteration/story/delete?id=${story.id}">Delete</a>
                    &nbsp;
                    <a href="/project/iteration/story/details?id=${story.id}">Details</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
