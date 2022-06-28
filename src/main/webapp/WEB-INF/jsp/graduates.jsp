<%@include file="/WEB-INF/jspf/header.jspf"%>

<html>
<head>
    <title><fmt:message key="graduates.title"/></title>
    <%@include file="/WEB-INF/jspf/head.jspf"%>
</head>
<body>
    <%@include file="/WEB-INF/jspf/navbar.jspf"%>

    <h2 class="center"><fmt:message key="graduates.congradulations"/><br><fmt:message key="graduates.hint"/></h2>

    <div class="row justify-content-center">
        <form method="POST" action="controller?command=END_COURSE">
            <input type="number" hidden name="courseId" value="${courseId}"/>


            <div class="row col-md-6">
                <table class="table table-bordered table-sm">
                    <thead class="thead-light">
                    <tr>
                        <th><fmt:message key="graduates.login"/></th>
                        <th><fmt:message key="graduates.email"/></th>
                        <th><fmt:message key="graduates.first.name"/></th>
                        <th><fmt:message key="graduates.last.name"/></th>
                        <th><fmt:message key="graduates.mark"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="student" items="${graduates}">
                        <tr>
                            <td>${student.login}</td>
                            <td>${student.email}</td>
                            <td>${student.firstName}</td>
                            <td>${student.lastName}</td>
                            <td>
                                <input type="number" hidden name="student-id" value="${student.id}"/>
                                <input type="number" name="mark" min="1" max="100" required/>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <input type="submit" name="enroll" value="Finish"/>
        </form>
    </div>

    <%@include file="/WEB-INF/jspf/bootstrapScripts.jspf"%>
</body>
</html>
