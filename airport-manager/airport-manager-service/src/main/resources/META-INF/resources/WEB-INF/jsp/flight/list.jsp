<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <title>Flight List</title>
</head>

<body>
<table class="table">
    <caption>Flights</caption>
    <thead>
    <tr>
        <th>id</th>
        <th>flightNumber</th>
        <th>origin</th>
        <th>departure</th>
        <th>destination</th>
        <th>arrival</th>
        <th>airplane</th>
        <th>stewards</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${flights}" var="flight">
        <tr>
            <td>${flight.id}</td>
            <td><c:out value="${flight.flightNumber}"/></td>
            <td><c:out value="${flight.origin.country}"/> <c:out value="${flight.origin.city}"/></td>
            <td><c:out value="${flight.departureTime}"/></td>
            <td><c:out value="${flight.destination.country}"/> <c:out value="${flight.destination.city}" /></td>
            <td><c:out value="${flight.arrivalTime}"/></td>
            <td><c:out value="${flight.airplane.name}"/></td>
            <c:forEach items="${flight.stewards}" var="steward">
                <td><c:out value="${steward.name}"/></td>
            </c:forEach>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>

</html>