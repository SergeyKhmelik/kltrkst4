package ua.nure.khmelik.SummaryTask4.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.khmelik.SummaryTask4.service.CourseService;
import ua.nure.khmelik.SummaryTask4.service.UserService;

public class DeleteServlet extends HttpServlet {

    private static final long serialVersionUID = -7740515957618244962L;
    private static final Logger LOGGER = Logger.getLogger(DeleteServlet.class);
    private static final String USER_OBJECT = "user";
    private static final String COURSE_OBJECT = "course";
    private static final Object BLOCK_COMMAND = "block";
    private static final Object DELETE_COMMAND = "delete";

    private UserService userService;
    private CourseService courseService;

    @Override
    public void init() throws ServletException {
	userService = (UserService) getServletContext().getAttribute(
		"userService");
	if (userService == null) {
	    LOGGER.error("Could not get services from appcontext");
	    throw new UnavailableException("Couldn`t get user service.");
	}
    }

    @Override
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {

	response.setContentType("text/html");

	String deletingObject = request.getParameter("object");
	String command = request.getParameter("command");

	try {
	    if (USER_OBJECT.equals(deletingObject)) {
		if (BLOCK_COMMAND.equals(command)) {
		    int idStudent = Integer
			    .parseInt(request.getParameter("id"));
		    int block = Integer.parseInt(request.getParameter("block"));
		    userService.blockStudent(idStudent, block == 0);
		    response.sendRedirect("userManagement");
		} else if (DELETE_COMMAND.equals(command)) {
		    userService.deleteUser(Integer.parseInt(request
			    .getParameter("id")));
		    response.sendRedirect("userManagement");
		}
	    } else if (COURSE_OBJECT.equals(deletingObject)) {
		courseService.deleteCourse(Integer.parseInt(request
			.getParameter("id")));
		    response.sendRedirect("courseManagement");
	    }
	} catch (NumberFormatException | SQLException e) {
	    LOGGER.error("SQLException while deliting " + deletingObject);
	    response.sendRedirect("error");
	}
    }
}
