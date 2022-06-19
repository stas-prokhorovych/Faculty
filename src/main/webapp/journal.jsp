<%@include file="/jspf/header.jspf"%>

<html>
<head>
    <title>Journal</title>
    <%@include file="/jspf/head.jspf"%>
</head>
<body>
    <%@include file="/jspf/navbar.jspf"%>

    <h2 class="center">Courses in progress that you teach</h2>
    <div class="row justify-content-center">
        <div class="row col-md-8">
            <table class="table table-bordered table-sm">
                <thead class="thead-light">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Theme</th>
                    <th>Start date</th>
                    <th>End date</th>
                    <th>Duration in days</th>
                    <th>Teacher option</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="course" items="${inProgressCourses}">
                    <tr>
                        <td>${course.id}</td>
                        <td>${course.name}</td>
                        <td>${course.theme}</td>
                        <td>${course.startDate}</td>
                        <td>${course.endDate}</td>
                        <td>${course.durationInDays}</td>
                        <td>
                            <form method="post" action="<c:url value='/controller'/>">
                                <input type="hidden" name="command" value="SHOW_GRADUATES">
                                <input type="number" hidden name="teacher-id" value="${sessionScope.id}"/>
                                <input type="number" hidden name="course-id" value="${course.id}"/>
                                <input type="submit" name="enroll" value="End this course"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <h2 class="center">Finished courses that you taught</h2>
    <div class="row justify-content-center">
        <div class="row col-md-8">
            <table class="table table-bordered table-sm">
                <thead class="thead-light">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Theme</th>
                    <th>Start date</th>
                    <th>End date</th>
                    <th>Duration in days</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="course" items="${finishedCourses}">
                    <tr>
                        <td>${course.id}</td>
                        <td>${course.name}</td>
                        <td>${course.theme}</td>
                        <td>${course.startDate}</td>
                        <td>${course.endDate}</td>
                        <td>${course.durationInDays}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <%@include file="/jspf/bootstrapScripts.jspf"%>
</body>
</html>
