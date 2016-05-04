<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="p" tagdir="/WEB-INF/tags"%>
<%@ include file="/WEB-INF/views/fragments/head.jsp"%>
<body>

	<%@include file="/WEB-INF/views/fragments/header.jsp"%>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">${page.nbElementTotal}&nbsp;Computer${page.nbElementTotal > 1 ? 's' : ''}&nbsp;found</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">
						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="Search name" /> <input
							type="submit" id="searchsubmit" value="Filter by name"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer"
						href="${pageContext.request.contextPath}/computer/add">Add
						Computer</a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();">Edit</a>
				</div>
			</div>
		</div>

		<form id="deleteForm"
			action="${pageContext.request.contextPath}/computer/delete"
			method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<!-- Table header for Computer Name -->
						<th><p:link target="computer/view/all"
								orderSort="${orderSort == 'asc' ? 'desc' : 'asc'}"
								search="${search}" orderBy="name">Computer name</p:link>
								<c:if
								test="${orderBy == 'name'}">
								<span class="${orderSort}"></span>
							</c:if></th>
						<th><p:link target="computer/view/all"
								orderSort="${orderSort == 'asc' ? 'desc' : 'asc'}"
								search="${search}" orderBy="introduced">Introduced date</p:link>
							<c:if test="${orderBy eq 'introduced'}">
								<span class="${orderSort}"></span>
							</c:if></th>
						<!-- Table header for Discontinued Date -->
						<th><p:link target="computer/view/all"
								orderSort="${orderSort == 'asc' ? 'desc' : 'asc'}"
								search="${search}" orderBy="discontinued">Discontinued date</p:link>
							<c:if test="${orderBy eq 'discontinued'}">
								<span class="${orderSort}"></span>
							</c:if></th>
						<!-- Table header for Company -->
						<th><p:link target="computer/view/all"
								orderSort="${orderSort == 'asc' ? 'desc' : 'asc'}"
								search="${search}" orderBy="company">Company</p:link> <c:if
								test="${orderBy eq 'company'}">
								<span class="${orderSort}"></span>
							</c:if></th>

					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach var="computer" items="${page.elements}">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${computer.id}"></td>
							<td><a
								href="${pageContext.request.contextPath}/computer/edit?id=<c:out value="${computer.id}">
										</c:out>"
								onclick=""><c:out value="${computer.name}"></c:out></a></td>
							<td>${computer.introduced }</td>
							<td>${computer.discontinued}</td>
							<td>${computer.nameCompany}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<p:footer page="${page }" />


	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/dashboard.js"></script>


</body>
</html>