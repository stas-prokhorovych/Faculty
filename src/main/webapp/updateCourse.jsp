<%@include file="/jspf/header.jspf"%>

<html>
<head>
    <title>Update course</title>
    <%@include file="/jspf/head.jspf"%>
</head>
<body class="form-page">
    <%@include file="/jspf/navbar.jspf"%>

    <form id="form" action="controller?command=UPDATE_COURSE" method="post">
        <section class="vh-100">
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-90">
                    <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                        <div class="card shadow-2-strong" style="border-radius: 1rem;">
                            <div class="card-body p-5 text-center">

                                <h3 class="mb-4">Update course</h3>
                                <c:if test="${dataError != null}">
                                    <div id="commonError" class="data-error">${dataError}</div>
                                    <br>
                                </c:if>

                                <div class="form-outline mb-4">
                                    <label class="form-label" for="name"><strong>Name</strong>(current: ${course.name})</label>
                                    <input type="text" id="name" name="name" class="form-control form-control-lg" <c:if test="${validLogin != null}"> value="${validLogin}"</c:if>/>
                                    <c:if test="${nameError != null}">
                                        <div id="nameError" class="error">${nameError}</div>
                                    </c:if>
                                </div>

                                <div class="form-outline mb-4">
                                    <label class="form-label" for="theme"><strong>Theme*</strong>(current: ${course.theme})</label>
                                    <input type="text" id="theme" name="theme" class="form-control form-control-lg" <c:if test="${validPassword != null}"> value="${validPassword}"</c:if>/>
                                    <c:if test="${themeError != null}">
                                        <div id="themeError" class="error">${themeError}</div>
                                    </c:if>
                                </div>

                                <div class="form-outline mb-4">
                                    <label class="form-label" for="start-date"><strong>Start date*</strong>(current: ${course.startDate})</label>
                                    <input type="datetime-local" id="start-date" name="start-date" class="form-control form-control-lg" <c:if test="${validPassword != null}"> value="${validPassword}"</c:if>/>
                                    <c:if test="${startDateError != null}">
                                        <div id="startDateError" class="error">${startDateError}</div>
                                    </c:if>
                                </div>

                                <div class="form-outline mb-4">
                                    <label class="form-label" for="end-date"><strong>End date*</strong>(current: ${course.endDate})</label>
                                    <input type="datetime-local" id="end-date" name="end-date" class="form-control form-control-lg" <c:if test="${validPassword != null}"> value="${validPassword}"</c:if>/>
                                    <c:if test="${endDateError != null}">
                                        <div id="endDateError" class="error">${endDateError}</div>
                                    </c:if>
                                </div>

                                <div class="form-outline mb-4">
                                    <label for="id-lecturer"><strong>Teacher</strong></label>
                                    <select name="id-lecturer" id="id-lecturer" class="form-control form-control-lg">
                                        <option value="">Select teacher if you want</option>
                                        <c:forEach items="${teacherForForm}" var="teacher">
                                            <option value="${teacher.id}">${teacher.firstName} ${teacher.lastName}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <input type="number" hidden name="id" value="${course.id}"/>
                                <button class="btn btn-primary btn-lg btn-block" type="submit">Add</button>
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
