<%@include file="/jspf/header.jspf"%>

<html>
<head>
    <title>Error</title>
    <%@include file="/jspf/head.jspf"%>
</head>
<body>
    <p><%=request.getAttribute("error")%></p>
</body>
</html>
