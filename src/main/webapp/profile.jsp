<%@include file="/jspf/header.jspf"%>

<html>
<head>
    <title>Faculty</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <%@include file="/jspf/head.jspf"%>
</head>
<body>
    <%@include file="/jspf/navbar.jspf"%>
    <h1>User information</h1>
    <c:choose>
        <c:when test="${sessionScope.login != null}">
            <p>Name: <c:out value="${sessionScope.name}"/></p>
            <p>Surname: <c:out value="${sessionScope.surname}"/></p>
            <p>You are: <c:out value="${sessionScope.role}"/></p>
            <p>Login: <c:out value="${sessionScope.login}"/></p>
            <p>Email: <c:out value="${sessionScope.email}"/></p>
            <p>Phone: <c:out value="${sessionScope.phone}"/></p>
        </c:when>
    </c:choose>

    <%@include file="/jspf/footer.jspf"%>
</body>
</html>

