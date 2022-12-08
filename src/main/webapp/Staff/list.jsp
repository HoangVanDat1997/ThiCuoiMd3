<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Management Application</title>
</head>
<body>
<center>
    <h1>Staff Management</h1>
    <h2>
        <a href="/StaffServlet?action=create">Add New Staff</a>
    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Staff</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Address</th>
            <th>PhoneNumber</th>
            <th>Salary</th>
            <th>Department</th>
        </tr>
        <c:forEach var="staff" items="${listStaff}">
            <tr>
                <td><c:out value="${staff.getId()}"/></td>
                <td><c:out value="${staff.getName()}"/></td>
                <td><c:out value="${staff.getEmail()}"/></td>
                <td><c:out value="${staff.getAddress()}"/></td>
                <td><c:out value="${staff.getPhoneNumber()}"/></td>
                <td><c:out value="${staff.getSalary()}"/></td>
                <td><c:out value="${staff.getDepartment()}"/></td>
                <td>
                    <a href="/Staff?action=edit&id=${staff.id}">Edit</a>
                    <a href="/Staff?action=delete&id=${staff.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
