<%@include file="/jspf/header.jspf" %>

<html>
<head>
    <title><fmt:message key="login.login"/></title>
    <%@include file="/jspf/head.jspf" %>
<%--    <script defer src="js/validation.js"></script>--%>
</head>

<body class="form-page">
<%@include file="/jspf/navbar.jspf" %>
<form id="form" action="controller?command=LOGIN" method="post">
    <section class="vh-100">
        <div class="container py-5 h-100">
            <div class="row d-flex justify-content-center align-items-center h-90">
                <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                    <div class="card shadow-2-strong" style="border-radius: 1rem;">
                        <div class="card-body p-5 text-center">

                            <h3 class="mb-4"><fmt:message key="login.login"/></h3>
                            <c:if test="${dataError != null}">
                                <div id="commonError" class="data-error">${dataError}</div>
                                <br>
                            </c:if>

                            <div class="form-outline mb-4">
                                <label class="form-label" for="login"><strong><fmt:message key="login.login"/></strong></label>
                                <input type="text" id="login" name="login" class="form-control form-control-lg"  <c:if test="${validLogin != null}"> value="${validLogin}"</c:if>/>
                                <c:if test="${loginError != null}">
                                    <div id="loginError" class="error">${loginError}</div>
                                </c:if>
                            </div>

                            <div class="form-outline mb-4">
                                <label class="form-label" for="password"><strong><fmt:message key="login.password"/></strong></label>
                                <input type="password" id="password" name="password" class="form-control form-control-lg" <c:if test="${validPassword != null}"> value="${validPassword}"</c:if>/>
                                <c:if test="${passwordError != null}">
                                    <div id="passwordError" class="error">${passwordError}</div>
                                </c:if>
                            </div>

                            <button class="btn btn-primary btn-lg btn-block" type="submit"><fmt:message key="login.login"/></button>

                            <hr class="my-4">
                            <div class="col">
                                <a href="forgotPassword.jsp"><fmt:message key="login.forget.password"/></a>
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
