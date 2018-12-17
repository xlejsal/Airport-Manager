<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Steward Administration">
<jsp:attribute name="body">

    <table class="table">
        <thead>
        <tr>
            <th>id</th>
            <th>gender</th>
            <th>name</th>
            <th>surname</th>
            <th>nationality</th>
            <th>birth date</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${steward.id}</td>
            <td><c:out value="${steward.gender}"/></td>
            <td><c:out value="${steward.name}"/></td>
            <td><c:out value="${steward.surname}"/></td>
            <td><c:out value="${steward.nationality}"/></td>
            <fmt:parseDate value="${steward.birthDate}" pattern="yyyy-MM-dd" var="Date"/>
            <td><fmt:formatDate value="${Date}" pattern="yyyy-MM-dd"/></td>
        </tr>
        </tbody>
    </table>

    <form method="post" action="${pageContext.request.contextPath}/steward/delete/${steward.id}">
        <button type="submit" class="btn btn-warning">Delete</button>
    </form>

</jsp:attribute>
</my:pagetemplate>