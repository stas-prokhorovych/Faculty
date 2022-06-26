<%@include file="/jspf/header.jspf"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<html>
<head>
    <title><fmt:message key="catalogue.title"/></title>
    <%@include file="/jspf/head.jspf"%>
</head>
<body>
<%@include file="/jspf/navbar.jspf"%>
<br>
<br>

<c:set var="path" value="controller?command=COURSE_CATALOGUE"/>

<c:set var="themeAttr" value=""/>
<c:if test="${theme != null}">
    <c:set var="themeAttr" value="&theme=${theme}"/>
</c:if>

<c:set var="sortAttr" value=""/>
<c:if test="${sort != null}">
    <c:set var="sortAttr" value="&sort=${sort}"/>
</c:if>

<c:set var="orderAttr" value=""/>
<c:if test="${order != null}">
    <c:set var="orderAttr" value="&order=${order}"/>
</c:if>

<c:set var="recordsPerPageAttr" value=""/>
<c:if test="${recordsPerPage != null}">
    <c:set var="recordsPerPageAttr" value="&recordsPerPage=${recordsPerPage}"/>
</c:if>

    <h3 class="center"><fmt:message key="catalogue.filter"/></h3>
    <div class="row justify-content-center">
    <form action="controller">
        <input type="hidden" name="command" value="COURSE_CATALOGUE">

        <div class="row col-md-13">
            <table class="table table-bordered table-sm">
                <thead class="thead-light">
                    <tr>
                        <th><fmt:message key="catalogue.theme"/></th>
                        <th><fmt:message key="catalogue.teacher"/></th>
                        <th><fmt:message key="catalogue.sort"/></th>
                        <th><fmt:message key="catalogue.order"/></th>
                        <th><fmt:message key="catalogue.records"/></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            <label for="theme"></label>
                            <select name="theme" id="theme">
                                <option value="" selected disabled hidden><fmt:message key="catalogue.all"/></option>
                                <c:forEach items="${themesForForm}" var="theme">
                                    ${theme}<br>
                                    <option value="${theme}">${theme}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <label for="teacher"></label>
                            <select name="teacher" id="teacher">
                                <option value="" selected disabled hidden><fmt:message key="catalogue.all"/></option>
                                <c:forEach items="${teacherForForm}" var="teacher">
                                    ${teacher}<br>
                                    <option value="${teacher.id}">${teacher.firstName} ${teacher.lastName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <input type="radio" id="name" name="sort" value="course.name">
                            <label for="name"><fmt:message key="catalogue.sort.name"/></label><br>
                            <input type="radio" id="start_date" name="sort" value="DATEDIFF(end_date, start_date)">
                            <label for="start_date"><fmt:message key="catalogue.sort.duration"/></label><br>
                            <input type="radio" id="student_enrolled" name="sort" value="student_enrolled">
                            <label for="student_enrolled"><fmt:message key="catalogue.sort.student.enrolled"/></label><br>
                        </td>
                        <td>
                            <input type="radio" id="ascending" name="order" value="ascending">
                            <label for="ascending"><fmt:message key="catalogue.order.ascending"/></label><br>
                            <input type="radio" id="descending" name="order" value="descending">
                            <label for="descending"><fmt:message key="catalogue.order.descending"/></label><br>
                        </td>
                        <td>
                            <input type="radio" id="2" name="recordsPerPage" value="2">
                            <label for="ascending">2</label><br>
                            <input type="radio" id="5" name="recordsPerPage" value="5">
                            <label for="descending">5</label><br>
                            <input type="radio" id="10" name="recordsPerPage" value="10">
                            <label for="ascending">10</label><br>
                            <input type="radio" id="25" name="recordsPerPage" value="25">
                            <label for="descending">25</label><br>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <input type="submit" value="<fmt:message key="catalogue.apply"/>">
        <input type="reset" value="<fmt:message key="catalogue.reset"/>">
    </form>
    </div>

    <br><br>
    <h3 class="center"><fmt:message key="catalogue.course.title"/></h3>

<div class="row justify-content-center">
    <div class="row col-md-8">
        <table class="table table-bordered table-sm">
            <thead class="thead-light">
            <tr>
                <th><fmt:message key="catalogue.course.name"/></th>
                <th><fmt:message key="catalogue.course.theme"/></th>
                <th><fmt:message key="catalogue.course.start.date"/></th>
                <th><fmt:message key="catalogue.course.end.date"/></th>
                <th><fmt:message key="catalogue.course.duration.in.days"/></th>
                <th><fmt:message key="catalogue.course.teacher"/></th>
                <th><fmt:message key="catalogue.course.course.status"/></th>
                <th><fmt:message key="catalogue.course.student.enrolled"/></th>
                <c:choose>
                    <c:when test="${sessionScope.role == 'Student'}">
                        <th>Student option</th>
                    </c:when>
                </c:choose>
                <c:choose>
                    <c:when test="${sessionScope.role == 'Admin'}">
                        <th>Admin option</th>
                    </c:when>
                </c:choose>
            </tr>
            </thead>
            <tbody>
            <c:set var="index" value="0"/>

            <c:forEach var="course" items="${courses}">

                <tr>
                    <td>${course.name}</td>
                    <td>${course.theme}</td>
                    <td><mytag:dateFormatTag localDateTime="${course.startDate}"/></td>
                    <td><mytag:dateFormatTag localDateTime="${course.endDate}"/></td>
                    <td>${course.durationInDays}</td>
                    <td>
                        <c:forEach var="teacher" items="${teachers}" varStatus="loop">
                            <c:if test="${loop.index eq index}">${teacher.firstName}  ${teacher.lastName}</c:if>
                        </c:forEach>
                    </td>
                    <td>${course.courseStatus}</td>
                    <td>
                        <c:forEach var="stud" items="${nuOfStudents}" varStatus="loop">
                            <c:if test="${loop.index eq index}">${stud}</c:if>
                        </c:forEach>
                    </td>
                    <c:choose>
                        <c:when test="${sessionScope.role == 'Student'}">
                            <c:forEach var="alreadySelected" items="${courseAlreadySelected}" varStatus="loop">
                                <c:if test="${loop.index eq index}">
                                    <c:if test="${alreadySelected eq false}">
                                        <td>
                                            <form method="post" action="<c:url value='/controller'/>">
                                                <input type="hidden" name="command" value="ENROLL_ON_COURSE">
                                                <input type="number" hidden name="student-id" value="${sessionScope.id}"/>
                                                <input type="number" hidden name="course-id" value="${course.id}"/>
                                                <input class="btn btn-success" type="submit" name="enroll" value="Enroll"/>
                                            </form>
                                        </td>
                                    </c:if>
                                    <c:if test="${alreadySelected eq true}">
                                        <td>
                                            <form method="post" action="<c:url value='/controller'/>">
                                                <input type="hidden" name="command" value="LEAVE_COURSE">
                                                <input type="number" hidden name="student-id" value="${sessionScope.id}"/>
                                                <input type="number" hidden name="course-id" value="${course.id}"/>
                                                <input class="btn btn-danger" type="submit" name="enroll" value="Leave"/>
                                            </form>
                                        </td>
                                    </c:if>
                                </c:if>
                            </c:forEach>
                        </c:when>
                    </c:choose>
                        <c:if test="${sessionScope.role == 'Admin'}">
                           <td>
                               <form method="post" action="<c:url value='/controller'/>">
                                   <input type="hidden" name="command" value="DELETE_COURSE">
                                   <input type="number" hidden name="id" value="${course.id}"/>
                                   <input type="submit" name="delete" value="Delete"/>
                               </form>
                               <form method="get" action="<c:url value='/controller'/>">
                                   <input type="hidden" name="command" value="SHOW_COURSE_INFO">
                                   <input type="number" hidden name="id" value="${course.id}"/>
                                   <input type="submit" value="Update"/>
                               </form>
                           </td>
                     </c:if>
                </tr>
                <c:set var="index" value="${index + 1}"/>
            </c:forEach>
            </tbody>
        </table>

        <nav aria-label="navigation for courses">
            <ul class="pagination">
                <c:if test="${currentPage != 1}">
                    <li class="page-item">
                        <a class="page-link"
                           href="${path}&page=${currentPage - 1}${themeAttr}${sortAttr}${orderAttr}${recordsPerPageAttr}">
                            <fmt:message key="catalogue.course.page.previous"/>
                        </a>
                    </li>
                </c:if>

                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <c:if test="${1 != noOfPages}">
                                <li class="page-item">
                                    <a class="page-link"
                                       href="${path}&page=${i}${themeAttr}${sortAttr}${orderAttr}${recordsPerPageAttr}">${i}
                                    </a>
                                </li>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a class="page-link"
                                   href="${path}&page=${i}${themeAttr}${sortAttr}${orderAttr}${recordsPerPageAttr}">${i}
                                </a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${currentPage lt noOfPages}">
                    <li class="page-item">
                        <a class="page-link"
                           href="${path}&page=${currentPage + 1}${themeAttr}${sortAttr}${orderAttr}${recordsPerPageAttr}">
                            <fmt:message key="catalogue.course.page.next"/>
                        </a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </div>
</div>

<%@include file="/jspf/bootstrapScripts.jspf"%>
</body>
</html>
