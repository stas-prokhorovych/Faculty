<%@include file="/jspf/header.jspf"%>

<html>
<head>
    <title>Faculty</title>
    <%@include file="/jspf/head.jspf"%>
</head>
<body>
    <%@include file="/jspf/navbar.jspf"%>
    <br>
    <br>

    <div>
        <h3>Filter</h3>
        <form action="list">
            <label for="theme">Theme:</label>
            <select name="theme" id="theme">
                <option value="All">All</option>
                <c:forEach items="${themes}" var="theme">
                    ${theme}<br>
                    <option value="${theme}">${theme}</option>
                </c:forEach>
            </select>
            <br>
            <label for="sort">Sort by:</label>
            <select name="sort" id="sort">
                <option value="sort-name">Name</option>
                <option value="sort-duration">Duration</option>
                <option value="sort-student-enrolled">Student enrolled</option>
            </select>
            <br>
            <label for="order">Order by:</label>
            <select name="order" id="order">
                <option value="ascending">Ascending</option>
                <option value="descending">Descending</option>
            </select>
            <br>
            <%--        <input type="hidden" name="theme" value="${theme}">&ndash;%&gt;--%>
            <input type="submit" value="Apply">
        </form>
    </div>

    <h3>Courses</h3>
    <div class="row col-md-6">
        <table class="table table-bordered table-sm">
            <thead class="thead-light">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Theme</th>
                <th>Start date</th>
                <th>End date</th>
                <th>Duration in days</th>
                <c:choose>
                    <c:when test="${sessionScope.role == 'Admin'}">
                        <th>Admin option</th>
                    </c:when>
                </c:choose>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="course" items="${courses}">
                <tr>
                    <td>${course.id}</td>
                    <td>${course.name}</td>
                    <td>${course.theme}</td>
                    <td>${course.startDate}</td>
                    <td>${course.endDate}</td>
                    <td>${course.durationInDays}</td>
                    <td>
                        <c:choose>
                            <c:when test="${sessionScope.role == 'Admin'}">
                                <form method="post" action="<c:url value='/controller?command=DELETE_COURSE'/>">
                                    <input type="number" hidden name="id" value="${course.id}"/>
                                    <input type="submit" name="delete" value="Delete"/>
                                </form>

                                <form method="get" action="<c:url value='/update-course'/>">
                                    <input type="number" hidden name="id" value="${course.id}"/>
                                    <input type="submit" value="Update"/>
                                </form>
                            </c:when>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <nav aria-label="navigation for courses">
            <ul class="pagination">
                <c:if test="${currentPage != 1}">
                    <li class="page-item">
                        <a class="page-link" href="controller?command=COURSE_CATALOGUE&page=${currentPage - 1}<c:if test="${theme != null}">&theme=${theme}</c:if>">Previous</a>
                    </li>
                </c:if>

                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <li class="page-item">
                                    <%--                        ${i}--%>
                                <a class="page-link" href="controller?command=COURSE_CATALOGUE&page=${i}<c:if test="${theme != null}">&theme=${theme}</c:if>">${i}</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a class="page-link" href="controller?command=COURSE_CATALOGUE&page=${i}<c:if test="${theme != null}">&theme=${theme}</c:if>">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${currentPage lt noOfPages}">
                    <li class="page-item">
                        <a class="page-link" href="controller?command=COURSE_CATALOGUE&page=${currentPage + 1}<c:if test="${theme != null}">&theme=${theme}</c:if>">
                            Next
                        </a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </div>

    <%@include file="/jspf/bootstrapScripts.jspf"%>
</body>
</html>

