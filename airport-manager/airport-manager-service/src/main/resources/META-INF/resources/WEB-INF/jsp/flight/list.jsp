<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Flights">
<jsp:attribute name="body">

<table class="table">
    <caption>Flights</caption>
    <thead>
    <tr>
        <th>Id</th>
        <th>Flight number</th>
        <th>Origin</th>
        <th>Departure</th>
        <th>Destination</th>
        <th>Arrival</th>
        <th>Airplane</th>
        <th>Stewards</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${flights}" var="flight">
        <tr>
            <td>${flight.id}</td>
            <td><c:out value="${flight.flightNumber}"/></td>
            <td><c:out value="${flight.origin.city}"/> <c:out value="${flight.origin.country}"/></td>
            <td><c:out value="${flight.departureTime}"/></td>
            <td><c:out value="${flight.destination.city}"/> <c:out value="${flight.destination.country}" /></td>
            <td><c:out value="${flight.arrivalTime}"/></td>
            <td><c:out value="${flight.airplane.type}"/></td>
            <c:forEach items="${flight.stewards}" var="steward">
                <td><c:out value="${steward.name}"/></td>
            </c:forEach>
        </tr>
    </c:forEach>
    </tbody>
</table>
</jsp:attribute>
</my:pagetemplate>