<%@ include file="/WEB-INF/view/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/view/jspf/directive/taglib.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" media="screen"
	href="css/bootstrap.min.css" />
<title>Courses</title>
</head>
<body>
	<%@ include file="/WEB-INF/view/jspf/header.jspf"%>

	<div class="container">
		<div class="row">
			<c:forEach var="theme" items="${requestScope.themes}">
				<div class="col-sm-6 col-md-4">
					<div class="thumbnail">
						<div class="caption">
							<h3>${theme.name}</h3>
							<p>${theme.description}</p>
							<p>
								<a href="?theme=${theme.id}" class="btn btn-primary"
									role="button">Courses</a>
							</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/view/jspf/footer.jspf"%>
</body>
</html>