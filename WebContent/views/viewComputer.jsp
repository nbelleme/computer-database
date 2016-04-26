<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/css/font-awesome.css"
	rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/css/main.css"
	rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand"
				href="${pageContext.request.contextPath}/views/dashboard.html">
				Application - Computer Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<table class="table table-striped table-bordered">
				<tr>
					<th>Computer Name</th>
					<th>Introduced Date</th>
					<th>Discontinued Date</th>
					<th>Company</th>
				</tr>
				<tr>
					<td><c:out value="${computer.name}"></c:out></td>
					<td><c:choose>
							<c:when test="${not empty computer.introduced}">
								<c:out value="${computer.introduced }">
								</c:out>
							</c:when>
							<c:when test="${empty computer.introduced}">
					N/C
				</c:when>
						</c:choose></td>
					<td><c:choose>
							<c:when test="${not empty computer.discontinued}">
								<c:out value="${computer.discontinued }">
								</c:out>
							</c:when>
							<c:when test="${empty computer.discontinued}">
					N/C
				</c:when>
						</c:choose></td>

					<td><c:choose>
							<c:when test="${not empty computer.company}">
								<c:out value="${computer.company.name }">
								</c:out>
							</c:when>
							<c:when test="${empty computer.company}">N/C</c:when>
						</c:choose></td>
				</tr>
			</table>
		</div>
	</section>

	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/dashboard.js"></script>

</body>
</html>