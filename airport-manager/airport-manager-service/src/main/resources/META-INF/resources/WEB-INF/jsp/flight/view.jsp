<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Flight Administration">
<jsp:attribute name="body">

    <table class="table">
        <thead>
        <tr>
            <th>Id</th>
            <th>Flight number</th>
            <th>Origin</th>
            <th>Departure Time</th>
            <th>Destination</th>
            <th>Arrival Time</th>
            <th>Airplane</th>
            <th>Stewards</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${flight.id}</td>
            <td><c:out value="${flight.flightNumber}"/></td>
            <td><c:out value="${flight.origin.city}"/> <c:out value="${flight.origin.country}"/></td>
            <td><c:out value="${flight.departureTime}"/></td>
            <td><c:out value="${flight.destination.city}"/> <c:out value="${flight.destination.country}" /></td>
            <td><c:out value="${flight.arrivalTime}"/></td>
            <td><c:out value="${flight.airplane.name}"/></td>
            <c:forEach items="${flight.stewards}" var="steward">
                <td><c:out value="${steward.name} ${steward.surname}"/></td>
            </c:forEach>
        </tr>
        </tbody>
    </table>

    <form method="post" action="${pageContext.request.contextPath}/flight/delete/${flight.id}">
        <button type="submit" class="btn btn-danger">Delete</button>
    </form>

</jsp:attribute>
</my:pagetemplate>