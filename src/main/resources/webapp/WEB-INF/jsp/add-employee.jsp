<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Add-Employee</title>
    </head>
    <body>
        <div>
            <h3>Add employee</h3>
            <form action="/employees" method="post">
                <div>
                    <label for="employeeId">EmpId </label>
                    <input type="text" value="${employee.employeeId}" name="employeeId" required>
                </div>
                <div>
                    <label for="firstName">F.Name</label>
                    <input type="text" value="${employee.firstName}" name="firstName" required>
                </div>
                <div>
                    <label for="lastName">L.Name </label>
                    <input type="text" value="${employee.lastName}" name="lastName" required>
                </div>
                <div>
                    <label for="idCardNumber">idCardNumber </label>
                    <input type="text" value="${employee.idCardNumber}" name="idCardNumber" required>
                </div>
                <button type="submit">add</button>
            </form>
            <a href='/employees'>Back</a>
            <a href='/home'>Home</a>
        </div>
    </body>
</html>