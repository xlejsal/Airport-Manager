<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <title>Airplane List</title>
</head>

<body>
<table class="table">
    <caption>Airplanes</caption>
    <thead>
    <tr>
        <th>id</th>
        <th>type</th>
        <th>name</th>
        <th>company</th>
        <th>capacity</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${airplanes}" var="airplane">
        <tr>
            <td>${airplane.id}</td>
            <td><c:out value="${airplane.type}"/></td>
            <td><c:out value="${airplane.name}"/></td>
            <td><c:out value="${airplane.company}"/></td>
            <td><c:out value="${airplane.capacity}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>

</html>