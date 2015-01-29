package ua.nure.khmelik.SummaryTask4.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.khmelik.SummaryTask4.entity.dbentities.Permission;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.User;
import ua.nure.khmelik.SummaryTask4.exceptions.NoSuchRoleException;
import ua.nure.khmelik.SummaryTask4.exceptions.NoSuchUserException;
import ua.nure.khmelik.SummaryTask4.service.AuthorizationService;
import ua.nure.khmelik.SummaryTask4.service.PermissionService;

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = -9056876894273126770L;

    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);

    private AuthorizationService authorizationService;
    private PermissionService permissionService;

    @Override
    public void init() throws ServletException {
	super.init();
	authorizationService = (AuthorizationService) getServletContext()
		.getAttribute("authorizationService");
	permissionService = (PermissionService) getServletContext()
		.getAttribute("permissionService");

	if (authorizationService == null || permissionService == null) {
	    LOGGER.error("Could not get services from appcontext");
	    throw new UnavailableException("Couldn`t get DAO");
	}
    }

    @Override
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html");

	String login = request.getParameter("login");
	String password = request.getParameter("password");

	LOGGER.info("Entered login servlet: login=" + login + " password="
		+ password);

	User user;
	ArrayList<Permission> permissions;
	try {
	    user = authorizationService.getUser(login, password);

	    if (user == null) {
		throw new NoSuchUserException();
	    }
	    permissions = permissionService.getPermissions(user.getIdRole());

	    HttpSession session = request.getSession();
	    session.setAttribute("user", user);
	    session.setAttribute("permissions", permissions);

	    // request.getRequestDispatcher("/mainjsp").forward(request,
	    // response);
	    LOGGER.info("User " + user.getLogin() + "(id:" + user.getId()
		    + ") logged in. ");
	    response.sendRedirect("main");

	} catch (NoSuchUserException | NoSuchRoleException e) {
	    LOGGER.info("Wrong username and password.");
	    response.sendRedirect("");
	    return;
	} catch (SQLException e) {
	    // REDIRECT NA "SORRY PAGE"
	    LOGGER.error("Exception during users authorization.");
	    return;
	}

    }
}
