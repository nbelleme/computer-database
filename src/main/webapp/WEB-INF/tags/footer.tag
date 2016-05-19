<%@ tag body-content="empty" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="p" tagdir="/WEB-INF/tags"%>
<%@ attribute name="current" required="false"%>
<%@ attribute name="count" required="false"%>
<%@ attribute name="page" required="true" type="com.excilys.ui.Page"%>

<footer class="navbar-fixed-bottom">
	<div class="container text-center">
		<ul class="pagination">
			<li><p:link nbElementPage="${page.nbElementPage}"
					pageNumber="${(page.nbCurrentPage -1 > 0) ? page.nbCurrentPage -1 : 1}"
					target="computer/view/all">&laquo;</p:link></li>
			<li><p:link nbElementPage="${page.nbElementPage}" pageNumber="1"
					target="computer/view/all">1</p:link></li>
			<c:choose>
				<c:when test="${ page.nbPageTotal > 5 }">
					<c:choose>
						<c:when test="${ page.nbCurrentPage < 4 }">
							<li><p:link nbElementPage="${page.nbElementPage}"
									pageNumber="2" target="computer/view/all">2</p:link></li>
							<li><p:link nbElementPage="${page.nbElementPage}"
									pageNumber="3" target="computer/view/all">3</p:link></li>
							<li><p:link nbElementPage="${page.nbElementPage}"
									pageNumber="4" target="computer/view/all">4</p:link></li>
							<li><p:link nbElementPage="${page.nbElementPage}"
									pageNumber="5" target="computer/view/all">5</p:link></li>
							<li><span aria-hidden="true">&hellip;</span></li>
						</c:when>

						<c:when test="${ page.nbCurrentPage > page.nbPageTotal - 4 }">
							<li><span aria-hidden="true">&hellip;</span></li>
							<li><p:link nbElementPage="${page.nbElementPage}"
									pageNumber="${ page.nbPageTotal - 4 }" target="computer/view/all">
									${page.nbPageTotal - 4 }</p:link></li>
							<li><p:link nbElementPage="${page.nbElementPage}"
									pageNumber="${ page.nbPageTotal - 3 }" target="computer/view/all">
									${page.nbPageTotal - 3 }</p:link></li>
							<li><p:link nbElementPage="${page.nbElementPage}"
									pageNumber="${ page.nbPageTotal - 2 }" target="computer/view/all">
									${page.nbPageTotal - 2 }
									</p:link></li>
							<li><p:link nbElementPage="${page.nbElementPage}"
									pageNumber="${ page.nbPageTotal - 1 }" target="computer/view/all">
									${page.nbPageTotal - 1 }</p:link></li>
						</c:when>

						<c:otherwise>
							<li><span aria-hidden="true">&hellip;</span></li>
							<li><p:link nbElementPage="${page.nbElementPage}"
									pageNumber="${ page.nbCurrentPage -1 }" target="computer/view/all">
									${page.nbCurrentPage - 1 }</p:link></li>
							<li class="active"><p:link
									nbElementPage="${page.nbElementPage}"
									pageNumber="${ page.nbCurrentPage}" target="computer/view/all">
									${page.nbCurrentPage }</p:link></li>
							<li><p:link nbElementPage="${page.nbElementPage}"
									pageNumber="${ page.nbCurrentPage + 1 }" target="computer/view/all">${page.nbCurrentPage + 1 }</p:link></li>
							<li><span aria-hidden="true">&hellip;</span></li>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:when test="${ page.nbPageTotal > 2}">
					<c:forEach begin="1" end="${ page.nbPageTotal - 2 }" var="i">
						<li><p:link nbElementPage="${page.nbElementPage}"
								pageNumber="${i}" target="computer/view/all">
									${ i + 1 }</p:link>
					</c:forEach>
				</c:when>


			</c:choose>

			<c:if test="${ page.nbPageTotal > 1 }">
				<li><p:link nbElementPage="${page.nbElementPage}"
						pageNumber="${page.nbPageTotal }" target="computer/view/all">${ page.nbPageTotal }</p:link>
					</li>
			</c:if>

			<li><p:link nbElementPage="${page.nbElementPage}"
					pageNumber="${(page.nbCurrentPage + 1 <= page.nbPageTotal) ? page.nbCurrentPage + 1 : page.nbPageTotal}"
					target="computer/view/all">&raquo;</p:link>
		</ul>

		<div class="btn-group btn-group-sm pull-right" role="group">
			<p:link nbElementPage="10" pageNumber="${page.nbCurrentPage}"
				target="computer/view/all">
				<button type="button" class="btn btn-default">10</button>
			</p:link>
			<p:link nbElementPage="50" pageNumber="${page.nbCurrentPage}"
				target="computer/view/all">
				<button type="button" class="btn btn-default">50</button>
			</p:link>
			<p:link nbElementPage="100" pageNumber="${page.nbCurrentPage}"
				target="computer/view/all">
				<button type="button" class="btn btn-default">100</button>
			</p:link>
		</div>
	</div>
</footer>