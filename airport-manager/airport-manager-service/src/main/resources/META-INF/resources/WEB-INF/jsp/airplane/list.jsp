<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Airplanes">
<jsp:attribute name="body">

<table class="table">
    <thead>
    <tr>
        <th>name</th>
        <th>type</th>
        <th>company</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${airplanes}" var="airplane">
        <tr>
            <td><c:out value="${airplane.name}"/></td>
            <td><c:out value="${airplane.type}"/></td>
            <td><c:out value="${airplane.company}"/></td>
            <td>
                <my:a href="/airplane/view/${airplane.id}" class="btn btn-primary btn-sm">View</my:a>
                <my:a href="/airplane/edit/${airplane.id}" class="btn btn-warning btn-sm">Edit</my:a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

    <my:a href="/airplane/new" class="btn btn-success">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        Create new
    </my:a>

</jsp:attribute>
</my:pagetemplate>