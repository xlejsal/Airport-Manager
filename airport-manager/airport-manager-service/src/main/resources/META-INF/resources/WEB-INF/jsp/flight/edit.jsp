<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Edit Flight">
<jsp:attribute name="body">

    <form:form method="post" action="${pageContext.request.contextPath}/flight/update/${flight.id}"
               modelAttribute="flight" cssClass="form-horizontal">

        <div class="form-group ${flightNumber_error?'has-error':''}">
            <form:label path="flightNumber" cssClass="col-sm-2 control-label">Flight Number</form:label>
            <div class="col-sm-10">
                <form:input path="flightNumber" cssClass="form-control" readonly="true" value="${flight.flightNumber}"/>
                <form:errors path="flightNumber" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${originId_error?'has-error':''}">
            <form:label path="originId" cssClass="col-sm-2 control-label">Origin</form:label>
            <div class="col-sm-10">
                <form:input path="originId" cssClass="form-control" readonly="true" value="${flight.origin}"/>
                <form:errors path="originId" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${departureTime_error?'has-error':''}">
            <form:label path="departureTime" cssClass="col-sm-2 control-label">Departure Time</form:label>
            <div class="col-sm-10">
                <form:input path="departureTime" cssClass="form-control" value="${flight.departureTime}"/>
                <form:errors path="departureTime" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${destinationId_error?'has-error':''}">
            <form:label path="destinationId" cssClass="col-sm-2 control-label">Destination</form:label>
            <div class="col-sm-10">
                <form:input path="destinationId" cssClass="form-control" readonly="true" value="${flight.destination}"/>
                <form:errors path="destinationId" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${arrivalTime_error?'has-error':''}">
            <form:label path="arrivalTime" cssClass="col-sm-2 control-label">Arrival Time</form:label>
            <div class="col-sm-10">
                <form:input path="arrivalTime" cssClass="form-control" value="${flight.arrivalTime}"/>
                <form:errors path="arrivalTime" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="airplaneId" cssClass="col-sm-2 control-label">Airplane</form:label>
            <div class="col-sm-10">
                    <form:select path="airplaneId" cssClass="form-control" value="${flight.airplane.name}">
                        <c:forEach items="${airplane}" var="c">
                            <form:option value="${c.id}">${c.name}</form:option>
                        </c:forEach>
                    </form:select>
                <p class="help-block"><form:errors path="airplaneId" cssClass="error"/></p>
            </div>
        </div>
        <div class="form-group">
            <form:label path="stewardIds" cssClass="col-sm-2 control-label">Stewards</form:label>
            <div class="col-sm-10">
                    <form:select path="stewardIds" cssClass="form-control" value="${flight.stewards}">
                        <c:forEach items="${stewards}" var="c">
                            <form:option value="${c.id}">${c.name}${c.surname}</form:option>
                        </c:forEach>
                    </form:select>
                <p class="help-block"><form:errors path="stewardIds" cssClass="error"/></p>
            </div>
        </div>

        <button class="btn btn-primary" type="submit">Edit Flight</button>
    </form:form>

</jsp:attribute>
</my:pagetemplate>