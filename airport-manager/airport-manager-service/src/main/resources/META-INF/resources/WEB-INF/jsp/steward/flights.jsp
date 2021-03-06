<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Flights of Steward ${steward.name} ${steward.surname}">
<jsp:attribute name="body">

<table class="table">
    <thead>
    <tr>
        <th>flight number</th>
        <th>origin</th>
        <th>destination</th>
        <th>departure</th>
        <th>arrival</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${flights}" var="flight">
        <tr>
            <td><c:out value="${flight.flightNumber}"/></td>
            <td><c:out value="${flight.origin.city}"/>, <c:out value="${flight.origin.country}"/></td>
            <td><c:out value="${flight.destination.city}"/>, <c:out value="${flight.destination.country}"/></td>
            <td><c:out value="${flight.departureTime}"/></td>
            <td><c:out value="${flight.arrivalTime}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</jsp:attribute>
</my:pagetemplate>