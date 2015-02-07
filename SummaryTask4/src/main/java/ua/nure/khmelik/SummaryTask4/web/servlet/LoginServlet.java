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

import ua.nure.khmelik.SummaryTask4.entity.data.UserData;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Permission;
import ua.nure.khmelik.SummaryTask4.exceptions.NoSuchRoleException;
import ua.nure.khmelik.SummaryTask4.exceptions.NoSuchUserException;
import ua.nure.khmelik.SummaryTask4.service.AuthorizationService;
import ua.nure.khmelik.SummaryTask4.service.PermissionService;

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = -9056876894273126770L;
    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);

    private static final String LOGPASS_VALIDATION_ERROR_MESSAGE = "Login or password cannot be empty.";

    private AuthorizationService authorizationService;
    private PermissionService permissionService;

    @Override
    public void init() throws ServletException {
	authorizationService = (AuthorizationService) getServletContext()
		.getAttribute("authorizationService");
	permissionService = (PermissionService) getServletContext()
		.getAttribute("permissionService");

	if (authorizationService == null || permissionService == null) {
	    LOGGER.error("Could not get services from appcontext");
	    throw new UnavailableException(
		    "Couldn`t get authorization and permission services.");
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

	UserData user;
	ArrayList<Permission> permissions;

	HttpSession session = request.getSession();
	if (!validate(login, password)) {
	    session.setAttribute("loginvalidation",
		    LOGPASS_VALIDATION_ERROR_MESSAGE);
	    response.sendRedirect("login");
	    return;
	}

	try {
	    user = authorizationService.getUser(login, password);

	    permissions = permissionService.getPermissions(user.getRole()
		    .getId());

	    session.setAttribute("user", user);
	    session.setAttribute("permissions", permissions);

	    LOGGER.info("User " + user.getLogin() + "(id:" + user.getIdUser()
		    + ") logged in. ");

	    response.sendRedirect("main");
	} catch (SQLException e) {
	    LOGGER.error("SQLException during users authorization.");
	    response.sendRedirect("error");
	    return;
	} catch (NoSuchUserException | NoSuchRoleException e) {
	    LOGGER.error("NoSuchUser or NoSuchRole exception during users authorization.");
	    session.setAttribute("loginerror", e.getMessage());
	    response.sendRedirect("login");
	    return;
	}
    }

    private boolean validate(String login, String password) {
	if (login == null || password == null || login == "" || password == "") {
	    return false;
	}
	return true;
    }

}
