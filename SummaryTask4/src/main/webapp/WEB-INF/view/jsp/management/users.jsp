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

		<!-------->
		<div id="content">


			<table class="table table-hover">
				<caption>Students</caption>
				<thead>
					<tr>
						<th>id</th>
						<th>Name</th>
						<th>Patronymic</th>
						<th>Sirname</th>
						<th>Email</th>
						<th>Login</th>
						<th>Password</th>
						<th>College</th>
						<th>Blocked</th>
						<th><jsp:include page="/WEB-INF/view/jspf/popups/StudentRegister.jspf" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="student" items="${requestScope.students}">
						<tr>
							<td>${student.idUser}</td>
							<td>${student.name}</td>
							<td>${student.patronymic}</td>
							<td>${student.sirname}</td>
							<td>${student.email}</td>
							<td>${student.login}</td>
							<td>${student.password}</td>
							<td>${student.college}</td>
							<td>${student.blocked}</td>
							<td>
								<button type="button" class="btn btn-default"
									onclick="block(${student.idUser})">Block</button>
								<button type="button" class="btn btn-default"
									onclick="update(${student.idUser})">Update</button>
								<button type="button" class="btn btn-default"
									onclick="delete(${student.idUser})">X</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<table class="table table-hover">
				<caption>Teachers</caption>
				<thead>
					<tr>
						<th>id</th>
						<th>Name</th>
						<th>Patronymic</th>
						<th>Sirname</th>
						<th>Email</th>
						<th>Login</th>
						<th>Password</th>
						<th>Specialization</th>
						<th>Experience</th>
						<th><jsp:include page="/WEB-INF/view/jspf/popups/StudentRegister.jspf" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="teacher" items="${requestScope.teachers}">
						<tr>
							<td>${teacher.idUser}</td>
							<td>${teacher.name}</td>
							<td>${teacher.patronymic}</td>
							<td>${teacher.sirname}</td>
							<td>${teacher.email}</td>
							<td>${teacher.login}</td>
							<td>${teacher.password}</td>
							<td>${teacher.specialization}</td>
							<td>${teacher.experience}</td>
							<td>
								<button type="button" class="btn btn-default"
									onclick="update(${teacher.idUser})">Update</button>
								<button type="button" class="btn btn-default"
									onclick="delete(${teacher.idUser})">x</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
	</div>

	<jsp:include page="/WEB-INF/view/jspf/footer.jspf"></jsp:include>

</body>
</html>