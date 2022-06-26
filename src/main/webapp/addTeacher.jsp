<%@include file="/jspf/header.jspf"%>

<html>
<head>
    <title>Add teacher</title>
    <%@include file="/jspf/head.jspf"%>
</head>
<body class="form-page">
    <%@include file="/jspf/navbar.jspf"%>

    <form id="form" action="controller?command=CREATE_TEACHER" method="post">
        <section class="vh-100">
            <div class="container py-5 h-10">
                <div class="row d-flex justify-content-center align-items-center h-90">
                    <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                        <div class="card shadow-2-strong" style="border-radius: 1rem;">
                            <div class="card-body p-5 text-center">

                                <h3 class="mb-4">Create teacher and assign to course</h3>

                                <div class="form-outline mb-4">
                                    <label for="users">Users:</label>
                                            <select name="students" id="users">
                                                <c:forEach items="${students}" var="student">
                                                    <option value="${student.id}">${student.firstName} ${student.lastName}</option>
                                                </c:forEach>
                                            </select>
                                </div>

                                <div class="form-outline mb-4">
                                    <label for="choose-course">Courses:</label>
                                        <select name="courses" id="choose-course">
                                            <c:forEach items="${courses}" var="course">
                                                <option value="${course.id}">${course.name}</option>
                                            </c:forEach>
                                        </select>
                                </div>

                                <button class="btn btn-primary btn-lg btn-block" type="submit">Finish</button>
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

                                <h3 class="mb-4">Choose teacher and assign to course</h3>
                                <c:if test="${dataError != null}">
                                    <div id="commonError" class="data-error">${dataError}</div>
                                    <br>
                                </c:if>

                                <div class="form-outline mb-4">
                                    <label for="teachers">Teachers:</label>
                                    <select name="teachers" id="teachers">
                                        <c:forEach items="${teachers}" var="teacher">
                                            <option value="${teacher.id}">${teacher.firstName} ${teacher.lastName}</option>
                                        </c:forEach>
                                    </select>
                                    <c:if test="${loginError != null}">
                                        <div id="loginError" class="error">${loginError}</div>
                                    </c:if>
                                </div>

                                <div class="form-outline mb-4">
                                    <label for="courses">Courses:</label>
                                    <select name="courses" id="courses">
                                        <c:forEach items="${courses}" var="course">
                                            <option value="${course.id}">${course.name}</option>
                                        </c:forEach>
                                    </select>
                                    <c:if test="${passwordError != null}">
                                        <div id="passwordError" class="error">${passwordError}</div>
                                    </c:if>
                                </div>

                                <button class="btn btn-primary btn-lg btn-block" type="submit">Finish</button>
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
