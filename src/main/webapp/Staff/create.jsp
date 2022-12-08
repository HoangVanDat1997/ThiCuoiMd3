<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Staff</title>
</head>
<body>
<center>
    <h1>Staff Management</h1>
    <h2>
        <a href="/StaffServlet?action=listStaff">List All Staff</a>
    </h2>
</center>
<div align="center">
    <form action="StaffServlet?action=create" method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>Add New Staff</h2>
            </caption>
            <tr>
                <th>Staff Name:</th>
                <td>
                    <input type="text" name="name" id="name" size="45"/>
                </td>
            </tr>
            <tr>
                <th>Staff Email:</th>
                <td>
                    <input type="text" name="email" id="email" size="45"/>
                </td>
            </tr>
            <tr>
                <th>Staff Address:</th>
                <td>
                    <input type="text" name="address" id="address" size="15"/>
                </td>
                <tr>
            <tr>
                <th>Staff PhoneNumber</th>
                <td>
                    <input type="text" name="phonenumber" id="phonenumber">
                </td>
            <tr>
            <tr>
                <th>Staff Salary</th>
                <td>
                    <input type="text" name="salary" id = salary>
                </td>
            <tr>
                <th> Staff Department</th>
                <td>
                    <input type="text" name="department" id="department">
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
