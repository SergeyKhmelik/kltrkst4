package ua.nure.khmelik.SummaryTask4.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.khmelik.SummaryTask4.entity.data.RoleData;
import ua.nure.khmelik.SummaryTask4.service.PermissionService;

public class PermissionManagementServlet extends HttpServlet{

    private static final long serialVersionUID = 4933661367388728611L;
    private static final Logger LOGGER = Logger.getLogger(PermissionManagementServlet.class);

    private PermissionService permissionService;
    
    @Override
    public void init() throws ServletException {
        permissionService = (PermissionService) getServletContext().getAttribute("permissionService");
        
        if(permissionService == null){
            throw new UnavailableException("Couldn`t get DAO");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
	
	response.setContentType("text/html");
	
	LOGGER.info("Entered permissionManagement servlet");
	
	ArrayList<RoleData> roles = new ArrayList<RoleData>();
	
	try {
	    roles = permissionService.getRoles();
	} catch (SQLException e) {
	    LOGGER.error("Exception during role reading.");
	    response.sendRedirect("error");
	    return;
	}
	
	System.out.println(roles);
	request.setAttribute("roles", roles);	
	
	request.getRequestDispatcher("permissions").forward(request, response);
    }
    
}
