<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Airplanes">
<jsp:attribute name="body">

<table class="table">
    <thead>
    <tr>
        <th>type</th>
        <th>name</th>
        <th>company</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${airplanes}" var="airplane">
        <tr>
            <td><c:out value="${airplane.type}"/></td>
            <td><c:out value="${airplane.name}"/></td>
            <td><c:out value="${airplane.company}"/></td>
            <td>
                <my:a href="/airplane/view/${airplane.id}" class="btn btn-primary btn-sm">View</my:a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</jsp:attribute>
</my:pagetemplate>