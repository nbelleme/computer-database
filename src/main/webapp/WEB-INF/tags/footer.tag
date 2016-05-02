<%@ tag body-content="empty" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="p" tagdir="/WEB-INF/tags"%>
<%@ attribute name="current" required="false"%>
<%@ attribute name="count" required="false"%>
<%@ attribute name="page" required="true" type="com.excilys.ui.Page"%>

<footer class="navbar-fixed-bottom">
	<div class="container text-center">
		<ul class="pagination">
			<li><p:linkToPage nbElementPage="${page.nbElementPage}"
					page="${(page.nbCurrentPage -1 > 0) ? page.nbCurrentPage -1 : 1}"
					target="computer/view/all">&laquo;</p:linkToPage></li>
			<li><p:linkToPage nbElementPage="${page.nbElementPage}" page="1"
					target="computer/view/all">1</p:linkToPage></li>
			<c:choose>
				<c:when test="${ page.nbPageTotal > 5 }">
					<c:choose>
						<c:when test="${ page.nbCurrentPage < 4 }">
							<li><p:linkToPage nbElementPage="${page.nbElementPage}"
									page="2" target="computer/view/all">2</p:linkToPage></li>
							<li><p:linkToPage nbElementPage="${page.nbElementPage}"
									page="3" target="computer/view/all">3</p:linkToPage></li>
							<li><p:linkToPage nbElementPage="${page.nbElementPage}"
									page="4" target="computer/view/all">4</p:linkToPage></li>
							<li><p:linkToPage nbElementPage="${page.nbElementPage}"
									page="5" target="computer/view/all">5</p:linkToPage></li>
							<li><span aria-hidden="true">&hellip;</span></li>
						</c:when>

						<c:when test="${ page.nbCurrentPage > page.nbPageTotal - 4 }">
							<li><span aria-hidden="true">&hellip;</span></li>
							<li><p:linkToPage nbElementPage="${page.nbElementPage}"
									page="${ page.nbPageTotal - 4 }" target="computer/view/all">
									${page.nbPageTotal - 4 }</p:linkToPage></li>
							<li><p:linkToPage nbElementPage="${page.nbElementPage}"
									page="${ page.nbPageTotal - 3 }" target="computer/view/all">
									${page.nbPageTotal - 3 }</p:linkToPage></li>
							<li><p:linkToPage nbElementPage="${page.nbElementPage}"
									page="${ page.nbPageTotal - 2 }" target="computer/view/all">
									${page.nbPageTotal - 2 }
									</p:linkToPage></li>
							<li><p:linkToPage nbElementPage="${page.nbElementPage}"
									page="${ page.nbPageTotal - 1 }" target="computer/view/all">
									${page.nbPageTotal - 1 }</p:linkToPage></li>
						</c:when>

						<c:otherwise>
							<li><span aria-hidden="true">&hellip;</span></li>
							<li><p:linkToPage nbElementPage="${page.nbElementPage}"
									page="${ page.nbCurrentPage -1 }" target="computer/view/all">
									${page.nbCurrentPage - 1 }</p:linkToPage></li>
							<li class="active"><p:linkToPage
									nbElementPage="${page.nbElementPage}"
									page="${ page.nbCurrentPage}" target="computer/view/all">
									${page.nbCurrentPage }</p:linkToPage></li>
							<li><p:linkToPage nbElementPage="${page.nbElementPage}"
									page="${ page.nbCurrentPage + 1 }" target="computer/view/all">${page.nbCurrentPage + 1 }</p:linkToPage></li>
							<li><span aria-hidden="true">&hellip;</span></li>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:when test="${ page.nbPageTotal > 2}">
					<c:forEach begin="1" end="${ page.nbPageTotal - 2 }" var="i">
						<li><p:linkToPage nbElementPage="${page.nbElementPage}"
								page="${i}" target="computer/view/all">
									${ i + 1 }</p:linkToPage>
					</c:forEach>
				</c:when>


			</c:choose>

			<c:if test="${ page.nbPageTotal > 1 }">
				<li><p:linkToPage nbElementPage="${page.nbElementPage}"
						page="${page.nbPageTotal }" target="computer/view/all">${ page.nbPageTotal }</p:linkToPage>
					</a></li>
			</c:if>

			<li><p:linkToPage nbElementPage="${page.nbElementPage}"
					page="${(page.nbCurrentPage + 1 <= page.nbPageTotal) ? page.nbCurrentPage + 1 : page.nbPageTotal}"
					target="computer/view/all">&raquo;</p:linkToPage></span>
		</ul>

		<div class="btn-group btn-group-sm pull-right" role="group">
			<p:linkToPage nbElementPage="10" page="${page.nbCurrentPage}"
				target="computer/view/all">
				<button type="button" class="btn btn-default">10</button>
			</p:linkToPage>
			<p:linkToPage nbElementPage="50" page="${page.nbCurrentPage}"
				target="computer/view/all">
				<button type="button" class="btn btn-default">50</button>
			</p:linkToPage>
			<p:linkToPage nbElementPage="100" page="${page.nbCurrentPage}"
				target="computer/view/all">
				<button type="button" class="btn btn-default">100</button>
			</p:linkToPage>
		</div>
	</div>
</footer>