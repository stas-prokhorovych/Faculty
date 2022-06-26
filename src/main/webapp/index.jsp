<%@include file="/jspf/header.jspf"%>

<html class="main-page">
<head>
    <title><fmt:message key="index.title"/></title>
    <%@include file="/jspf/head.jspf"%>
</head>
<body class="main-page">
    <%@include file="/jspf/navbar.jspf"%>

    <div class="position-relative overflow-hidden p-3 p-md-5 m-md-3 text-center bg-light">
        <div class="col-m d-5 p-lg-5 mx-auto my-5">
            <h1 class="display-4 font-weight-normal"><fmt:message key="index.courses"/></h1>
            <p class="lead font-weight-normal"><fmt:message key="index.greeting"/></p>

        </div>
        <div class="product-device shadow-sm d-none d-md-block"></div>
        <div class="product-device product-device-2 shadow-sm d-none d-md-block"></div>
    </div>

    <div class="position-relative overflow-hidden p-3 p-md-5 m-md-3 text-center bg-light">
        <div class="col-md-5 p-lg-5 mx-auto my-5"></div>
        <div class="col-md-5 p-lg-3 mx-auto my-5"></div>
        <div class="product-device shadow-sm d-none d-md-block"></div>
        <div class="product-device product-device-2 shadow-sm d-none d-md-block"></div>
    </div>

    <%@include file="/jspf/bootstrapScripts.jspf"%>
</body>
</html>
