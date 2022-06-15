<%@include file="/jspf/header.jspf"%>

<html>
<head>
    <title>Title</title>
    <%@include file="/jspf/head.jspf"%>
    <link rel="stylesheet" type="text/css" href="css/styling.css">
</head>
<body  class="form-page">
    <%@include file="/jspf/navbar.jspf"%>

    <form id="form" action="login" method="post">
        <section class="vh-100">
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                        <div class="card shadow-2-strong" style="border-radius: 1rem;">
                            <div class="card-body p-5 text-center">
                                <h3 class="mb-5">Password reset</h3>
                                <div class="form-outline mb-4">
                                    <input type="text" id="login" name="login" class="form-control form-control-lg" />
                                    <label class="form-label" for="login">Login</label>
                                    <div id="password-error" class="error"></div>
                                </div>
                                <button class="btn btn-primary btn-lg btn-block" type="submit">Send link to email</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </form>

    <%@include file="/jspf/bootstrapScripts.jspf"%>
</body>
</html>
