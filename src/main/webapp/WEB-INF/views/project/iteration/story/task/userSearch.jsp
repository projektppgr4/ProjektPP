<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="type" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Search User</title>
</head>
<body>
<div align="center">
    <h1>Search User</h1>
    <table>
        <form:form action="/project/iteration/story/task/userToFind" method="post" modelAttribute="user">
            <tr>
                <td>Name:</td>
                <td><form:input path="lastName"/></td>
            </tr>
        </form:form>
    </table>
</div>

</body>
</html>