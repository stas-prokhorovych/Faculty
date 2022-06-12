<%@include file="/jspf/header.jspf" %>

<html>
<head>
    <title><fmt:message key="page.login.title"/></title>
    <%@include file="/jspf/head.jspf" %>
<%--    <script defer src="js/validation.js"></script>--%>
    <style>
        .error {
            color: red;
            font-size: smaller;
        }
    </style>
</head>

<body class="form-page">
<%@include file="/jspf/navbar.jspf" %>
<form id="form" action="controller?command=LOGIN" method="post">
    <section class="vh-100">
        <div class="container py-5 h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                    <div class="card shadow-2-strong" style="border-radius: 1rem;">
                        <div class="card-body p-5 text-center">

                            <h3 class="mb-4"><fmt:message key="page.login.title"/></h3>

                            <div class="form-outline mb-4">
                                <label class="form-label" for="login"><strong><fmt:message key="page.login.title"/></strong></label>
                                <input type="text" id="login" name="login" class="form-control form-control-lg"/>
                                <c:choose>
                                    <c:when test="${loginError != null}">
                                        <div id="login-error" class="error">${loginError}</div>
                                    </c:when>
                                </c:choose>
                            </div>

                            <div class="form-outline mb-4">
                                <label class="form-label" for="password"><strong><fmt:message key="page.login.password.title"/></strong></label>
                                <input type="password" id="password" name="password" class="form-control form-control-lg"/>
                                <c:choose>
                                    <c:when test="${passwordError != null}">
                                        <div id="login-error" class="error">${passwordError}</div>
                                    </c:when>
                                </c:choose>
                            </div>

                            <button class="btn btn-primary btn-lg btn-block" type="submit"><fmt:message key="page.login.title"/></button>

                            <hr class="my-4">
                            <div class="col">
                                <a href="forgotPassword.jsp">Forgot password?</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</form>

<%@include file="/jspf/bootstrapScripts.jspf" %>
</body>
</html>
