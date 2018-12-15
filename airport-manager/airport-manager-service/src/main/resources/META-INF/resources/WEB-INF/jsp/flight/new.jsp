<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="New Flight">
<jsp:attribute name="body">
<form:form method="post" action="${pageContext.request.contextPath}/flight/create" modelAttribute="flightCreate">

<table class="table">
    <tr>
        <td><form:label path="id">Id</form:label></td>
        <td><form:input path="id"/></td>
    </tr>
    <tr>
        <td><form:label path="flightNumber">Flight number</form:label></td>
        <td><form:input path="flightNumber"/></td>
    </tr>
    <tr>
        <td><form:label path="origin">Origin</form:label></td>
        <td><form:select path="origin">
                    <c:forEach items="${origins}" var="o">
                        <form:option value="${o.airportCode}">${o.airportCode}</form:option>
                    </c:forEach>
        </form:select></td>
    </tr>
    <tr>
        <td><form:label type="date" path="departureTime">Departure</form:label></td>
        <td><form:input path="departureTime"/></td>
    </tr>
    <tr>
        <td><form:label path="destination">Destination</form:label></td>
        <td><form:select path="destination">
                    <c:forEach items="${destinations}" var="d">
                        <form:option value="${d.airportCode}">${d.airportCode}</form:option>
                    </c:forEach>
        </form:select></td>
    </tr>
    <tr>
        <td><form:label type="date" path="arrivalTime">Arrival</form:label></td>
        <td><form:input path="arrivalTime"/></td>
    </tr>
    <tr>
        <td><form:label path="airplane">Airplane</form:label></td>
        <td><form:select path="airplane">
                    <c:forEach items="${airplanes}" var="a">
                        <form:option value="${a.name}">${a.name}</form:option>
                    </c:forEach>
        </form:select></td>
    </tr>
    <tr>
        <td><form:label path="stewards">Stewards</form:label></td>
        <td><form:select path="stewards" multiple="rue">
                    <c:forEach items="${stewards}" var="s">
                        <form:option value="${s.id}">${s.id}</form:option>
                    </c:forEach>
        </form:select></td>
    </tr>
    <tr>
        <td><button class="btn btn-primary" type="submit">Create flight</button></td>
    </tr>

</table>
</form:form>
</jsp:attribute>
</my:pagetemplate>