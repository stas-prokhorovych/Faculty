<%@include file="/WEB-INF/jspf/header.jspf" %>

<html>
<head>
    <title><fmt:message key="signup.register"/></title>
    <%@include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body class="form-page">
    <%@include file="/WEB-INF/jspf/navbar.jspf" %>

    <form id="form" action="controller?command=REGISTER" method="post">
        <section class="vh-100">
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                        <div class="card shadow-2-strong" style="border-radius: 1rem;">
                            <div class="card-body p-5 text-center">

                                <h3 class="mb-4"><fmt:message key="signup.signup"/></h3>
                                <c:if test="${dataError != null}">
                                    <div id="dataError" class="data-error">${dataError}</div>
                                    <br>
                                </c:if>

                                <div class="form-outline mb-4">
                                    <label class="form-label" for="login-signup"><strong><fmt:message key="signup.login"/></strong></label>
                                    <input type="text" id="login-signup" name="login" class="form-control form-control-lg" <c:if test="${validLogin != null}"> value="${validLogin}"</c:if>/>
                                    <c:if test="${loginError != null}">
                                        <div id="loginError" class="error">${loginError}</div>
                                    </c:if>
                                </div>

                                <div class="form-outline mb-4">
                                    <label class="form-label" for="password-signup"><strong><fmt:message key="signup.password"/></strong></label>
                                    <input type="password" id="password-signup" name="password" class="form-control form-control-lg" <c:if test="${validPassword != null}"> value="${validPassword}"</c:if>/>
                                    <c:if test="${passwordError != null}">
                                        <div id="passwordError" class="error">${passwordError}</div>
                                    </c:if>
                                </div>

                                <div class="form-outline mb-4">
                                    <label class="form-label" for="password-repeat"><strong><fmt:message key="signup.repeat.password"/></strong></label>
                                    <input type="password" id="password-repeat" name="password-repeat" class="form-control form-control-lg" <c:if test="${validPasswordRepeat != null}"> value="${validPasswordRepeat}"</c:if>/>
                                    <c:if test="${repeatPasswordError != null}">
                                        <div id="repeatPasswordError" class="error">${repeatPasswordError}</div>
                                    </c:if>
                                </div>

                                <div class="form-outline mb-4">
                                    <label class="form-label" for="email"><strong><fmt:message key="signup.email"/></strong></label>
                                    <input type="email" id="email" name="email" class="form-control form-control-lg" <c:if test="${validEmail != null}"> value="${validEmail}"</c:if>/>
                                    <c:if test="${emailError != null}">
                                        <div id="emailError" class="error">${emailError}</div>
                                    </c:if>
                                </div>

                                <div class="form-outline mb-4">
                                    <label class="form-label" for="first-name"><strong><fmt:message key="signup.first.name"/></strong></label>
                                    <input type="text" id="first-name" name="first-name" class="form-control form-control-lg" <c:if test="${validFirstName != null}"> value="${validFirstName}"</c:if>/>
                                    <c:if test="${firstNameError != null}">
                                        <div id="firstNameError" class="error">${firstNameError}</div>
                                    </c:if>
                                </div>

                                <div class="form-outline mb-4">
                                    <label class="form-label" for="last-name"><strong><fmt:message key="signup.last.name"/></strong></label>
                                    <input type="text" id="last-name" name="last-name"  class="form-control form-control-lg" <c:if test="${validLastName != null}"> value="${validLastName}"</c:if>/>
                                    <c:if test="${lastNameError != null}">
                                        <div id="lastNameError" class="error">${lastNameError}</div>
                                    </c:if>
                                </div>

                                <div class="form-outline mb-4">
                                    <label class="form-label" for="phone"><strong><fmt:message key="signup.phone"/></strong></label>
                                    <input type="text" id="phone" name="phone"  class="form-control form-control-lg" <c:if test="${validPhone != null}"> value="${validPhone}"</c:if>/>
                                    <c:if test="${phoneError != null}">
                                        <div id="phoneError" class="error">${phoneError}</div>
                                    </c:if>
                                </div>

                                <div class="g-recaptcha" data-sitekey="6Lc_oHUgAAAAAG0P3EUS26BlHURZT1mYJGGd5p3X" ></div>
                                <div id="captcha-error" class="error"></div>
                                <br>

                                <button class="btn btn-primary btn-lg btn-block" type="submit"><fmt:message key="signup.signup"/></button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </form>

    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    <script type="text/javascript" src='<c:url value="/js/captchaCheck.js"/>'></script>

    <%@include file="/WEB-INF/jspf/bootstrapScripts.jspf" %>
</body>
</html>
