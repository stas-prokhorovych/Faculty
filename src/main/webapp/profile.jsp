<%@include file="/jspf/header.jspf"%>

<html>
<head>
    <title>Faculty</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <%@include file="/jspf/head.jspf"%>
    <style>
        body {background-color: darkgrey;}
        .center {
            text-align: center;
        }
    </style>
</head>
<body>
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
</body>
</html>

