<%@include file="/jspf/header.jspf"%>

<html>
<head>
    <title>Add teacher</title>
    <%@include file="/jspf/head.jspf"%>
</head>
<body>
    <%@include file="/jspf/navbar.jspf"%>

    <h3>Select user to register him as teacher</h3>
    <form action="controller">
        <input type="hidden" name="command" value="ADD_TEACHER">
        <label for="students">Users:</label>
        <select name="students" id="students">
            <c:forEach items="${students}" var="student">
                <option value="${student.firstName}">${student.firstName}</option>
            </c:forEach>
        </select>
        <br>

        <label for="courses">Courses:</label>
        <select name="courses" id="courses">
            <c:forEach items="${courses}" var="course">
                <option value="${course.name}">${course.name}</option>
            </c:forEach>
        </select>
        <br>
        <input type="submit" value="Apply">

    </form>

    <%@include file="/jspf/bootstrapScripts.jspf"%>
</body>
</html>
