<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="New Flight">
<jsp:attribute name="body">
<form:form method="POST" action="${pageContext.request.contextPath}/flight/create" modelAttribute="flightDto"
           cssClass="form-horizontal">

    <div class="form-group ${flightNumber_error?'has-error':''}">
        <form:label path="flightNumber" cssClass="col-sm-2 control-label">Flight Number</form:label>
        <div class="col-sm-10">
            <form:input path="flightNumber" cssClass="form-control" required="true"/>
            <form:errors path="flightNumber" cssClass="help-block"/>
        </div>
    </div>
    <div class="form-group">
        <form:label path="originId" cssClass="col-sm-2 control-label">Origin</form:label>
        <div class="col-sm-10">
                <form:select path="originId" cssClass="form-control" required="true">
                    <c:forEach items="${origin}" var="c">
                        <form:option value="${c.id}">${c.airportCode}</form:option>
                    </c:forEach>
                </form:select>
            <p class="help-block"><form:errors path="originId" cssClass="error"/></p>
        </div>
    </div>
    <div class="form-group ${departureTime_error?'has-error':''}">
        <form:label path="departureTime" cssClass="col-sm-2 control-label">Departure Time</form:label>
        <div class="col-sm-10">
            <form:input path="departureTime" cssClass="form-control" required="true"/>
            <form:errors path="departureTime" cssClass="help-block"/>
        </div>
    </div>
    <div class="form-group">
        <form:label path="destinationId" cssClass="col-sm-2 control-label">Destination</form:label>
        <div class="col-sm-10">
                <form:select path="destinationId" cssClass="form-control" required="true">
                    <c:forEach items="${destination}" var="c">
                        <form:option value="${c.id}">${c.airportCode}</form:option>
                    </c:forEach>
                </form:select>
            <p class="help-block"><form:errors path="destinationId" cssClass="error"/></p>
        </div>
    </div>
    <div class="form-group ${arrivalTime_error?'has-error':''}">
        <form:label path="arrivalTime" cssClass="col-sm-2 control-label">Arrival Time</form:label>
        <div class="col-sm-10">
            <form:input path="arrivalTime" cssClass="form-control" required="true"/>
            <form:errors path="arrivalTime" cssClass="help-block"/>
        </div>
    </div>
    <div class="form-group">
        <form:label path="airplaneId" cssClass="col-sm-2 control-label">Airplane</form:label>
        <div class="col-sm-10">
                <form:select path="airplaneId" cssClass="form-control" required="true">
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
                <form:select path="stewardIds" cssClass="form-control">
                    <c:forEach items="${stewards}" var="c">
                        <form:option value="${c.id}">${c.name}${c.surname}</form:option>
                    </c:forEach>
                </form:select>
            <p class="help-block"><form:errors path="stewardIds" cssClass="error"/></p>
        </div>
    </div>

    <td><button class="btn btn-primary" type="submit">Create flight</button></td>

</form:form>
</jsp:attribute>
</my:pagetemplate>