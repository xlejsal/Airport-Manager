<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<my:pagetemplate title="Destinations">
<jsp:attribute name="body">

<table class="table">
    <thead>
    <tr>
        <th>Airport Code</th>
        <th>City</th>
        <th>Country</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${destinations}" var="destination">
        <tr>
            <td><c:out value="${destination.airportCode}"/></td>
            <td><c:out value="${destination.city}"/></td>
            <td><c:out value="${destination.country}"/></td>
            <sec:authorize access="hasRole('ADMIN')">
            <td>
                <my:a href="/destination/view/${destination.id}" class="btn btn-primary">View</my:a>
            </td>
            </sec:authorize>
        </tr>
    </c:forEach>
    </tbody>
</table>

    <my:a href="/destination/new" class="btn btn-success">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        New Destination
    </my:a>

</jsp:attribute>
</my:pagetemplate>