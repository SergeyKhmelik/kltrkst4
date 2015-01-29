<%@ include file="/WEB-INF/view/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/view/jspf/directive/taglib.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" media="screen"
	href="css/bootstrap.min.css" />
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script>
<title>User Management</title>
</head>
<body>

	<%@ include file="/WEB-INF/view/jspf/header.jspf"%>

	<div class="container">

		<!-------->
		<div id="content">


			<h1>students</h1>
			<table border="1px">
				<thead>
					<tr>
						<td>id</td>
						<td>Name</td>
						<td>Patronymic</td>
						<td>Sirname</td>
						<td>Email</td>
						<td>Login</td>
						<td>Password</td>
						<td>College</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="student" items="${requestScope.students}">
						<tr>
							<td>${student.id}</td>
							<td>${student.name}</td>
							<td>${student.patronymic}</td>
							<td>${student.sirname}</td>
							<td>${student.email}</td>
							<td>${student.login}</td>
							<td>${student.password}</td>
							<td>${student.college}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<h1>teachers</h1>
			<table border="1px">
				<thead>
					<tr>
						<td>id</td>
						<td>Name</td>
						<td>Patronymic</td>
						<td>Sirname</td>
						<td>Email</td>
						<td>Login</td>
						<td>Password</td>
						<td>Specialization</td>
						<td>Experience</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="teacher" items="${requestScope.teachers}">
						<tr>
							<td>${teacher.id}</td>
							<td>${teacher.name}</td>
							<td>${teacher.patronymic}</td>
							<td>${teacher.sirname}</td>
							<td>${teacher.email}</td>
							<td>${teacher.login}</td>
							<td>${teacher.password}</td>
							<td>${teacher.specialization}</td>
							<td>${teacher.experience}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
	</div>

	<%@ include file="/WEB-INF/view/jspf/footer.jspf"%>
</body>
</html>