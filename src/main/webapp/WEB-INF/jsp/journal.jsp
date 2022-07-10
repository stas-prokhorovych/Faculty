<%@include file="/WEB-INF/jspf/header.jspf"%>

<html>
<head>
    <title><fmt:message key="journal.title"/></title>
    <%@include file="/WEB-INF/jspf/head.jspf"%>
</head>
<body>
    <%@include file="/WEB-INF/jspf/navbar.jspf"%>

    <c:if test="${dataError != null}">
        <div class="card-body p-5 text-center">
            <div id="dataError" class="data-error">${dataError}</div>
            <br>
        </div>
    </c:if>

    <h2 class="center"><fmt:message key="journal.open.courses"/></h2>
    <div class="row justify-content-center">
        <div class="row col-md-8">
            <table class="table table-bordered table-sm">
                <thead class="thead-light">
                <tr>
                    <th><fmt:message key="journal.courses.name"/></th>
                    <th><fmt:message key="journal.courses.theme"/></th>
                    <th><fmt:message key="journal.courses.start.date"/></th>
                    <th><fmt:message key="journal.courses.end.date"/></th>
                    <th><fmt:message key="journal.courses.duration.in.days"/></th>
                    <th><fmt:message key="journal.teacher.option"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="course" items="${openForRegCourses}">
                    <tr>
                        <td>${course.name}</td>
                        <td>${course.theme}</td>
                        <td><mytag:dateFormatTag localDateTime="${course.startDate}"/></td>
                        <td><mytag:dateFormatTag localDateTime="${course.endDate}"/></td>
                        <td>${course.durationInDays}</td>
                        <td>
                            <form method="post" action="controller?command=START_COURSE">
                                <input type="hidden" name="command" value="START_COURSE">
                                <input type="number" hidden name="teacher-id" value="${sessionScope.id}"/>
                                <input type="number" hidden name="course-id" value="${course.id}"/>
                                <input type="submit" name="enroll" value="<fmt:message key="journal.start.course"/>"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <h2 class="center"><fmt:message key="journal.in.progress.courses"/></h2>
    <div class="row justify-content-center">
        <div class="row col-md-8">
            <table class="table table-bordered table-sm">
                <thead class="thead-light">
                <tr>
                    <th><fmt:message key="journal.courses.name"/></th>
                    <th><fmt:message key="journal.courses.theme"/></th>
                    <th><fmt:message key="journal.courses.start.date"/></th>
                    <th><fmt:message key="journal.courses.end.date"/></th>
                    <th><fmt:message key="journal.courses.duration.in.days"/></th>
                    <th><fmt:message key="journal.teacher.option"/></th>
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
                        <td>
                            <form method="POST" action="controller?command=SHOW_GRADUATES">
                                <input type="hidden" name="command" value="SHOW_GRADUATES">
                                <input type="number" hidden name="teacher-id" value="${sessionScope.id}"/>
                                <input type="number" hidden name="course-id" value="${course.id}"/>
                                <input type="submit" name="enroll" value="<fmt:message key="journal.end.course"/>"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <h2 class="center"><fmt:message key="journal.finished.courses"/></h2>
    <div class="row justify-content-center">
        <div class="row col-md-8">
            <table class="table table-bordered table-sm">
                <thead class="thead-light">
                <tr>
                    <th><fmt:message key="journal.courses.name"/></th>
                    <th><fmt:message key="journal.courses.theme"/></th>
                    <th><fmt:message key="journal.courses.start.date"/></th>
                    <th><fmt:message key="journal.courses.end.date"/></th>
                    <th><fmt:message key="journal.courses.duration.in.days"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="course" items="${finishedCourses}">
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

    <%@include file="/WEB-INF/jspf/bootstrapScripts.jspf"%>
</body>
</html>
