<%@ include file="/WEB-INF/view/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/view/jspf/directive/taglib.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<jsp:useBean id="user"
	class="ua.nure.khmelik.SummaryTask4.entity.dbentities.User"
	scope="session"></jsp:useBean>


<jsp:include page="/WEB-INF/view/jspf/head.jspf">
	<jsp:param value="title" name="SummaryTask4" />
</jsp:include>

<body>

	<%@ include file="/WEB-INF/view/jspf/header.jspf"%>


	<%@ include file="/WEB-INF/view/jspf/footer.jspf"%>

	<!-- Course news, for example -->

</body>
</html>