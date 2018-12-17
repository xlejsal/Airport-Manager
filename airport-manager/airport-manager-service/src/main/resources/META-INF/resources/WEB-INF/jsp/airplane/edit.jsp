<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Edit Airplane">
<jsp:attribute name="body">

    <form:form method="post" action="${pageContext.request.contextPath}/airplane/update/${airplane.id}"
               modelAttribute="airplane" cssClass="form-horizontal">
        <div class="form-group ${name_error?'has-error':''}">
            <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
            <div class="col-sm-10">
                <form:input path="name" readonly="true" cssClass="form-control" value="${airplane.name}"/>
                <form:errors path="name" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${type_error?'has-error':''}">
            <form:label path="type" cssClass="col-sm-2 control-label">Type</form:label>
            <div class="col-sm-10">
                <form:input path="type" cssClass="form-control" required="true" value="${airplane.type}"/>
                <form:errors path="type" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${company_error?'has-error':''}" >
            <form:label path="company" cssClass="col-sm-2 control-label">Company</form:label>
            <div class="col-sm-10">
                <form:input path="company" cssClass="form-control" required="true" value="${airplane.company}"/>
                <form:errors path="company" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${capacity_error?'has-error':''}">
            <form:label path="capacity" cssClass="col-sm-2 control-label">Capacity</form:label>
            <div class="col-sm-10">
                <form:input type="number" path="capacity" cssClass="form-control" value="${airplane.capacity}"/>
                <form:errors path="capacity" cssClass="help-block"/>
            </div>
        </div>


        <button class="btn btn-primary" type="submit">Edit Airplane</button>
    </form:form>

</jsp:attribute>
</my:pagetemplate>