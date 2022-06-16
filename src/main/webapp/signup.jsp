<%@include file="/jspf/header.jspf" %>

<html>
<head>
    <title><fmt:message key="signup.register"/></title>
    <%@include file="/jspf/head.jspf" %>
</head>
<body class="form-page">
    <%@include file="/jspf/navbar.jspf" %>

    <form id="form" action="controller?command=REGISTER" method="post">
        <section class="vh-100">
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                        <div class="card shadow-2-strong" style="border-radius: 1rem;">
                            <div class="card-body p-5 text-center">

                                <h3 class="mb-4"><fmt:message key="signup.signup"/></h3>

                                <div class="form-outline mb-4">
                                    <label class="form-label" for="login"><strong><fmt:message key="signup.login"/></strong></label>
                                    <input type="text" id="login" name="login" class="form-control form-control-lg"/>
                                    <c:if test="${loginError != null}">
                                        <div id="loginError" class="error">${loginError}</div>
                                    </c:if>
                                </div>

                                <div class="form-outline mb-4">
                                    <label class="form-label" for="password"><strong><fmt:message key="signup.password"/></strong></label>
                                    <input type="password" id="password" name="password" class="form-control form-control-lg"/>
                                    <c:if test="${passwordError != null}">
                                        <div id="passwordError" class="error">${passwordError}</div>
                                    </c:if>
                                </div>

                                <div class="form-outline mb-4">
                                    <label class="form-label" for="password-repeat"><strong><fmt:message key="signup.repeat.password"/></strong></label>
                                    <input type="password" id="password-repeat" name="password-repeat" class="form-control form-control-lg"/>
                                    <c:if test="${repeatPasswordError != null}">
                                        <div id="repeatPasswordError" class="error">${repeatPasswordError}</div>
                                    </c:if>
                                </div>

                                <div class="form-outline mb-4">
                                    <label class="form-label" for="email"><strong><fmt:message key="signup.email"/></strong></label>
                                    <input type="email" id="email" name="email" class="form-control form-control-lg"/>
                                    <c:if test="${emailError != null}">
                                        <div id="emailError" class="error">${emailError}</div>
                                    </c:if>
                                </div>

                                <div class="form-outline mb-4">
                                    <label class="form-label" for="first-name"><strong><fmt:message key="signup.first.name"/></strong></label>
                                    <input type="text" id="first-name" name="first-name" class="form-control form-control-lg"/>
                                    <div id="first-name-error" class="error"></div>
                                </div>

                                <div class="form-outline mb-4">
                                    <label class="form-label" for="last-name"><strong><fmt:message key="signup.last.name"/></strong></label>
                                    <input type="text" id="last-name" name="last-name"  class="form-control form-control-lg"/>
                                    <div id="last-name-error" class="error"></div>
                                </div>

                                <div class="form-outline mb-4">
                                    <label class="form-label" for="phone"><strong><fmt:message key="signup.phone"/></strong></label>
                                    <input type="text" id="phone" name="phone"  class="form-control form-control-lg"/>
                                    <div id="phone-error" class="error"></div>
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
    <script src="js/captchaCheck.js"/>

    <%@include file="/jspf/bootstrapScripts.jspf" %>
</body>
</html>
