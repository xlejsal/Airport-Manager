<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Destination Details">
<jsp:attribute name="body">

    <table class="table">
        <thead>
        <tr>
            <th>Id</th>
            <th>Airport Code</th>
            <th>City</th>
            <th>Country</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${destination.id}</td>
            <td><c:out value="${destination.airportCode}"/></td>
            <td><c:out value="${destination.city}"/></td>
            <td><c:out value="${destination.country}"/></td>
        </tr>
        </tbody>
    </table>

    <form method="post" action="${pageContext.request.contextPath}/destination/delete/${destination.id}">
        <button type="submit" class="btn btn-danger">Delete</button>
    </form>

</jsp:attribute>
</my:pagetemplate>