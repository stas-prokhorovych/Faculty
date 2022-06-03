<%@include file="/jspf/header.jspf"%>

<html>
<head>
    <title>Faculty</title>
    <%@include file="/jspf/head.jspf"%>
</head>
<body>
    <div>Name<c:out value="${requestScope.course.name}"/></div>
    <br />

    <form method="post" action="<c:url value='/update-course'/>">
        <label>New name: <input type="text" name="name" /></label><br>
        <input type="number" hidden name="id" value="${requestScope.course.id}"/>
        <input type="submit" value="Ok" name="Ok"><br>
    </form>
</body>
</html>
