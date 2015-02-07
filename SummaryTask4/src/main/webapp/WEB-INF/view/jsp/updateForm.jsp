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
					<h1 class="text-center">Update</h1>
				</div>
				
					<div class="modal-body">
						<form class="form col-md-12 center-block" action="userRegister"
							method="post">	

						<div class="form-group">
							<input type="text" class="form-control input-lg"
								value="${param.name}" name="name" />
						</div>
	
						<div class="form-group">
							<input type="text" class="form-control input-lg"
								value="${param.patronymic}" name="patronymic" />
						</div>
	
						<div class="form-group">
							<input type="text" class="form-control input-lg"
								value="${param.sirname}" name="sirname" />
						</div>
	
						<div class="form-group">
							<input type="text" class="form-control input-lg"
								value="${param.login}" name="login" />
						</div>

						<div class="form-group">
							<input type="text" class="form-control input-lg"
								value="${param.password}" name="password" />
						</div>

						<div class="form-group">
							<span class="input-group-addon" id="basic-addon1">@
							<input type="text" class="form-control" value="${param.email }" 
							aria-describedby="basic-addon1" name="email"/></span> 
						</div>

						<c:if test="${param.role eq 'student'}">
							<div class="form-group">
								<input type="text" class="form-control input-lg"
									value="${param.college}" name="college" />
							</div>
						</c:if>
					
						<c:if test="${param.role eq 'teacher'}">
							<div class="form-group">
								<input type="text" class="form-control input-lg"
									value="${param.specialization}" name="specialization" />
							</div>

							<div class="form-group">
								<input type="number" class="form-control input-lg"
									value="${param.experience}" name="experience" />
							</div>
						</c:if>
					
						<div class="form-group" hidden="true">
							<input type="text" class="form-control input-lg" name="role" value="${param.role}">
						</div>

						<div class="form-group" hidden="true">
							<input type="text" class="form-control input-lg" name="command" value="update">
						</div>

						<div class="form-group" hidden="true">
							<input type="text" class="form-control input-lg" name="id" value="${param.id}">
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