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
        </tr>
    </c:forEach>
    </tbody>
</table>

</jsp:attribute>
</my:pagetemplate>