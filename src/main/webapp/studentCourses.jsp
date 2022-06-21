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
                            <td>${course.name}</td>
                            <td>${course.theme}</td>
                            <td><mytag:dateFormatTag localDateTime="${course.startDate}"/></td>
                            <td><mytag:dateFormatTag localDateTime="${course.endDate}"/></td>
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
                        <td>${course.name}</td>
                        <td>${course.theme}</td>
                        <td><mytag:dateFormatTag localDateTime="${course.startDate}"/></td>
                        <td><mytag:dateFormatTag localDateTime="${course.endDate}"/></td>
                        <td>${course.durationInDays}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <h2 class="center">Finished courses</h2>
    <div class="row justify-content-center">
        <div class="row col-md-8">
            <table class="table table-bordered table-sm">
                <thead class="thead-light">
                <tr>
                    <th>Name</th>
                    <th>Theme</th>
                    <th>Start date</th>
                    <th>End date</th>
                    <th>Duration in days</th>
                    <th>Mark points</th>
                    <th>Mark code</th>
                    <th>Mark explanation</th>
                </tr>
                </thead>
                <tbody>
                <c:set var="index" value="0"/>
                <c:forEach var="course" items="${finishedCourses}">
                    <tr>
                        <td>${course.name}</td>
                        <td>${course.theme}</td>
                        <td><mytag:dateFormatTag localDateTime="${course.startDate}"/></td>
                        <td><mytag:dateFormatTag localDateTime="${course.endDate}"/></td>
                        <td>${course.durationInDays}</td>
                        <c:forEach var="journal" items="${journalInfo}" varStatus="loop">
                            <c:if test="${loop.index eq index}">
                                <td>${journal.markPoints}</td>
                                <td>${journal.markCode}</td>
                                <td>${journal.markExplanation}</td>
                            </c:if>
                        </c:forEach>
                    </tr>
                    <c:set var="index" value="${index + 1}"/>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>


    <%@include file="/jspf/bootstrapScripts.jspf" %>
</body>
</html>
