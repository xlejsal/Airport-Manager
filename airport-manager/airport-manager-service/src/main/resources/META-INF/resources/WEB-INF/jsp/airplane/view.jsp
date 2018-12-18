<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Airplane Administration">
<jsp:attribute name="body">

    <table class="table">
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
        <tr>
            <td>${airplane.id}</td>
            <td><c:out value="${airplane.type}"/></td>
            <td><c:out value="${airplane.name}"/></td>
            <td><c:out value="${airplane.company}"/></td>
            <td><c:out value="${airplane.capacity}"/></td>
        </tr>
        </tbody>
    </table>

    <form method="post" action="${pageContext.request.contextPath}/airplane/delete/${airplane.id}">
        <button type="submit" class="btn btn-danger">Delete</button>
    </form>

</jsp:attribute>
</my:pagetemplate>