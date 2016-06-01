<%@ tag body-content="scriptless" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="target" required="true"%>
<%@ attribute name="pageNumber" required="false" type="java.lang.String"%>
<%@ attribute name="nbElementPage" required="false"%>
<%@ attribute name="orderBy" required="false"%>
<%@ attribute name="orderSort" required="false"%>
<%@ attribute name="search" required="false"%>

<a
	href="${pageContext.request.contextPath}/${target}?
	<c:if test="${nbElementPage != null && nbElementPage != ''}">
	nbElementPage=${nbElementPage}
	</c:if>
	<c:if test="${pageNumber != null && pageNumber != ''}">
	&page=${pageNumber}
	</c:if>
	<c:if test="${not empty orderSort }">
	&orderSort=${orderSort}
	</c:if>
	<c:if test="${not empty orderBy}">
	&orderBy=${orderBy}
	</c:if>	
	<c:if test="${not empty search}">
	&search=${search}
	</c:if>
	">
	<jsp:doBody></jsp:doBody>
</a>