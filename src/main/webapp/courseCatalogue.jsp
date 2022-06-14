<%@include file="/jspf/header.jspf"%>

<html>
<head>
    <title>Faculty</title>
    <%@include file="/jspf/head.jspf"%>
    <style>
        .center {
            text-align: center;
        }
    </style>
</head>
<body>
    <%@include file="/jspf/navbar.jspf"%>
    <br>
    <br>

    <div class="center">
        <h3>Filter</h3>
        <form action="controller">

            <input type="hidden" name="command" value="COURSE_CATALOGUE">
            <h6><label for="theme">Theme:</label></h6>
            <select name="theme" id="theme">
                <option value="" selected disabled hidden>Choose here</option>
                <c:forEach items="${themesForForm}" var="theme">
                    ${theme}<br>
                    <option value="${theme}">${theme}</option>
                </c:forEach>
            </select>
            <br><br>

            <h6><label for="teacher">Teacher:</label></h6>
            <select name="teacher" id="teacher">
                <option value="" selected disabled hidden>Choose here</option>
                <c:forEach items="${teacherForForm}" var="teacher">
                    ${teacher}<br>
                    <option value="${teacher.id}">${teacher.firstName} ${teacher.lastName}</option>
                </c:forEach>
            </select>
            <br><br>

            <h6>Sort by:</h6>
            <input type="radio" id="sort-name" name="sort" value="sort-name">
            <label for="sort-name">Name</label><br>
            <input type="radio" id="sort-duration" name="sort" value="sort-duration">
            <label for="sort-duration">Duration</label><br>
            <input type="radio" id="sort-student-enrolled" name="sort" value="sort-student-enrolled">
            <label for="sort-student-enrolled">Student enrolled</label><br>

            <h6>Order by:</h6>
            <input type="radio" id="ascending" name="order" value="ascending">
            <label for="ascending">Ascending</label><br>
            <input type="radio" id="descending" name="order" value="descending">
            <label for="descending">Descending</label><br>

            <input type="submit" value="Apply">
        </form>
    <br><br>

    <h3>Courses</h3>
    </div>

    <div class="row justify-content-center">
    <div class="row col-md-8">
        <table class="table table-bordered table-sm">
            <thead class="thead-light">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Theme</th>
                <th>Start date</th>
                <th>End date</th>
                <th>Duration in days</th>
                <th>Teacher</th>
                <th>Student enrolled</th>
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
                    <td>${course.id}</td>
                    <td>${course.name}</td>
                    <td>${course.theme}</td>
                    <td>${course.startDate}</td>
                    <td>${course.endDate}</td>
                    <td>${course.durationInDays}</td>
                    <td>
                        <c:forEach var="teacher" items="${teachers}" varStatus="loop">
                            <c:if test="${loop.index eq index}">${teacher.firstName}  ${teacher.lastName}</c:if>
                        </c:forEach>
                    </td>
                    <td>
                         <c:forEach var="stud" items="${nuOfStudents}" varStatus="loop">
                             <c:if test="${loop.index eq index}">${stud}</c:if>
                         </c:forEach>
                    </td>
                    <c:choose>
                        <c:when test="${sessionScope.role == 'Student'}">
                            <td>
                                <form method="post" action="<c:url value='/controller'/>">
                                    <input type="hidden" name="command" value="ENROLL_ON_COURSE">
                                    <input type="number" hidden name="student-id" value="${sessionScope.id}"/>
                                    <input type="number" hidden name="course-id" value="${course.id}"/>
                                    <input type="submit" name="enroll" value="Enroll"/>
                                </form>
                            </td>
                        </c:when>
                    </c:choose>
                        <c:choose>
                            <c:when test="${sessionScope.role == 'Admin'}">
                                <td>
                                <form method="post" action="<c:url value='/controller'/>">
                                    <input type="hidden" name="command" value="DELETE_COURSE">
                                    <input type="number" hidden name="id" value="${course.id}"/>
                                    <input type="submit" name="delete" value="Delete"/>
                                </form>

                                <form method="get" action="<c:url value='/updateCourse.jsp'/>">
                                    <input type="hidden" name="command" value="UPDATE_COURSE">
                                    <input type="number" hidden name="id" value="${course.id}"/>
                                    <input type="text" hidden name="name" value="${course.name}"/>
                                    <input type="submit" value="Update"/>
                                </form>
                                </td>
                            </c:when>
                        </c:choose>
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
                            href="controller?command=COURSE_CATALOGUE&page=${currentPage - 1}<c:if test="${theme != null}">&theme=${theme}</c:if><c:if test="${sort != null}">&sort=${sort}</c:if><c:if test="${order != null}">&order=${order}</c:if>">
                            Previous
                        </a>
                    </li>
                </c:if>

                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <li class="page-item">
                                <a class="page-link" href="controller?command=COURSE_CATALOGUE&page=${i}<c:if test="${theme != null}">&theme=${theme}</c:if><c:if test="${sort != null}">&sort=${sort}</c:if><c:if test="${order != null}">&order=${order}</c:if>">${i}</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a class="page-link" href="controller?command=COURSE_CATALOGUE&page=${i}<c:if test="${theme != null}">&theme=${theme}</c:if><c:if test="${sort != null}">&sort=${sort}</c:if><c:if test="${order != null}">&order=${order}</c:if>">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${currentPage lt noOfPages}">
                    <li class="page-item">
                        <a class="page-link" href="controller?command=COURSE_CATALOGUE&page=${currentPage + 1}<c:if test="${theme != null}">&theme=${theme}</c:if><c:if test="${sort != null}">&sort=${sort}</c:if><c:if test="${order != null}">&order=${order}</c:if>">
                            Next
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

