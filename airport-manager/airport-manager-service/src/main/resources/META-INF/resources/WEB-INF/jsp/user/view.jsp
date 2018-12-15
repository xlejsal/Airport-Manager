<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="User Administration">
<jsp:attribute name="body">

    <table class="table">
        <thead>
        <tr>
            <th>id</th>
            <th>login</th>
            <th>email</th>
            <th>name</th>
            <th>surname</th>
            <th>admin</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${user.id}</td>
            <td><c:out value="${user.login}"/></td>
            <td><c:out value="${user.email}"/></td>
            <td><c:out value="${user.name}"/></td>
            <td><c:out value="${user.surname}"/></td>
            <td><c:out value="${user.admin}"/></td>
        </tr>
        </tbody>
    </table>

    <form method="post" action="${pageContext.request.contextPath}/user/delete/${user.id}">
        <button type="submit" class="btn btn-warning">Delete</button>
    </form>

</jsp:attribute>
</my:pagetemplate>