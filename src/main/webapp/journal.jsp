<%@include file="/jspf/header.jspf"%>

<html>
<head>
    <title>Journal</title>
    <%@include file="/jspf/head.jspf"%>
</head>
<body>
    <%@include file="/jspf/navbar.jspf"%>

    <h3>In progress</h3>

    <c:forEach items="${inProgressCourses}" var="course">
        Course name: ${course.name}
        Course theme: ${course.theme}
        Course start date: ${course.startDate}
        Course end date: ${course.endDate}
        Course status: ${course.courseStatus}
    </c:forEach>
    <br>


    <%@include file="/jspf/bootstrapScripts.jspf"%>
</body>
</html>
