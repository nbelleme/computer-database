<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/views/fragments/head.jsp" %>
<body onload="checkDateOnLoad()">
<%@include file="/WEB-INF/views/fragments/header.jsp" %>
<section id="main">
    <div class="container">
        <div class="row">
            <div class="col-xs-8 col-xs-offset-2 box">
                <div class="label label-default pull-right">
                    id:${computer.id}</div>
                <h1>Edit Computer : ${computer.name}</h1>

                <form action="${pageContext.request.contextPath}/computer/edit" method="POST">
                    <input id="id" type="hidden" value="${computer.id}" name="id"/>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" class="form-control"/>

                    <fieldset>
                        <div class="form-group">
                            <label for="computerName">Computer name</label> <input
                                type="text" class="form-control" id="computerName"
                                name="name" placeholder="Computer name"
                                value="${computer.name}">
                        </div>
                        <div class="form-group">
                            <label for="introduced">Introduced date</label> <input
                                type="date" class="form-control" id="introduced"
                                name="introduced" placeholder="Introduced date"
                                value="${computer.introduced}"
                                onkeyup="checkDate('introduced')">
                        </div>
                        <div class="form-group">
                            <label for="discontinued">Discontinued date</label> <input
                                type="date" class="form-control" id="discontinued"
                                name="discontinued" placeholder="Discontinued date"
                                value="${computer.discontinued}"
                                onkeyup="checkDate('discontinued')">
                        </div>
                        <div class="form-group">
                            <label for="companyId">Company</label> <select
                                class="form-control" id="companyId" name="company">
                            <option value=""> --</option>
                            <c:forEach var="company" items="${companies}">
                                <option value="${company.id}"
                                    ${computer.idCompany == company.id ? 'selected' : '' }>
                                        ${company.name}</option>
                            </c:forEach>
                        </select>
                        </div>
                    </fieldset>
                    <div class="actions pull-right">
                        <input type="submit" value="Edit" class="btn btn-primary">
                        or <a href="${pageContext.request.contextPath}/computer/view/all"
                              class="btn btn-default">Cancel</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>

<spring:url value="/resources/js/addComputer.js" var="addComputerJS"/>
<script type="text/javascript" src="${addComputerJS}"></script>
</body>
</html>