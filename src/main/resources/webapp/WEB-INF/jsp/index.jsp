<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home page</title>
</head>
<body>
    <div>
        <p><h1>Home page</h1></p>
        <p>Greetings, <b>${login}</b></p>
        <h2><a href="/employees">All</a></h2>
        <h2><a href="/employee/get">Get</a></h2>
        <h2><a href="/employee/add">Get</a></h2>
        <h2><a href="/logout"></a></h2>        
    </div>
</body>
</html>