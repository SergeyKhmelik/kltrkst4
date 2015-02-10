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
import ua.nure.khmelik.SummaryTask4.service.PermissionService;
import ua.nure.khmelik.SummaryTask4.service.UserService;

public class DeleteServlet extends HttpServlet {

    private static final long serialVersionUID = -7740515957618244962L;
    private static final Logger LOGGER = Logger.getLogger(DeleteServlet.class);
    private static final String USER_OBJECT = "user";
    private static final String COURSE_OBJECT = "course";
    private static final String PERMISSION_OBJECT = "permission";
    private static final Object BLOCK_COMMAND = "block";
    private static final Object DELETE_COMMAND = "delete";

    private UserService userService;
    private CourseService courseService;
    private PermissionService permissionServise;
    
    @Override
    public void init() throws ServletException {
	userService = (UserService) getServletContext().getAttribute(
		"userService");
	courseService = (CourseService) getServletContext().getAttribute("courseService");
	permissionServise = (PermissionService) getServletContext().getAttribute("permissionService");
	
	if (userService == null || permissionServise == null) {
	    LOGGER.error("Could not get services from appcontext");
	    throw new UnavailableException("Couldn`t get user service.");
	}
    }

    @Override
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {

	response.setContentType("text/html");

	String command = request.getParameter("command");
	String deletingObject = request.getParameter("object");
	LOGGER.debug("Delete servlet started with command " + command + "."
		+ " Object is " + deletingObject);
	
	try {
	    if (USER_OBJECT.equals(deletingObject)) {
		if (BLOCK_COMMAND.equals(command)) {
		    LOGGER.error(request.getParameter("id"));
		    int idStudent = Integer.parseInt(request.getParameter("id"));
		    boolean block = Boolean.parseBoolean(request.getParameter("block"));
		    userService.blockStudent(idStudent, block);
		    response.sendRedirect("userManagement");
		} else if (DELETE_COMMAND.equals(command)) {
		    userService.deleteUser(Integer.parseInt(request
			    .getParameter("id")));
		    response.sendRedirect("userManagement");
		}
		return;
	    }
	    
	    if (COURSE_OBJECT.equals(deletingObject)) {
		courseService.deleteCourse(Integer.parseInt(request
			.getParameter("id")));
		    response.sendRedirect("courseManagement");
		    return;
	    }
	    	    
	    if(PERMISSION_OBJECT.equals(deletingObject)){
		int idPermission = Integer.parseInt(request.getParameter("idPermission"));
		int idRole = Integer.parseInt(request.getParameter("idRole"));
		permissionServise.removePermissionFromRole(idPermission, idRole);
		response.sendRedirect("permissionManagement");
	    }
	    
	} catch (SQLException e) {
	    LOGGER.error("SQLException while deliting " + deletingObject);
	    response.sendRedirect("error");
	} catch (NumberFormatException e) {
	    LOGGER.error("NumberFormatException while deliting " + deletingObject);
	    response.sendRedirect("error");
	}
    }
}
