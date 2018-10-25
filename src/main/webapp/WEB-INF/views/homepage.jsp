<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<body>
<h1>My list</h1>

<table>
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Action</th>
    </tr>
    <c:forEach var="student" items="${students}">
        <c:url var="deleteLink" value="deleteStudent">
            <c:param name="studentId" value="${student.id}" />
        </c:url>
        <tr>
            <td>${student.firstName}</td>
            <td>${student.lastName}</td>
            <td><a href="${deleteLink}" onclick="if (!(confirm('Are you sure you want to delete this student?'))) return false">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<h3>Add new Student</h3>
<form:form action="saveStudent" modelAttribute="student" method="POST">
    <table>
        <tr>
            <td>First Name:</td>
            <td><form:input path="firstName" /></td>
        </tr>
        <tr>
            <td>Last Name:</td>
            <td><form:input path="lastName" /></td>
        </tr>
        <tr>
            <td />
            <td><input type="submit" value="save" /></td>
        </tr>
    </table>

</form:form>

</body>
</html>