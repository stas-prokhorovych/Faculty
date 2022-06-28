<%@include file="/WEB-INF/jspf/header.jspf"%>

<html>
<head>
    <title><fmt:message key="error.title"/></title>
    <%@include file="/WEB-INF/jspf/head.jspf"%>
</head>
<body>
    <%@include file="/WEB-INF/jspf/navbar.jspf" %>

    <div class="h-100 d-flex align-items-center justify-content-center">
        <h1><fmt:message key="error.hint"/></h1><br>
        <a href="controller?command=GOTO_HOME_PAGE" class="btn  btn-secondary fw-bold border-white" style=""><fmt:message key="navbar.home"/></a>
    </div>
</body>
</html>
