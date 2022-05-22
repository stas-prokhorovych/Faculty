<%--
  Created by IntelliJ IDEA.
  User: Stas
  Date: 5/15/2022
  Time: 12:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Faculty</title>
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
