<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="p" tagdir="/WEB-INF/tags"%>
<%@ include file="/WEB-INF/views/fragments/head.jsp"%>
<body>
	<%@ include file="/WEB-INF/views/fragments/header.jsp"%>

	<section id="main">
		<div class="container">
			<div class="alert alert-danger">
				Error 404: Page not found. Too bad bitch! <br />
				<!-- stacktrace -->
			</div>
		</div>
	</section>
</body>
</html>