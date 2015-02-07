<%@ include file="/WEB-INF/view/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/view/jspf/directive/taglib.jspf"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<jsp:include page="/WEB-INF/view/jspf/head.jspf">
	<jsp:param value="Update" name="title"/>
</jsp:include>

<body>
	<div id="loginModal" class="modal show" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
			
				<div class="modal-header">
					<h1 class="text-center">Update ${requestScope.user.role.name}</h1>
				</div>
				
				<div class="modal-body">
					<form class="form col-md-12 center-block" action="userRegister"
						method="post">

					<div class="form-group">
						<input type="text" class="form-control input-lg"
							value="${requestScope.user.name }" name="name" />
					</div>

					<div class="form-group">
						<input type="text" class="form-control input-lg"
							value="${requestScope.user.patronymic}" name="patronymic" />
					</div>

					<div class="form-group">
						<input type="text" class="form-control input-lg"
							value="${requestScope.user.sirname }" name="sirname" />
					</div>

					<div class="form-group">
						<input type="text" class="form-control input-lg"
							value="${requestScope.user.login }" name="login" />
					</div>

					<div class="form-group">
						<input type="text" class="form-control input-lg"
							value="${requestScope.user.password }" name="password" />
					</div>

					<div class="form-group">
						<span class="input-group-addon" id="basic-addon1">@
						<input type="text" class="form-control" value="${requestScope.user.email }" 
						aria-describedby="basic-addon1" name="email"/></span> 
					</div>

					<c:if test="${requestScope.user.role.name eq 'student'}">
						<div class="form-group">
							<input type="text" class="form-control input-lg"
								value="${requestScope.user.college}" name="college" />
						</div>
					</c:if>
					
					<c:if test="${requestScope.user.role.name eq 'teacher'}">
						<div class="form-group">
							<input type="text" class="form-control input-lg"
								value="${requestScope.user.specialization}" name="specialization" />
						</div>

						<div class="form-group">
							<input type="number" class="form-control input-lg"
								value="${requestScope.user.experience}" name="experience" />
						</div>
					</c:if>
					
					<div class="form-group" hidden="true">
						<input type="text" class="form-control input-lg" name="role" value="${requestScope.user.role.name}">
					</div>

					<div class="form-group" hidden="true">
						<input type="text" class="form-control input-lg" name="command" value="update">
					</div>

					<div class="form-group" hidden="true">
						<input type="text" class="form-control input-lg" name="id" value="${requestScope.user.idUser}">
					</div>
					

					<div class="form-group">
						<button type="submit" class="btn btn-primary btn-lg btn-block">Update</button>
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