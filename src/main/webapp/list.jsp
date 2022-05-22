<%--
  Created by IntelliJ IDEA.
  User: Stas
  Date: 5/14/2022
  Time: 10:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Faculty</title>
    <link rel="stylesheet" href="css/styles.css"/>
</head>
<body>
<h1>All courses</h1>
<hr class="divider"/>
<c:forEach var="course" items="${requestScope.courses}">
    <ul>

        <li><c:out value="${course.name}"/></li>

        <form method="post" action="<c:url value='/delete-course'/>">
            <input type="number" hidden name="id" value="${course.id}"/>
            <input type="submit" name="delete" value="Delete"/>
        </form>

        <form method="get" action="<c:url value='/update-course'/>">
            <input type="number" hidden name="id" value="${course.id}"/>
            <input type="submit" value="Update"/>
        </form>

    </ul>
    <hr/>
</c:forEach>

<h1>Create new course</h1>
<form method="post" action="add-course">
    <label><input type="text" name="name"></label>Name<br>
    <label><input type="text" name="theme"></label>Theme<br>
    <label><input type="text" name="duration-in-days"></label>Duration in days<br>
    <label><input type="text" name="id-lecturer"></label>Lecturer id<br>
    <input type="submit" value="Ok" name="Ok"><br>
</form>
</body>
</html>

