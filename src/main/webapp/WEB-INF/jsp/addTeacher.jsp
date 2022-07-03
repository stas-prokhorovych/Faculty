<%@include file="/WEB-INF/jspf/header.jspf"%>

<html>
<head>
    <title><fmt:message key="add.teacher.title"/></title>
    <%@include file="/WEB-INF/jspf/head.jspf"%>
</head>
<body class="form-page">
    <%@include file="/WEB-INF/jspf/navbar.jspf"%>

    <form id="form" action="controller?command=CREATE_TEACHER" method="post">
        <section class="vh-100">
            <div class="container py-5 h-10">
                <div class="row d-flex justify-content-center align-items-center h-90">
                    <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                        <div class="card shadow-2-strong" style="border-radius: 1rem;">
                            <div class="card-body p-5 text-center">

                                <h3 class="mb-4"><fmt:message key="add.teacher.create"/></h3>

                                <div class="form-outline mb-4">
                                    <label for="users"><fmt:message key="add.teacher.users"/></label>
                                            <select name="students" id="users" required>
                                                <c:forEach items="${students}" var="student">
                                                    <option value="${student.id}">${student.firstName} ${student.lastName}</option>
                                                </c:forEach>
                                            </select>
                                </div>

                                <div class="form-outline mb-4">
                                    <label for="choose-course"><fmt:message key="add.teacher.courses"/></label>
                                        <select name="courses" id="choose-course" required>
                                            <c:forEach items="${courses}" var="course">
                                                <option value="${course.id}">${course.name}</option>
                                            </c:forEach>
                                        </select>
                                </div>

                                <button class="btn btn-primary btn-lg btn-block" type="submit"><fmt:message key="add.teacher.finish"/></button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </form>


    <form id="form" action="controller?command=ASSIGN_TEACHER_TO_COURSE" method="post">
        <section class="vh-100">
            <div class="container py-5 h-10">
                <div class="row d-flex justify-content-center align-items-center h-90">
                    <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                        <div class="card shadow-2-strong" style="border-radius: 1rem;">
                            <div class="card-body p-5 text-center">

                                <h3 class="mb-4"><fmt:message key="add.teacher.assign"/></h3>
                                <c:if test="${dataError != null}">
                                    <div id="commonError" class="data-error">${dataError}</div>
                                    <br>
                                </c:if>

                                <div class="form-outline mb-4">
                                    <label for="teachers"><fmt:message key="add.teacher.teachers"/></label>
                                    <select name="teachers" id="teachers" required>
                                        <c:forEach items="${teachers}" var="teacher">
                                            <option value="${teacher.id}">${teacher.firstName} ${teacher.lastName}</option>
                                        </c:forEach>
                                    </select>
                                    <c:if test="${loginError != null}">
                                        <div id="loginError" class="error">${loginError}</div>
                                    </c:if>
                                </div>

                                <div class="form-outline mb-4">
                                    <label for="courses"><fmt:message key="add.teacher.courses"/></label>
                                    <select name="courses" id="courses" required>
                                        <c:forEach items="${courses}" var="course">
                                            <option value="${course.id}">${course.name}</option>
                                        </c:forEach>
                                    </select>
                                    <c:if test="${passwordError != null}">
                                        <div id="passwordError" class="error">${passwordError}</div>
                                    </c:if>
                                </div>

                                <button class="btn btn-primary btn-lg btn-block" type="submit"><fmt:message key="add.teacher.finish"/></button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </form>

    <%@include file="/WEB-INF/jspf/bootstrapScripts.jspf"%>
</body>
</html>
