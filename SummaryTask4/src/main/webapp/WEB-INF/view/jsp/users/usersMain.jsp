<%@ include file="/WEB-INF/view/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/view/jspf/directive/taglib.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<jsp:include page="/WEB-INF/view/jspf/head.jspf">
	<jsp:param name="title" value="UserManagement" />
</jsp:include>

<body>
	<jsp:include page="/WEB-INF/view/jspf/header.jspf"></jsp:include>

	<div class="container">

		<div id="content">

			<span style="color: red">${sessionScope.nameValidationError}</span>
			<c:remove var="nameValidationError" scope="session" />
			<span style="color: red">${sessionScope.nameValidationError}</span>
			<c:remove var="nameValidationError" scope="session" />
			<span style="color: red">${sessionScope.nameValidationError}</span>
			<c:remove var="nameValidationError" scope="session" />
			<span style="color: red">${sessionScope.loginDuplicateInsert}</span>
			<c:remove var="loginDuplicateInsert" scope="session" />
			<span style="color: red">${sessionScope.passwordValidationError}</span>
			<c:remove var="passwordValidationError" scope="session" />
			<span style="color: red">${sessionScope.emailValidationError}</span>
			<c:remove var="emailValidationError" scope="session" />

			<span style="color: red">${sessionScope.emailDuplicateInsert}</span>
			<c:remove var="emailDuplicateInsert" scope="session" />
			<span style="color: red">${sessionScope.collegeValidationError}</span>
			<c:remove var="collegeValidationError" scope="session" />
			<span style="color: red">${sessionScope.experienceValidationError}</span>
			<c:remove var="experienceValidationError" scope="session" />
			
			
			<div role="tabpanel">

				<!-- Nav tabs -->
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a href="#students"
						aria-controls="students" role="tab" data-toggle="tab">Students</a></li>
					<li role="presentation"><a href="#teachers"
						aria-controls="profile" role="tab" data-toggle="tab">Teachers</a></li>
				</ul>

				<div class="tab-content">
					<div role="tabpanel" class="tab-pane fade in active" id="students">
						<table class="table table-hover">
							<caption>
								<br />${requestScope.students[0].role.description}
							</caption>
							<thead>
								<tr>
									<th data-field="name" data-align="center" data-sortable="true">Name</th>
									<th data-field="patronymic" data-align="center" data-sortable="true">Patronymic</th>
									<th data-field="sirname" data-align="center" data-sortable="true">Sirname</th>
									<th data-field="email" data-align="center" data-sortable="true">Email</th>
									<th data-field="login" data-align="center" data-sortable="true">Login</th>
									<th data-field="college" data-align="center" data-sortable="true">College</th>
									<th data-field="blocked" data-align="center" data-sortable="true">Blocked</th>
									<th data-field="action" data-align="center">
										<jsp:include page="/WEB-INF/view/jspf/popups/StudentRegister.jspf" />
									</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="student" items="${requestScope.students}">
									<tr>
										<td>${student.name}</td>
										<td>${student.patronymic}</td>
										<td>${student.sirname}</td>
										<td>${student.email}</td>
										<td>${student.login}</td>
										<td>${student.college}</td>
										<td>${student.blocked}</td>
										<td>
											<div class="container-fluid">
	    										<div class="row">
	    											<div class="col-md-3 col-md-offset-1">
														<form action="delete" method="post">
															<input type="text" hidden="true" name="id" value="${student.idUser}" />
															<input type="text" hidden="true" name="object" value="user" />
															<input type="text" hidden="true" name="command" value="block" />
															<input type="text" hidden="true" name="block" value="${student.blocked}" />
															<button type="submit" class="btn btn-default">block</button>
														</form>
													</div>
													<div class="col-md-3 col-md-offset-1">
														<form action="delete" method="post">
															<input type="text" hidden="true" name="id" value="${student.idUser}" />
															<input type="text" hidden="true" name="object" value="user" />
															<input type="text" hidden="true" name="command" value="delete" />
															<button type="submit" class="btn btn-default">delete</button>
														</form>
													</div>
													<div class="col-md-3 col-md-offset-1">
														<form action="update" method="post">
															<input type="text" hidden="true" name="role" value="student" />
															<input type="text" hidden="true" name="id" value="${student.idUser}" />
															<input type="text" hidden="true" name="name" value="${student.name}" />
															<input type="text" hidden="true" name="sirname" value="${student.sirname}" />
															<input type="text" hidden="true" name="patronymic" value="${student.patronymic}" />
															<input type="text" hidden="true" name="login" value="${student.login}" />
															<input type="text" hidden="true" name="password" value="${student.password}" />
															<input type="text" hidden="true" name="email" value="${student.email}" />
															<input type="text" hidden="true" name="blocked"	value="${student.blocked}" />
															<input type="text" hidden="true" name="college" value="${student.college}" />
															<button type="submit" class="btn btn-default">update</button>
														</form>
													</div>	
												</div>
											</div>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>


					<div role="tabpanel" class="tab-pane fade" id="teachers">
						<table class="table table-hover">
							<caption>
								<br />${requestScope.teachers[0].role.description}
							</caption>
							<thead>
								<tr>
									<th>Name</th>
									<th>Patronymic</th>
									<th>Sirname</th>
									<th>Email</th>
									<th>Login</th>
									<th>Specialization</th>
									<th>Experience</th>
									<th><jsp:include
											page="/WEB-INF/view/jspf/popups/TeacherRegister.jspf" /></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="teacher" items="${requestScope.teachers}">
									<tr>
										<td>${teacher.name}</td>
										<td>${teacher.patronymic}</td>
										<td>${teacher.sirname}</td>
										<td>${teacher.email}</td>
										<td>${teacher.login}</td>
										<td>${teacher.specialization}</td>
										<td>${teacher.experience}</td>
										<td>
											<div class="container-fluid">
		    									<div class="row">
			    									<div class="col-md-3">
														<form action="delete" method="post">
															<input type="text" hidden="true" name="id"
																value="${teacher.idUser}" /> <input type="text"
																hidden="true" name="object" value="user" /> <input
																type="text" hidden="true" name="command" value="delete" />
															<button type="submit" class="btn btn-default">delete</button>
														</form>
													</div>
													
													<div class="col-md-3">
														<form action="updateForm" method="post">
															<input type="text" hidden="true" name="role" value="teacher" />
															<input type="text" hidden="true" name="id" value="${teacher.idUser}" />
															<input type="text" hidden="true" name="name" value="${teacher.name}" />
															<input type="text" hidden="true" name="sirname" value="${teacher.sirname}" />
															<input type="text" hidden="true" name="patronymic" value="${teacher.patronymic}" />
															<input type="text" hidden="true" name="login" value="${teacher.login}" />
															<input type="text" hidden="true" name="password" value="${teacher.password}" />
															<input type="text" hidden="true" name="email" value="${teacher.email}" />
															<input type="text" hidden="true" name="specialization" value="${teacher.specialization}" />
															<input type="text" hidden="true" name="experience" value="${teacher.experience}" />
															<button type="submit" class="btn btn-default">update</button>
														</form>
													</div>
												</div>
											</div>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>

	</div>

	<jsp:include page="/WEB-INF/view/jspf/footer.jspf"></jsp:include>

</body>
</html>