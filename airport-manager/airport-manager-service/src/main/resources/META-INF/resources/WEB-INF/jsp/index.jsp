<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Airport Manager">
<jsp:attribute name="body">

    <h3>An application for management of flights.</h3>

    <p>You need to log in in order to access the pages.</p>
    <p>User can access only flight, airplane and destination lists.</p>
    <p>Admin can access everything.</p>


</jsp:attribute>
</my:pagetemplate>