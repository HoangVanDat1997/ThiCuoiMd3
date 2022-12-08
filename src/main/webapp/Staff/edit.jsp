<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Staff Management</title>
</head>
<body>
<center>
    <h1>Starr Management</h1>
    <h2>
        <a href="/StaffServlet?action=listStaff">List All Staff</a>
    </h2>
</center>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    Edit Staff
                </h2>
            </caption>
            <c:if test="${staff != null}">
                <input type="hidden" name="id" value="<c:out value='${staff.id}' />"/>
            </c:if>
            <tr>
                <th>Staff Name:</th>
                <td>
                    <input type="text" name="name" size="45"
                           value="<c:out value='${staff.name}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Staff Email:</th>
                <td>
                    <input type="text" name="email" size="45"
                           value="<c:out value='${staff.email}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Staff Address:</th>
                <td>
                    <input type="text" name="address" size="15"
                           value="<c:out value='${staff.address}' />"
                    />
                </td>
            </tr>
            <tr>
                <th> Staff PhoneNumber</th>
                <td>
                    <input type="text" name = "phonenumber" size="15"
                    value="<c:out value='${staff.phonenumber}' />"
                           />
                </td>
            </tr>
            <tr>
                <th> Staff Salary</th>
                <td>
                    <input type="text" name = "salary" size="15"
                    value="<c:out value='${staff.salary}' />"
                           />
                </td>
            </tr>
            <tr>
                <th> Staff Department</th>
                <td>
                    <input type="text" name = "department" size="15"
                    value="<c:out value='${staff.department}' />"
                           />
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
