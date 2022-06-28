<%@include file="/WEB-INF/jspf/header.jspf"%>

<html>
<head>
    <title><fmt:message key="profile.title"/></title>
    <%@include file="/WEB-INF/jspf/head.jspf"%>
</head>
<body class="profile-background">
    <%@include file="/WEB-INF/jspf/navbar.jspf"%>

    <div class="center block">
        <c:if test="${sessionScope.access eq false}">
            <h1>Sorry, but your blocked</h1>
        </c:if>
    </div>


    <div class="center">
        <h1><fmt:message key="profile.user.info"/></h1>
        <c:choose>
            <c:when test="${sessionScope.login != null}">
                <p><b><fmt:message key="profile.name"/></b> <c:out value="${sessionScope.name}"/></p>
                <p><b><fmt:message key="profile.surname"/></b> <c:out value="${sessionScope.surname}"/></p>
                <p><b><fmt:message key="profile.you.are"/></b> <c:out value="${sessionScope.role}"/></p>
                <p><b><fmt:message key="profile.login"/></b> <c:out value="${sessionScope.login}"/></p>
                <p><b><fmt:message key="profile.email"/></b> <c:out value="${sessionScope.email}"/></p>
                <p><b><fmt:message key="profile.phone"/></b> <c:out value="${sessionScope.phone}"/></p>
            </c:when>
        </c:choose>
    </div>
</body>
</html>

