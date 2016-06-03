<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="p" tagdir="/WEB-INF/tags" %>
<%@ include file="/WEB-INF/views/fragments/head.jsp" %>
<body>

<header class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <a class="navbar-brand"
           href="${pageContext.request.contextPath}/computer/view/all"><spring:message
                code="header.title"/></a>
    </div>
</header>

<section id="main">
    <div class="container">
        <div id="login-box">

            <h1 id="titre"><spring:message code="login"/></h1>


            <c:url var="j_spring_security_check" value="/login"/>
            <form id="loginform" name='loginForm' class="form-horizontal"
                  action="${loginUrl}" method='POST'>

                <c:if test="${not empty error}">
                    <div class="error">${error}</div>
                </c:if>
                <c:if test="${not empty msg}">
                    <div class="msg">${msg}</div>
                </c:if>
                <div class="row">
                    <div class="form-group">
                        <label for="username" class="col-sm-4 control-label"><spring:message code="username"/> :</label>
                        <div class="col-sm-5">
                            <input id="username" class="form-control" type='text' name='username' value=''>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-sm-4 control-label"><spring:message code="password"/> :</label>
                        <div class="col-sm-5">
                            <input id="password" class="form-control" type='password' name='password'/>
                        </div>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                    <input class="btn btn-primary col-sm-offset-5 col-sm-2" name="submit" type="submit"
                           value="<spring:message code="submit"/>"/>
                </div>
            </form>
        </div>
    </div>
</section>

</body>
</html>