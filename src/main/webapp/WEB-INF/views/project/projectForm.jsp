<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="type" uri="http://www.springframework.org/tags/form" %>
<html>

<head>
    <title>Title</title>
</head>
<body>

nowy projekt lub edycja


<div align="center">
    <h1>New/EditProject</h1>
    <table>
        <form:form action="save" method="post" modelAttribute="project">
            <form:hidden path="id"/>
            <tr>
                <td>Name:</td>
                <td><form:input path="name"/></td>
            </tr>
            <tr>
                <td>Start Date:</td>
                <td><form:input path="startDate" type="date"/></td>
            </tr>
            <tr>
                <td>Completion Date:</td>
                <td><form:input path="completionDate" type="date"/></td>
            </tr>
            <tr>
                <td>Description:</td>
                <td><form:input path="description"/></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save">
                </td>
            </tr>
        </form:form>
    </table>
</div>


</body>
</html>
