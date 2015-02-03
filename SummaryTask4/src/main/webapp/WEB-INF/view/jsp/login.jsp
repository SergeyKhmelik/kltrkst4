<%@ include file="/WEB-INF/view/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/view/jspf/directive/taglib.jspf"%>

<html>
<jsp:include page="/WEB-INF/view/jspf/head.jspf">
	<jsp:param value="Welcome" name="title" />
</jsp:include>
<body>
	<div id="loginModal" class="modal show" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<span style="color: red;">${sessionScope.loginerror}</span>
					<c:remove var="loginerror" scope="session"/>
					<h1 class="text-center">Login</h1>
				</div>
				<div class="modal-body">
					<form class="form col-md-12 center-block" action="loginservlet"
						method="post">

						<div class="form-group">
							<input type="text" class="form-control input-lg"
								placeholder="Login" name="login" /> <span style="color: red;">${sessionScope.loginvalidation}
							</span>
							<c:remove var="loginvalidation" scope="session"/>
						</div>

						<div class="form-group">
							<input type="password" class="form-control input-lg"
								placeholder="Password" name="password" />
						</div>

						<div class="form-group">
							<button type="submit" class="btn btn-primary btn-lg btn-block">Sign
								In</button>
						</div>

					</form>
				</div>
				<div class="modal-footer">
					<div class="col-md-12"></div>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/view/jspf/footer.jspf"%>

</body>
</html>