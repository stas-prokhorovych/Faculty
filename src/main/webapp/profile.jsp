<%@include file="/jspf/header.jspf"%>

<html>
<head>
    <title>Faculty</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <%@include file="/jspf/head.jspf"%>
</head>
<body class="profile-background">
    <%@include file="/jspf/navbar.jspf"%>
    <div class="center">
        <h1>User information</h1>
        <c:choose>
            <c:when test="${sessionScope.login != null}">
                <p><b>Name:</b> <c:out value="${sessionScope.name}"/></p>
                <p><b>Surname:</b> <c:out value="${sessionScope.surname}"/></p>
                <p><b>You are:</b> <c:out value="${sessionScope.role}"/></p>
                <p><b>Login:</b> <c:out value="${sessionScope.login}"/></p>
                <p><b>Email:</b> <c:out value="${sessionScope.email}"/></p>
                <p><b>Phone:</b> <c:out value="${sessionScope.phone}"/></p>
            </c:when>
        </c:choose>
    </div>
    <br><br>
    <h3>Courses you registered but not started yet</h3>

    <h3>Courses in progress</h3>

    <h3>Courses you finish</h3>
</body>
</html>

