<%@include file="/jspf/header.jspf" %>

<html>
<head>
    <title>Faculty</title>
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

                            <h3 class="mb-4">Sign up</h3>

                            <div class="form-outline mb-4">
                                <input type="text" id="login" name="login" class="form-control form-control-lg"/>
                                <label class="form-label" for="login"><fmt:message key="page.login.title"/></label>
                                <div id="login-error" class="error"></div>
                            </div>

                            <div class="form-outline mb-4">
                                <input type="password" id="password" name="password" class="form-control form-control-lg"/>
                                <label class="form-label" for="password"><fmt:message key="page.login.password.title"/></label>
                                <div id="password-error" class="error"></div>
                            </div>

                            <div class="form-outline mb-4">
                                <input type="password" id="password-repeat" name="password-repeat" class="form-control form-control-lg"/>
                                <label class="form-label" for="password-repeat">Repeat password</label>
                                <div id="password-repeat-error" class="error"></div>
                            </div>

                            <div class="form-outline mb-4">
                                <input type="email" id="email" name="email" class="form-control form-control-lg"/>
                                <label class="form-label" for="email">Email</label>
                                <div id="email-error" class="error"></div>
                            </div>

                            <div class="form-outline mb-4">
                                <input type="text" id="first-name" name="first-name" class="form-control form-control-lg"/>
                                <label class="form-label" for="first-name">First name</label>
                                <div id="first-name-error" class="error"></div>
                            </div>

                            <div class="form-outline mb-4">
                                <input type="text" id="last-name" name="last-name"  class="form-control form-control-lg"/>
                                <label class="form-label" for="last-name">Last name</label>
                                <div id="last-name-error" class="error"></div>
                            </div>

                            <div class="form-outline mb-4">
                                <input type="text" id="phone" name="phone"  class="form-control form-control-lg"/>
                                <label class="form-label" for="phone">Phone</label>
                                <div id="phone-error" class="error"></div>
                            </div>

                            <button class="btn btn-primary btn-lg btn-block" type="submit">Sign up</button>


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
