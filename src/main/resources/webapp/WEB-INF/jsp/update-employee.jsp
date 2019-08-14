<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>UpdEmployee</title>
    </head>
    <body>
        <div>
            <h3>Update employee</h3>
            <form action="/update-employee" method="post">
                <div>
                    <label for="employeeId">EmpId </label>
                    <input type="text" value="${employees.employeeId}" name="employeeId" required>
                </div>
                <div>
                    <label for="firstName">F.Name</label>
                    <input type="text" value="${employees.firstName}" name="firstName" required>
                </div>
                <div>
                    <label for="lastName">L.Name </label>
                    <input type="text" value="${employees.lastName}" name="lastName" required>
                </div>
                <div>
                    <label for="idCardNumber">idCardNumber </label>
                    <input type="text" value="${employees.idCardNumber}" name="idCardNumber" required>
                </div>
                <button type="submit">Upd</button>
            </form>
            <a href='/employees'>Back</a>
            <a href='/home'>Home</a>
        </div>
    </body>
</html>