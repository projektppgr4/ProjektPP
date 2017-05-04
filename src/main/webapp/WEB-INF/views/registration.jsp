<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<body>

<div class="container">

    <form:form method="POST" modelAttribute="userForm">
        <h2>Create your account</h2>

        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="ssoId" placeholder="ssoId"
                        autofocus="true"></form:input>
            <form:errors path="ssoId"></form:errors>
        </div>

        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="password" path="password" placeholder="Password"></form:input>
            <form:errors path="password"></form:errors>
        </div>

        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="email" placeholder="Email"></form:input>
            <form:errors path="email"></form:errors>
        </div>

        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="firstName" placeholder="First Name"></form:input>
            <form:errors path="firstName"></form:errors>
        </div>

        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="lastName" placeholder="Last Name"></form:input>
            <form:errors path="lastName"></form:errors>
        </div>


        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>

</div>
</body>
</html>