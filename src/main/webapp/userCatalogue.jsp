<%@include file="/jspf/header.jspf"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<html>
<head>
    <title>User Catalogue</title>
    <%@include file="/jspf/head.jspf"%>
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
                        <tags:userInfoTable
                                login="${student.login}"
                                email="${student.email}"
                                firstName="${student.firstName}"
                                lastName="${student.lastName}"
                        />
                        <c:choose>
                            <c:when test="${student.userAccess == true}">
                                <td>
                                    <form method="post" action="<c:url value='/controller'/>">
                                        <input type="hidden" name="command" value="BLOCK_USER">
                                        <input type="number" hidden name="id" value="${student.id}"/>
                                        <input class="btn btn-danger" type="submit" name="block" value="Block"/>
                                    </form>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td>
                                    <form method="post" action="<c:url value='/controller'/>">
                                        <input type="hidden" name="command" value="UNBLOCK_USER">
                                        <input type="number" hidden name="id" value="${student.id}"/>
                                        <input class="btn btn-success" type="submit" name="block" value="Unblock"/>
                                    </form>
                                </td>
                            </c:otherwise>
                        </c:choose>

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
                        <tags:userInfoTable
                                login="${teacher.login}"
                                email="${teacher.email}"
                                firstName="${teacher.firstName}"
                                lastName="${teacher.lastName}"
                        />
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>
    </div>
    <br>
    <br>

    <%@include file="/jspf/bootstrapScripts.jspf"%>
</body>
</html>
