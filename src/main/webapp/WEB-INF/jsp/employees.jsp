<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Employees</title>
        <script src="js/jquery-3.4.1.min.js"></script>
        <script src="js/ajax.js"></script>
    </head>
    <body>

<div class="content">
        <div>
            <div>
            <table border="1" id="employeesTable">
                <caption><h2>Employee list</h2></caption>
                <thead>
                <tr>
                     <th>Id</th>
                     <th>FirstName</th>
                     <th>LastName</th>
                     <th>IdCardNumber</th>
                     <th>UPDATE</th>
                     <th>DELETE</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${employees}" var="employee">
                <tr>
                    <td>${employees.employeeId}</td>
                                              <td>${employees.firstName}</td>
                                              <td> ${employees.lastName}</td>
                                              <td> ${employees.idCardNumber}</td>
                                              <td><a href="/update-employee/${employees.employeeId}">Update</a></td>
                                              <td><a href="/delete-employee/${employees.employeeId}">Delete</a></td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
            </div>
        </div>
        <br>
                    <a href='/add-employee'>Add Employee</a>
    </div>
    </body>
</html>