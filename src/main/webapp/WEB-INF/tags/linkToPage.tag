<%@ tag body-content="scriptless" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="target" required="true"%>
<%@ attribute name="page" required="true" type="java.lang.String"%>
<%@ attribute name="nbElementPage" required="true"%>


<a
	href="${pageContext.request.contextPath}/${target}?nbElementPage=${nbElementPage != null ? nbElementPage:'10'}&page=${page != null ? page  :'1'}">
	<jsp:doBody></jsp:doBody>
</a>