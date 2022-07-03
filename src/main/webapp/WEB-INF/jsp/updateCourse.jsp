<%@include file="/WEB-INF/jspf/header.jspf"%>

<html>
<head>
    <title><fmt:message key="update.course.title"/></title>
    <%@include file="/WEB-INF/jspf/head.jspf"%>
</head>
<body class="form-page">
    <%@include file="/WEB-INF/jspf/navbar.jspf"%>

    <form id="form" action="controller?command=UPDATE_COURSE" method="post">
        <section class="vh-100">
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-90">
                    <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                        <div class="card shadow-2-strong" style="border-radius: 1rem;">
                            <div class="card-body p-5 text-center">

                                <h3 class="mb-4"><fmt:message key="update.course.title"/></h3>
                                <c:if test="${dataError != null}">
                                    <div id="commonError" class="data-error">${dataError}</div>
                                    <br>
                                </c:if>

                                <div class="form-outline mb-4">
                                    <label class="form-label" for="name"><strong><fmt:message key="update.course.name"/></strong>(<fmt:message key="update.course.current"/> ${updateCourse.name})</label>
                                    <input type="text" id="name" name="name" class="form-control form-control-lg"
                                        <c:choose>
                                            <c:when test="${validLogin != null}">
                                                value="${validLogin}"
                                            </c:when>
                                            <c:otherwise>
                                                value="${updateCourse.name}"
                                            </c:otherwise>
                                        </c:choose>
                                    />
                                    <c:if test="${nameError != null}">
                                        <div id="nameError" class="error">${nameError}</div>
                                    </c:if>
                                </div>

                                <div class="form-outline mb-4">
                                    <label class="form-label" for="theme"><strong><fmt:message key="update.course.theme"/></strong>(<fmt:message key="update.course.current"/>  ${updateCourse.theme})</label>
                                    <input type="text" id="theme" name="theme" class="form-control form-control-lg"
                                            <c:choose>
                                                <c:when test="${validTheme != null}">
                                                    value="${validTheme}"
                                                </c:when>
                                                <c:otherwise>
                                                    value="${updateCourse.theme}"
                                                </c:otherwise>
                                            </c:choose>
                                    />
                                    <c:if test="${themeError != null}">
                                        <div id="themeError" class="error">${themeError}</div>
                                    </c:if>
                                </div>

                                <div class="form-outline mb-4">
                                    <label class="form-label" for="start-date"><strong><fmt:message key="update.course.start.date"/></strong>(<fmt:message key="update.course.current"/>  <mytag:dateFormatTag localDateTime="${updateCourse.startDate}"/>)</label>
                                    <input type="datetime-local" id="start-date" name="start-date" class="form-control form-control-lg"
                                            <c:choose>
                                                <c:when test="${validStartDate != null}">
                                                    value="${validStartDate}"
                                                </c:when>
                                                <c:otherwise>
                                                    value="${updateCourse.startDate}"
                                                </c:otherwise>
                                            </c:choose>
                                    />
                                    <c:if test="${startDateError != null}">
                                        <div id="startDateError" class="error">${startDateError}</div>
                                    </c:if>
                                </div>

                                <div class="form-outline mb-4">
                                    <label class="form-label" for="end-date"><strong><fmt:message key="update.course.end.date"/></strong>(<fmt:message key="update.course.current"/>  <mytag:dateFormatTag localDateTime="${updateCourse.endDate}"/>)</label>
                                    <input type="datetime-local" id="end-date" name="end-date" class="form-control form-control-lg"
                                            <c:choose>
                                                <c:when test="${validEndDate != null}">
                                                    value="${validEndDate}"
                                                </c:when>
                                                <c:otherwise>
                                                    value="${updateCourse.endDate}"
                                                </c:otherwise>
                                            </c:choose>
                                    />
                                    <c:if test="${endDateError != null}">
                                        <div id="endDateError" class="error">${endDateError}</div>
                                    </c:if>
                                </div>

                                <div class="form-outline mb-4">
                                    <label for="id-lecturer"><strong><fmt:message key="update.course.teacher"/></strong></label>
                                    <select name="id-lecturer" id="id-lecturer" class="form-control form-control-lg">
                                        <option value=""><fmt:message key="update.course.hint"/></option>
                                        <c:forEach items="${updateTeachers}" var="teacher">
                                            <option value="${teacher.id}">${teacher.firstName} ${teacher.lastName}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <input type="number" hidden name="id" value="${updateCourse.id}"/>
                                <input type="text" hidden name="prevCourseName" value="${updateCourse.name}"/>
                                <button class="btn btn-primary btn-lg btn-block" type="submit"><fmt:message key="update.course.add"/> </button>
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
