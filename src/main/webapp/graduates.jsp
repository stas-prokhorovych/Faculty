<%@include file="/jspf/header.jspf"%>

<html>
<head>
    <title>Graduates</title>
    <%@include file="/jspf/head.jspf"%>
</head>
<body>
    <%@include file="/jspf/navbar.jspf"%>

    <h2 class="center">Congratulations with end of course.<br> Please enter marks for your students</h2>

    <div class="row justify-content-center">
        <form method="get" action="<c:url value='/controller'/>">
            <input type="hidden" name="command" value="END_COURSE">
            <input type="number" hidden name="courseId" value="${courseId}"/>


            <div class="row col-md-6">
                <table class="table table-bordered table-sm">
                    <thead class="thead-light">
                    <tr>
                        <th>Login</th>
                        <th>Email</th>
                        <th>First name</th>
                        <th>Last name</th>
                        <th>MARK</th>
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

    <%@include file="/jspf/bootstrapScripts.jspf"%>
</body>
</html>
