<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/fragments/head.jsp"%>
<body>
	<%@ include file="/WEB-INF/views/fragments/header.jsp"%>

	<section id="main">
		<div class="container">
			<div class="alert alert-danger">
				Error 500: An error has occured! <br />
				<!-- stacktrace -->
			</div>
		</div>
	</section>

	<script src="../js/jquery.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/dashboard.js"></script>

</body>
</html>