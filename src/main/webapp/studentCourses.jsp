<%@include file="/jspf/header.jspf" %>

<html>
<head>
    <title>Your courses</title>
    <%@include file="/jspf/head.jspf" %>
</head>
<body>
    <%@include file="/jspf/navbar.jspf" %>

    <h2 class="center">Registered courses</h2>
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
                    <c:forEach var="course" items="${registeredCourses}">
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

    <h2 class="center">Courses in progress</h2>
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
                <c:forEach var="course" items="${inProgressCourses}">
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

    <%@include file="/jspf/bootstrapScripts.jspf" %>
</body>
</html>
