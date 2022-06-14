<%@include file="/jspf/header.jspf"%>

<html>
<head>
    <title>Faculty</title>
    <%@include file="/jspf/head.jspf"%>
</head>
<body>
    <div>Name<c:out value="${param.name}"/></div>
    <br />

    <form method="post" action="<c:url value='controller?command=UPDATE_COURSE'/>">
        <label>New name: <input type="text" name="name" /></label><br>
        <input type="number" hidden name="id" value="${param.id}"/>
        <input type="submit" value="Ok" name="Ok"><br>
    </form>
</body>
</html>
