<%@ include file="/WEB-INF/view/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/view/jspf/directive/taglib.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/WEB-INF/view/jspf/head.jspf">
	<jsp:param value="title" name="Courses"/>
</jsp:include>

<body>
	<%@ include file="/WEB-INF/view/jspf/header.jspf"%>

	<div class="container">
		<div id="content">
			<!-- Отображать курсы, которые пришли с сервлета в таблице -->
			
			<c:choose>
				<c:when test="${fn:length(requestScope.courses) == 0 }">
					<h3>No courses found for query: ${requestScope.time}</h3>
				</c:when>
				
				<c:otherwise>
					<table class="table table-hover">
						<caption>
							<h3>${requestScope.time}</h3>
						</caption>
						<thead>
							<tr>
								<th>Name
									<div class="btn-group-vertical" role="group">
										<button>^</button>
										<button>v</button>
									</div>
								</th>
								
								<th>Theme
									<div class="btn-group-vertical" role="group">
										<button>^</button>
										<button>v</button>
									</div>
								</th>
								
								<th>Start
									<div class="btn-group-vertical" role="group">
										<button>^</button>
										<button>v</button>
									</div>
								</th>
								
								<th>End
									<div class="btn-group-vertical" role="group">
										<button>^</button>
										<button>v</button>
									</div>
								</th>
				
								<th>Teacher
									<div class="btn-group-vertical" role="group">
										<button>^</button>
										<button>v</button>
									</div>
								</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="course" items="${requestScope.courses}">
								<tr>
									<td>${course.name}</td>
									<td>${course.theme.name}</td>
									<td>${course.start}</td>
									<td>${course.end}</td>
									<td>${course.teacher.name} ${course.teacher.patronymic} ${course.teacher.sirname}</td>
									<td>
										<div class="container-fluid">
	    									<div class="row">
	    										<!-- DELETE -->
	    										<div class="col-md-3 col-md-offset-1">
													<form action="delete" method="post">
														<input type="text" hidden="true" name="id" value="${course.idCourse}" />
														<input type="text" hidden="true" name="object" value="course" />
														<input type="text" hidden="true" name="command" value="delete" />
														<button type="submit" class="btn btn-default">delete</button>
													</form>
												</div>
												
												<!-- UPDATE -->
												<div class="col-md-3 col-md-offset-1">
													<form action="updateCourse" method="post">
														<input type="text" hidden="true" name="command" value="general" />														
														<input type="text" hidden="true" name="id" value="${course.idCourse}" />
														<input type="text" hidden="true" name="name" value="${course.name }" />
														<input type="text" hidden="true" name="name" value="${course.start }" />
														<input type="text" hidden="true" name="name" value="${course.end }" />
														<button type="submit" class="btn btn-default">update</button>
													</form>
												</div>
												
												<!-- JOURNAL -->
												<div class="col-md-3 col-md-offset-1">
													<form action="getJournal" method="post">
														<input type="text" hidden="true" name="id" value="${course.idCourse}" />
														<button type="submit" class="btn btn-default">journal</button>
													</form>
												</div>
												
												<!-- Set theme DROPLIST WITH BUTTONS -->
												<div class="col-md-3 col-md-offset-1">
													<form action="updateCourse" method="post">
														<input type="text" hidden="true" name="command" value="theme" />														
														<input type="text" hidden="true" name="id" value="${course.idCourse}" />
														<input type="text" hidden="true" name="name" value="${course.theme.id }" />
														<button type="submit" class="btn btn-default">update</button>
													</form>
												</div>

												<!-- Set teacher DROPLIST WITH BUTTONS -->																									
												<div class="col-md-3 col-md-offset-1">
													<form action="updateCourse" method="post">
														<input type="text" hidden="true" name="command" value="teacher" />														
														<input type="text" hidden="true" name="id" value="${course.idCourse}" />
														<input type="text" hidden="true" name="name" value="${course.name }" />
														<input type="text" hidden="true" name="name" value="${course.start }" />
														<input type="text" hidden="true" name="name" value="${course.end }" />
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
				</c:otherwise>
			</c:choose>			
		</div>
	</div>
	
	<%@ include file="/WEB-INF/view/jspf/footer.jspf"%>
</body>
</html>