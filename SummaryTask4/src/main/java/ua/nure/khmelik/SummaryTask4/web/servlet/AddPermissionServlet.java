package ua.nure.khmelik.SummaryTask4.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.khmelik.SummaryTask4.service.PermissionService;

public class AddPermissionServlet extends HttpServlet {

    private static final long serialVersionUID = -9039909646827661785L;
    private static final Logger LOGGER = Logger.getLogger(AddPermissionServlet.class);

    private PermissionService permissionService;

    @Override
    public void init() throws ServletException {
	permissionService = (PermissionService) getServletContext()
		.getAttribute("permissionService");
	if (permissionService == null) {
	    LOGGER.error("Could not get services from appcontext");
	    throw new UnavailableException("Couldn`t get user service.");
	}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	
	response.setContentType("text/html");
	
	int idPermission = Integer.parseInt(request.getParameter("idPermission"));
	int idRole = Integer.parseInt(request.getParameter("idRole"));
	
	try {
	    permissionService.addPermissionToRole(idPermission, idRole);
	} catch (SQLException e) {
	    LOGGER.error("SQLException while adding permission.");
	    response.sendRedirect("error");
	    return;
	}
	
	response.sendRedirect("permissionManagement");
    }

}
