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
            <td>
                <my:a href="/flight/view/${flight.id}/stewards" class="btn btn-primary btn-sm">Stewards</my:a>
            </td>
        </tr>
        </tbody>
    </table>

    <form method="post" action="${pageContext.request.contextPath}/flight/delete/${flight.id}">
        <button type="submit" class="btn btn-danger">Delete</button>
    </form>

    <form:form method="POST" action="${pageContext.request.contextPath}/flight/update" modelAttribute="flight"
               cssClass="form-horizontal">

    <div class="form-group ${id_error?'has-error':''}">
        <form:label path="id" cssClass="col-sm-2 control-label">Id</form:label>
        <div class="col-sm-10">
            <form:input path="id" cssClass="form-control" required="true" readonly="true"/>
            <form:errors path="id" cssClass="help-block"/>
        </div>
    </div>
    <div class="form-group ${flightNumber_error?'has-error':''}">
        <form:label path="flightNumber" cssClass="col-sm-2 control-label">Flight Number</form:label>
        <div class="col-sm-10">
            <form:input path="flightNumber" cssClass="form-control" required="true" readonly="true"/>
            <form:errors path="flightNumber" cssClass="help-block"/>
        </div>
    </div>
    <div class="form-group ${departureTime_error?'has-error':''}">
        <form:label path="departureTime" cssClass="col-sm-2 control-label">Departure Time (format: yyyy-MM-dd-HH-mm)</form:label>
        <div class="col-sm-10">
            <form:input path="departureTime" cssClass="form-control" required="true" readonly="true"/>
            <form:errors path="departureTime" cssClass="help-block"/>
        </div>
    </div>
    <div class="form-group ${arrivalTime_error?'has-error':''}">
        <form:label path="arrivalTime" cssClass="col-sm-2 control-label">Arrival Time (format: yyyy-MM-dd-HH-mm)</form:label>
        <div class="col-sm-10">
            <form:input path="arrivalTime" cssClass="form-control" required="true"/>
            <form:errors path="arrivalTime" cssClass="help-block"/>
        </div>
    </div>
    <div class="form-group">
        <form:label path="origin.id" cssClass="col-sm-2 control-label">Origin</form:label>
        <div class="col-sm-10">
                <form:select path="origin.id" cssClass="form-control" required="true">
                    <c:forEach items="${origin}" var="c">
                        <form:option value="${c.id}">${c.airportCode}</form:option>
                    </c:forEach>
                </form:select>
            <p class="help-block"><form:errors path="origin.id" cssClass="error"/></p>
        </div>
    </div>

    <div class="form-group">
        <form:label path="destination.id" cssClass="col-sm-2 control-label">Destination</form:label>
        <div class="col-sm-10">
                <form:select path="destination.id" cssClass="form-control" required="true">
                    <c:forEach items="${destination}" var="c">
                        <form:option value="${c.id}">${c.airportCode}</form:option>
                    </c:forEach>
                </form:select>
            <p class="help-block"><form:errors path="destination.id" cssClass="error"/></p>
        </div>
    </div>
    <div class="form-group">
        <form:label path="airplane.id" cssClass="col-sm-2 control-label">Airplane</form:label>
        <div class="col-sm-10">
                <form:select path="airplane.id" cssClass="form-control" required="true">
                    <c:forEach items="${airplane}" var="c">
                        <form:option value="${c.id}">${c.name}</form:option>
                    </c:forEach>
                </form:select>
            <p class="help-block"><form:errors path="airplane.id" cssClass="error"/></p>
        </div>
    </div>

    <td><button class="btn btn-primary" type="submit">Update</button></td>

</form:form>



</jsp:attribute>
</my:pagetemplate>