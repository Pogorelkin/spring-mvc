<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Get Employee by his Id number</title>
    </head>
    <body>
        <div>
            <form action="../employee" method="get">
                <label for="id">ID: </label>
                <input type="text" name="id" placeholder="input id" required>
                <button type="submit">Get</button>
            </form>
            <a href='/home'>Home</a>
        </div>
    </body>
</html>