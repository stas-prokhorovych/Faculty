<%@include file="/jspf/header.jspf"%>

<html>
<head>
    <title>Journal</title>
    <%@include file="/jspf/head.jspf"%>
</head>
<body>
    <%@include file="/jspf/navbar.jspf"%>

<%--    <h3>Opened for registration</h3>--%>

<%--    <h3>In progress</h3>--%>

    <h3>Finished</h3>

    <c:forEach items="${finishedCourses}" var="finishedCourse">
        Course name: ${finishedCourse.name}
        Course theme: ${finishedCourse.theme}
        Course start date: ${finishedCourse.startDate}
        Course end date: ${finishedCourse.endDate}
        Course status: ${finishedCourse.courseStatus}
    </c:forEach>

    <br>


    <%@include file="/jspf/bootstrapScripts.jspf"%>
</body>
</html>
