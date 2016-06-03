<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<header class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="pull-left">
            <a class="navbar-brand"
               href="${pageContext.request.contextPath}/computer/view/all"><spring:message
                    code="header.title"/></a>
        </div>

        <div class="pull-right">
            <c:if test="${not empty user}">
                <span class="navbar-brand">
                    <spring:message code="welcome"/> ${user.username} ! | <a
                        href="${pageContext.request.contextPath}/logout"><spring:message code="logout"/></a>
                </span>
            </c:if>
            <c:if test="${empty user}">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/login">
                    <spring:message code="login"/>
                </a>
            </c:if>
        </div>
    </div>
</header>