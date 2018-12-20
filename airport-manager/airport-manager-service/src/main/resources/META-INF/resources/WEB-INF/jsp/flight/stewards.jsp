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
            <td>
                <form method="post" action="${pageContext.request.contextPath}/flight/view/${flight.id}/stewards/remove/${steward.id}">
                    <button type="submit" class="btn btn-danger">Remove from flight</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

    <my:a href="/flight/view/${flight.id}/stewards/add" class="btn btn-success">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        Add steward
    </my:a>

</jsp:attribute>
</my:pagetemplate>