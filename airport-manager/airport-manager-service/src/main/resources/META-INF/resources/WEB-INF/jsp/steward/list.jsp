<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Stewards">
<jsp:attribute name="body">

<table class="table">
    <thead>
    <tr>
        <th>name</th>
        <th>surname</th>
        <th>nationality</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${stewards}" var="steward">
        <tr>
            <td><c:out value="${steward.name}"/></td>
            <td><c:out value="${steward.surname}"/></td>
            <td><c:out value="${steward.nationality}"/></td>
            <td>
                <my:a href="/steward/view/${steward.id}" class="btn btn-primary btn-sm">View</my:a>
                <my:a href="/steward/edit/${steward.id}" class="btn btn-warning btn-sm">Edit</my:a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

    <my:a href="/steward/new" class="btn btn-success">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        Create new
    </my:a>

</jsp:attribute>
</my:pagetemplate>