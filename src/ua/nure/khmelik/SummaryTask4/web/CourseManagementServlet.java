package ua.nure.khmelik.SummaryTask4.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.khmelik.SummaryTask4.service.CourseService;

public class CourseManagementServlet extends HttpServlet {

    private static final long serialVersionUID = 5128155184892996667L;

    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);

    private CourseService courseService;

    @Override
    public void init() throws ServletException {
	courseService = (CourseService) getServletContext().getAttribute(
		"courseService");

	if (courseService == null) {
	    LOGGER.error("Could not get services from appcontext");
	    throw new UnavailableException("Couldn`t get DAO");
	}
    }

    @Override
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {

	response.setContentType("text/html");

	LOGGER.info("Entered courses servlet");

	if (request.getAttribute("course") == null) {
	    try {
		request.setAttribute("themes", courseService.getCourseThemes());
		request.getRequestDispatcher("/courses").forward(request,
			response);
	    } catch (SQLException e) {
		// REDIRECT NA "SORRY PAGE"
		LOGGER.error("Exception during users authorization.");
		return;
	    }
	} else {
	    request.getRequestDispatcher("/").forward(request, response);
	}
    }

}
