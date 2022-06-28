<%@include file="/WEB-INF/jspf/header.jspf"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<html>
<head>
    <title><fmt:message key="user.catalogue.title"/></title>
    <%@include file="/WEB-INF/jspf/head.jspf"%>
</head>
<body>
    <%@include file="/WEB-INF/jspf/navbar.jspf"%>

    <div class="center">
        <h2 class="center"><fmt:message key="user.catalogue.students"/></h2>
        <form method="post" action="controller?command=PDF_REPORT">
            <input class="btn btn-info" type="submit" name="delete" value="pdf"/>
        </form>
    </div>

    <div class="row justify-content-center">
        <div class="row col-md-6">
            <table class="table table-bordered table-sm">
                <thead class="thead-light">
                <tr>
                    <th><fmt:message key="user.catalogue.login"/></th>
                    <th><fmt:message key="user.catalogue.email"/></th>
                    <th><fmt:message key="user.catalogue.first.name"/></th>
                    <th><fmt:message key="user.catalogue.last.name"/></th>
                    <th><fmt:message key="user.catalogue.admin.option"/></th>
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
                                    <form method="post" action="controller?command=BLOCK_USER">
                                        <input type="number" hidden name="id" value="${student.id}"/>
                                        <input class="btn btn-danger" type="submit" name="block" value="Block"/>
                                    </form>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td>
                                    <form method="post" action="controller?command=UNBLOCK_USER">
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
        <h2 class="center"><fmt:message key="user.catalogue.teachers"/></h2>
        <form method="post" action="controller?command=PDF_REPORT">
            <input class="btn btn-info" type="submit" name="delete" value="pdf"/>
        </form>
    </div>

    <div class="row justify-content-center">
        <div class="row col-md-6">
            <table class="table table-bordered table-sm">
                <thead class="thead-light">
                <tr>
                    <th><fmt:message key="user.catalogue.login"/></th>
                    <th><fmt:message key="user.catalogue.email"/></th>
                    <th><fmt:message key="user.catalogue.first.name"/></th>
                    <th><fmt:message key="user.catalogue.last.name"/></th>
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

    <%@include file="/WEB-INF/jspf/bootstrapScripts.jspf"%>
</body>
</html>
