<%@include file="/jspf/header.jspf"%>

<html>
<head>
    <title>Title</title>
    <%@include file="/jspf/head.jspf"%>
</head>
<body>
<%@include file="/jspf/navbar.jspf"%>
<h1>Please choose teacher</h1>
<form action="teacher-courses">
    <label for="teacher">Choose teacher:</label>
    <select name="teacher" id="teacher">
        <c:forEach items="${teachers}" var="teacher">
            <option value="${teacher.firstName}${' '}${teacher.lastName}">${teacher.firstName}${' '}${teacher.lastName}</option>
        </c:forEach>
    </select>
    <br><br>
    <input type="submit" value="Submit">
</form>
<%@include file="/jspf/footer.jspf"%>
</body>
</html>