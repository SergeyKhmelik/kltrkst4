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
			<div role="tabpanel">
			
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a href="#passed"
						aria-controls="students" role="tab" data-toggle="tab">Passed</a></li>
					<li role="presentation"><a href="#current"
						aria-controls="profile" role="tab" data-toggle="tab">Current</a></li>
					<li role="presentation"><a href="#incoming"
						aria-controls="profile" role="tab" data-toggle="tab">Incoming</a></li>
				</ul>

				<div class="tab-content">
					<div role="tabpanel" class="tab-pane fade in active" id="passed">
						<div class="container">
							<div class="row">
								<c:forEach var="course" items="${requestScope.passedCourses}">
									<div class="col-sm-6 col-md-4">
										<div class="thumbnail">
											<div class="caption">
												<h3>${course.key.name}</h3>
												<p>${course.key.description}</p>
												<p>
													<a href="?theme=${course.key.id}&type=passed" class="btn btn-primary"
														role="button">Courses</a>
												</p>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
					
					<div role="tabpanel" class="tab-pane fade" id="current">
						<div class="container">
							<div class="row">
								<c:forEach var="course" items="${requestScope.currentCourses}">
									<div class="col-sm-6 col-md-4">
										<div class="thumbnail">
											<div class="caption">
												<h3>${course.key.name}</h3>
												<p>${course.key.description}</p>
												<p>
														<a href="?theme=${course.key.id}&type=current" class="btn btn-primary"
															role="button">Courses</a>
												</p>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
					
					<div role="tabpanel" class="tab-pane fade" id="incoming">
						<div class="container">
							<div class="row">
								<c:forEach var="course" items="${requestScope.incomingCourses}">
									<div class="col-sm-6 col-md-4">
										<div class="thumbnail">
											<div class="caption">
												<h3>${course.key.name}</h3>
												<p>${course.key.description}</p>
												<p>
														<a href="?theme=${course.key.id}&type=current" class="btn btn-primary"
															role="button">Courses</a>
												</p>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>	
				</div>
			</div>
		</div>
	</div>
		
	
	<%@ include file="/WEB-INF/view/jspf/footer.jspf"%>
</body>
</html>