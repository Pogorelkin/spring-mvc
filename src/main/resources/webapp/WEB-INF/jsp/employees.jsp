<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <head>
        <title>Employees</title>
        <script>
             function getAllEmployees() {
                        $.ajax({
                            url: "http://localhost:8082/employees",
                            method: "GET",
                            dataType: "json",
                            success: function (data) {
                                var tableBody = $('#employeesTable tbody');
                                tableBody.empty();
                                $(data).each(function (index, element) {
                                    tableBody.append('<tr><td>'+element.employeeId+'</td><td>'+element.firstName+'</td><td>'+element.lastName+'</td><td>'+element.idCardNumber+'</td><td><button onclick = "deleteEmployee('+element.id+')">Delete</button></td></tr>');
                                })
                            },
                            error: function (error) {
                                alert(error);
                            }
                        })
                    }

            function deleteEmployee(id){
                $.ajax({
                    url: 'http://localhost:8082/employees/'+id,
                    method: 'DELETE',
                    success: function () {
                        alert('employee has been deleted');
                        getAllEmployees();
                    },
                    error: function (error) {
                        alert(error);
                    }
                })
            }

        </script>
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
                     <th>DELETE</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${employees}" var="employee">
                <tr>
                    <td>${employee.employeeId}</td>
                    <td>${employee.firstName}</td>
                    <td> ${employee.lastName}</td>
                    <td> ${employee.idCardNumber}</td>
                    <td><button onclick = "deleteemployee(${employee.employeeId})">Delete</button></td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
            </div>
        </div>
        <br>
            <a href='employees/add'>Add Employee</a>
    </div>
    </body>
