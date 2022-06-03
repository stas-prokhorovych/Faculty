<%@include file="/jspf/header.jspf"%>

<html>
<head>
    <title>Faculty</title>
    <%@include file="/jspf/head.jspf"%>
    <script defer src="js/login.js"></script>
    <link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>
    <h2><fmt:message key="page.login.title"/></h2>
        <form id="form" action="login" method="post">
            <div>
                <label for="login">Login:</label>
                <input id="login" name="login" type="text" placeholder="login">
                <div id="login-error" class="error"></div>
            </div>
            <div>
                <label for="password">Password:</label>
                <input id="password" name="password" type="password" placeholder="password">
                <div id="password-error" class="error"></div>
            </div>
            <button type="submit">Submit</button>
    </form>

    <%@include file="/jspf/footer.jspf"%>
</body>
</html>
