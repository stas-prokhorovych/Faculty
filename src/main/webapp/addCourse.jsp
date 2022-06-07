<%@include file="/jspf/header.jspf"%>

<html>
<head>
    <title>Title</title>
    <%@include file="/jspf/head.jspf"%>
</head>
<body>
    <%@include file="/jspf/navbar.jspf"%>
    <h1>Create new course</h1>
    <form method="post" action="add-course">
        <label><input type="text" name="name"></label>Name<br>
        <label><input type="text" name="theme"></label>Theme<br>
        <label><input type="datetime-local" name="start-date"></label>Duration in days<br>
        <label><input type="datetime-local" name="end-date"></label>Duration in days<br>
        <label><input type="text" name="id-lecturer"></label>Lecturer id<br>
        <input type="submit" value="Ok" name="Ok"><br>
    </form>
</body>
</html>
