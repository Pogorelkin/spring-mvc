<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <head>
        <title>Login</title>
    </head>
    <body>
        <div>
            <label><b>Login</b></label>
            <form action="/login" method="post">
                <input type="text" name="login" required>
                <label><b>password</b></label>
                <input type="password" name="password" required>
                <button type="submit">login</button>
            </form>
        </div>
    </body>
