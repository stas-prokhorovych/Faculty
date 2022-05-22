<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Faculty</title>
    </head>
    <body>

        <form action="signup" method="post">
            <label for="login">Enter login:</label><br>
            <input type="text" id="login" name="login"><br>

            <label for="password">Enter password:</label><br>
            <input type="text" id="password" name="password"><br>

<%--            <label for="password-repeat">Repeat password:</label><br>--%>
<%--            <input type="text" id="password-repeat" name="password-repeat"><br>--%>

            <label for="email">Enter email:</label><br>
            <input type="text" id="email" name="email"><br>

            <label for="first-name">Enter first name:</label><br>
            <input type="text" id="first-name" name="first-name"><br>

            <label for="last-name">Enter last name:</label><br>
            <input type="text" id="last-name" name="last-name"><br>

            <label for="phone">Enter phone:</label><br>
            <input type="text" id="phone" name="phone"><br>

            <input type="submit" value="Submit">
        </form>
    </body>
</html>
