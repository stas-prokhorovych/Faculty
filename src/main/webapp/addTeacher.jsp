<%@include file="/jspf/header.jspf"%>

<html>
<head>
    <title>Add teacher</title>
    <%@include file="/jspf/head.jspf"%>
</head>
<body class="form-page">
    <%@include file="/jspf/navbar.jspf"%>

<%--    <form action="controller">--%>
<%--        <input type="hidden" name="command" value="ADD_TEACHER">--%>
<%--        <label for="students">Users:</label>--%>
<%--        <select name="students" id="students">--%>
<%--            <c:forEach items="${students}" var="student">--%>
<%--                <option value="${student.firstName}">${student.firstName}</option>--%>
<%--            </c:forEach>--%>
<%--        </select>--%>
<%--        <br>--%>

<%--        <label for="courses">Courses:</label>--%>
<%--        <select name="courses" id="courses">--%>
<%--            <c:forEach items="${courses}" var="course">--%>
<%--                <option value="${course.name}">${course.name}</option>--%>
<%--            </c:forEach>--%>
<%--        </select>--%>
<%--        <br>--%>
<%--        <input type="submit" value="Apply">--%>
<%--    </form>--%>



    <form id="form" action="controller?command=ADD_TEACHER" method="post">
        <section class="vh-100">
            <div class="container py-5 h-10">
                <div class="row d-flex justify-content-center align-items-center h-90">
                    <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                        <div class="card shadow-2-strong" style="border-radius: 1rem;">
                            <div class="card-body p-5 text-center">

                                <h3 class="mb-4">Create teacher and assign to course</h3>
                                <c:if test="${dataError != null}">
                                    <div id="commonError" class="data-error">${dataError}</div>
                                    <br>
                                </c:if>

                                <div class="form-outline mb-4">
                                    <label for="students">Users:</label>
                                            <select name="students" id="students">
                                                <c:forEach items="${students}" var="student">
                                                    <option value="${student.firstName}">${student.firstName}</option>
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
                                                <option value="${course.name}">${course.name}</option>
                                            </c:forEach>
                                        </select>
                                    <c:if test="${passwordError != null}">
                                        <div id="passwordError" class="error">${passwordError}</div>
                                    </c:if>
                                </div>

                                <button class="btn btn-primary btn-lg btn-block" type="submit">Finish </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </form>


    <form id="form" action="controller?command=ADD_TEACHER" method="post">
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
                                    <label for="students">Teachers:</label>
                                    <select name="students" id="students">
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
                                            <option value="${course.name}">${course.name}</option>
                                        </c:forEach>
                                    </select>
                                    <c:if test="${passwordError != null}">
                                        <div id="passwordError" class="error">${passwordError}</div>
                                    </c:if>
                                </div>

                                <button class="btn btn-primary btn-lg btn-block" type="submit">Finish </button>
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
