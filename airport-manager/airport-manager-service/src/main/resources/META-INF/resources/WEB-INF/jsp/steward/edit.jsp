<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Edit Steward">
<jsp:attribute name="body">

    <form:form method="post" action="${pageContext.request.contextPath}/steward/update/${steward.id}"
               modelAttribute="steward" cssClass="form-horizontal">
        <div class="form-group ${name_error?'has-error':''}">
            <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
            <div class="col-sm-10">
                <form:input readonly="true" class="form-control-plaintext" path="name" cssClass="form-control" required="true" value="${steward.name}"/>
                <form:errors path="name" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${surname_error?'has-error':''}">
            <form:label path="surname" cssClass="col-sm-2 control-label">Surname</form:label>
            <div class="col-sm-10">
                <form:input readonly="true" class="form-control-plaintext" path="surname" cssClass="form-control" required="true" value="${steward.surname}"/>
                <form:errors path="surname" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="gender" cssClass="col-sm-2 control-label">Gender</form:label>
            <div class="col-sm-10">
                <form:select path="gender" cssClass="form-control" required="true" value="${steward.gender}">
                    <c:forEach items="${genders}" var="c">
                        <form:option value="${c}">${c}</form:option>
                    </c:forEach>
                </form:select>
                <form:errors path="gender" cssClass="error"/>
            </div>
        </div>
        <div class="form-group ${birthDate_error?'has-error':''}">
            <form:label path="birthDate" cssClass="col-sm-2 control-label">Date of Birth</form:label>
            <div class="col-sm-10">
                <form:input type="date" readonly="true" class="form-control-plaintext" path="birthDate" cssClass="form-control" required="true" value="${steward.birthDate}"/>
                <form:errors path="birthDate" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${nationality_error?'has-error':''}">
            <form:label path="nationality" cssClass="col-sm-2 control-label">Nationality</form:label>
            <div class="col-sm-10">
                <form:input path="nationality" cssClass="form-control" value="${steward.nationality}"/>
                <form:errors path="nationality" cssClass="help-block"/>
            </div>
        </div>


        <button class="btn btn-primary" type="submit">Edit Steward</button>
    </form:form>

</jsp:attribute>
</my:pagetemplate>