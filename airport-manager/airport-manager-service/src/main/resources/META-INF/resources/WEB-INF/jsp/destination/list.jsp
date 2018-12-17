<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Destinations">
<jsp:attribute name="body">

    <my:a href="/destination/new" class="btn btn-primary">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        New Destination
    </my:a>

<table class="table">
    <caption>Destinations</caption>
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
            <td>
                <my:a href="/destination/view/${destination.id}" class="btn btn-primary">View</my:a>
            </td>
            <td>
                <form method="post" action="${pageContext.request.contextPath}/destination/delete/${destination.id}">
                    <button type="submit" class="btn btn-primary">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</jsp:attribute>
</my:pagetemplate>