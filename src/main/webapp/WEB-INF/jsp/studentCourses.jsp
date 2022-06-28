<%@include file="/WEB-INF/jspf/header.jspf" %>

<html>
<head>
    <title><fmt:message key="student.courses.title"/></title>
    <%@include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
    <%@include file="/WEB-INF/jspf/navbar.jspf" %>

    <h2 class="center"><fmt:message key="student.courses.registered.courses"/></h2>
    <div class="row justify-content-center">
        <div class="row col-md-8">
            <table class="table table-bordered table-sm">
                <thead class="thead-light">
                <tr>
                    <th><fmt:message key="student.courses.name"/></th>
                    <th><fmt:message key="student.courses.theme"/></th>
                    <th><fmt:message key="student.courses.start.date"/></th>
                    <th><fmt:message key="student.courses.end.date"/></th>
                    <th><fmt:message key="student.courses.duration.in.days"/></th>
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

    <h2 class="center"><fmt:message key="student.courses.in.progress.courses"/></h2>
    <div class="row justify-content-center">
        <div class="row col-md-8">
            <table class="table table-bordered table-sm">
                <thead class="thead-light">
                <tr>
                    <th><fmt:message key="student.courses.name"/></th>
                    <th><fmt:message key="student.courses.theme"/></th>
                    <th><fmt:message key="student.courses.start.date"/></th>
                    <th><fmt:message key="student.courses.end.date"/></th>
                    <th><fmt:message key="student.courses.duration.in.days"/></th>
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

    <h2 class="center"><fmt:message key="student.courses.finished.courses"/></h2>
    <div class="row justify-content-center">
        <div class="row col-md-8">
            <table class="table table-bordered table-sm">
                <thead class="thead-light">
                <tr>
                    <th><fmt:message key="student.courses.name"/></th>
                    <th><fmt:message key="student.courses.theme"/></th>
                    <th><fmt:message key="student.courses.start.date"/></th>
                    <th><fmt:message key="student.courses.end.date"/></th>
                    <th><fmt:message key="student.courses.duration.in.days"/></th>
                    <th><fmt:message key="student.courses.mark.points"/></th>
                    <th><fmt:message key="student.courses.mark.code"/></th>
                    <th><fmt:message key="student.courses.mark.explanation"/></th>
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

    <%@include file="/WEB-INF/jspf/bootstrapScripts.jspf" %>
</body>
</html>
