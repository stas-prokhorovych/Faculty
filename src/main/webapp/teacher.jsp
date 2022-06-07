<%@include file="/jspf/header.jspf"%>

<html>
<head>
    <title>Title</title>
    <%@include file="/jspf/head.jspf"%>
</head>
<body>
<%@include file="/jspf/navbar.jspf"%>
    <div class="center">
        <h3>Our teachers</h3>

        <c:forEach items="${teachers}" var="teacher">
            <p>${teacher.firstName}${' '}${teacher.lastName}</p>
        </c:forEach>
    </div>

    <%@include file="/jspf/bootstrapScripts.jspf"%>
</body>
</html>