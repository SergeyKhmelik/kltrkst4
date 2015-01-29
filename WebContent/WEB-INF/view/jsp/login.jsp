<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Добро пожаловать</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="css/bootstrap.min.css" rel="stylesheet">

<style type="text/css">
.modal-footer {
	border-top: 0px;
}
</style>
</head>

<body>
	<div id="loginModal" class="modal show" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="text-center">Login</h1>
				</div>
				<div class="modal-body">
					<form class="form col-md-12 center-block" action="login"
						method="post">

						<div class="form-group">
							<input type="text" class="form-control input-lg"
								placeholder="Login" name="login">
						</div>

						<div class="form-group">
							<input type="password" class="form-control input-lg"
								placeholder="Password" name="password">
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