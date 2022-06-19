<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="login" required="true" type="java.lang.String" %>
<%@attribute name="email" required="true" type="java.lang.String" %>
<%@attribute name="firstName" required="true" type="java.lang.String" %>
<%@attribute name="lastName" required="true" type="java.lang.String" %>


<td>${login}</td>
<td>${email}</td>
<td>${firstName}</td>
<td>${lastName}</td>
