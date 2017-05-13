<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Task Status</title>
</head>
<body>
<br>

Status zadania ${task.name}<br>
Nazwa projektu : ${project.name}<br>
Nazwa iteracji : ${iteration.name}<br>
Nazwa story : ${story.name}<br>
Status taska : ${task.taskStatus}<br>
<br>
Pozostały czas : ${task.duration}


<form:form action="changeTime" method="post" modelAttribute="task">
    <form:hidden path="id"/>
    <form:hidden path="name"/>
    <tr>
        <td>Remaining time :</td>
        <td><form:input path="duration"/></td>
    </tr>

    <tr>
        <td colspan="2" align="center">
            <input type="submit" value="Change">
        </td>
    </tr>
</form:form>
</body>
</html>
