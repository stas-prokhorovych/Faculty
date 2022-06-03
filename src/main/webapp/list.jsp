<%@include file="/jspf/header.jspf"%>

<html>
<head>
    <title>Faculty</title>
    <%@include file="/jspf/head.jspf"%>
    <link rel="stylesheet" href="css/styles.css"/>
</head>
<body>
    <%@include file="/jspf/navbar.jspf"%>
    <h1>Courses by this theme</h1>

    <form action="find-courses">
        <label for="sort">Sort by:</label>
        <select name="sort" id="sort">
                <option value="a-z">a-z</option>
                <option value="z-a">z-a</option>
                <option value="duration">duration</option>
                <option value="student-enrolled">student enrolled</option>
        </select>
        <br><br>
        <input type="hidden" name="theme" value="${theme}">
        <input type="submit" value="Submit">
    </form>

    <hr class="divider"/>
    <c:forEach var="course" items="${requestScope.courses}">
        <ul>
            <li><b><c:out value="${course.name}"/></b></li>
            <p>Start date: <c:out value="${course.startDate}"/></p>
            <p>End date: <c:out value="${course.endDate}"/></p>
            <p>Duration in days:  <c:out value="${course.durationInDays}"/></p>
            <c:choose>
                <c:when test="${sessionScope.role == 'Admin'}">
                    <form method="post" action="<c:url value='/delete-course'/>">
                        <input type="number" hidden name="id" value="${course.id}"/>
                        <input type="submit" name="delete" value="Delete"/>
                    </form>

                    <form method="get" action="<c:url value='/update-course'/>">
                        <input type="number" hidden name="id" value="${course.id}"/>
                        <input type="submit" value="Update"/>
                    </form>
                </c:when>
            </c:choose>
        </ul>
        <hr/>
    </c:forEach>

    <%@include file="/jspf/footer.jspf"%>
</body>
</html>

