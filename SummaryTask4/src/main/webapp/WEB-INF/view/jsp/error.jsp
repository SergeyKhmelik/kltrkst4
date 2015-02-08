<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<jsp:include page="/WEB-INF/view/jspf/head.jspf">
	<jsp:param value="Error" name="title" />
</jsp:include>

<body>
	<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="error-template">
                <h1>Oops!</h1>
                <h2>Server error</h2>
                <div class="error-details">
                    Sorry, an error has occured due to server issues. Please try again later!
                </div>
                <div class="error-actions">
                    <a href="/SummaryTask4/main" class="btn btn-primary btn-lg">Home page</a>
                </div>
            </div>
        </div>
    </div>
</div>
	
</body>
</html>