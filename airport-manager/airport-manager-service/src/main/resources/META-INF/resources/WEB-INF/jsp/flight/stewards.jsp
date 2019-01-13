<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Stewards of flight number ${flight.flightNumber}">
<jsp:attribute name="body">

<table class="table">
    <thead>
    <tr>
        <th>Name</th>
        <th>Surname</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${stewards}" var="steward">
        <tr>
            <td><c:out value="${steward.name}"/></td>
            <td><c:out value="${steward.surname}"/></td>
            <td><my:a href="/flight/${flight.flightNumber}/remove/steward/${steward.id}" class="btn btn-primary btn-sm">Remove</my:a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

    <my:a href="/flight/${flight.flightNumber}/stewards/toggle/" class="btn btn-success">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        Show available stewards
    </my:a>

    <table class="table">
        <thead>
        <tr>
            <th>Name</th>
            <th>Surname</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${availableStewards}" var="availableSteward">
        <tr>
            <td><c:out value="${availableSteward.name}"/></td>
            <td><c:out value="${availableSteward.surname}"/></td>
            <td><my:a href="/flight/${flight.flightNumber}/add/steward/${availableSteward.id}" class="btn btn-primary btn-sm">Add</my:a></td>
        </tr>
    </c:forEach>
        </tbody>
    </table>

</jsp:attribute>
</my:pagetemplate>