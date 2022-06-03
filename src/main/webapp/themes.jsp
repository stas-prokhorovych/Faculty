<%@include file="/jspf/header.jspf"%>

<html>
<head>
    <title>Title</title>
    <%@include file="/jspf/head.jspf"%>
</head>
<body>
    <%@include file="/jspf/navbar.jspf"%>
    <h1>Please choose course theme</h1>
    <form action="find-courses">
        <label for="theme">Choose courses theme:</label>
        <select name="theme" id="theme">
            <c:forEach items="${themes}" var="theme">
                ${theme}<br>
                <option value="${theme}">${theme}</option>
            </c:forEach>
        </select>
        <br><br>
        <input type="submit" value="Submit">
    </form>
    <%@include file="/jspf/footer.jspf"%>
</body>
</html>
