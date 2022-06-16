<%@include file="/jspf/header.jspf"%>

<html>
<head>
    <title>User Catalogue</title>
    <%@include file="/jspf/head.jspf"%>
    <style>
        .image {
            width: 50px;
            height: 50px;
        }

    </style>
</head>
<body>
    <%@include file="/jspf/navbar.jspf"%>

    <div class="center">
        <h2 class="center">Students</h2>
        <form method="get" action="controller">
            <input type="hidden" name="command" value="PDF_REPORT">
            <input class="image" type="image" src="images/pdf.png" alt="Alt text"/>
        </form>
    </div>

    <div class="row justify-content-center">
        <div class="row col-md-6">
            <table class="table table-bordered table-sm">
                <thead class="thead-light">
                <tr>
                    <th>Login</th>
                    <th>Email</th>
                    <th>First name</th>
                    <th>Last name</th>
                    <th>Admin option</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="student" items="${students}">
                    <tr>
                        <td>${student.login}</td>
                        <td>${student.email}</td>
                        <td>${student.firstName}</td>
                        <td>${student.lastName}</td>
                        <td>
                            <form method="post" action="<c:url value='/controller'/>">
                                <input type="hidden" name="command" value="BLOCK_USER">
                                <input type="number" hidden name="id" value="${student.id}"/>
                                <input type="submit" name="block" value="Block"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <div class="center">
        <h2 class="center">Teachers</h2>
        <form method="get" action="controller">
            <input type="hidden" name="command" value="PDF_REPORT">
            <input class="image" type="image" src="images/pdf.png" alt="Alt text"/>
        </form>
    </div>

    <div class="row justify-content-center">
        <div class="row col-md-6">
            <table class="table table-bordered table-sm">
                <thead class="thead-light">
                <tr>
                    <th>Login</th>
                    <th>Email</th>
                    <th>First name</th>
                    <th>Last name</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="teacher" items="${teachers}">
                    <tr>
                        <td>${teacher.login}</td>
                        <td>${teacher.email}</td>
                        <td>${teacher.firstName}</td>
                        <td>${teacher.lastName}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <%@include file="/jspf/bootstrapScripts.jspf"%>
</body>
</html>
