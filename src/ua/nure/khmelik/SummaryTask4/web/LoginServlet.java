package ua.nure.khmelik.SummaryTask4.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.khmelik.SummaryTask4.constants.Constants;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Permission;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.User;
import ua.nure.khmelik.SummaryTask4.exceptions.NoSuchRoleException;
import ua.nure.khmelik.SummaryTask4.exceptions.NoSuchUserException;
import ua.nure.khmelik.SummaryTask4.service.AuthorizationService;
import ua.nure.khmelik.SummaryTask4.service.PermissionService;

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = -9056876894273126770L;

    // private LoginService loginService;

    // @Override
    // public void init() throws ServletException {
    // super.init();
    // loginService = getServletContext().getAttribute("loginService");
    // }

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
	    throw new UnavailableException("Couldn`t get DAO");
	}
    }

    @Override
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html");

	System.out.println("Loging servlet");
	
	String login = request.getParameter("login");
	String password = request.getParameter("password");

	User user;
	ArrayList<Permission> permissions;
	try {
	    user = authorizationService.getUser(login, password);

	    permissions = permissionService.getPermissions(user.getIdRole());

	    HttpSession session = request.getSession();
	    session.setAttribute("user", user);
	    session.setAttribute("permissions", permissions);
	    if (user.getIdRole() == Constants.ROLE_ADMIN) {
		session.setAttribute("title", "admin");
	    }
	    if (user.getIdRole() == Constants.ROLE_TEACHER) {
		session.setAttribute("title", "teacher");
	    }
	    if (user.getIdRole() == Constants.ROLE_STUDENT) {
		session.setAttribute("title", "student");
	    }
	    request.getRequestDispatcher("/mainjsp").forward(request, response);

	} catch (NoSuchUserException | NoSuchRoleException e) {
	    RequestDispatcher rd = request.getRequestDispatcher("/loginjsp");
	    PrintWriter out = response.getWriter();
	    out.println("<font color=red>" + e.getMessage() + "</font>");
	    rd.include(request, response);
	    return;
	} catch (SQLException e) {
	    // REDIRECT NA "SORRY PAGE"
	    e.printStackTrace();
	    return;
	}

	/*
	 * try { AuthorizationLogic logic = new AuthorizationLogic(); User user
	 * = logic.getUserByLoginPassword(login, password); if (user != null) {
	 * ArrayList<Permission> permissions = logic
	 * .getPermissionsByRole(user.getIdRole()); HttpSession session =
	 * req.getSession(); session.setAttribute("user", user);
	 * session.setAttribute("permissions", permissions); if
	 * (user.getIdRole() == Constants.ROLE_ADMIN) {
	 * session.setAttribute("title", "admin"); } if (user.getIdRole() ==
	 * Constants.ROLE_TEACHER) { session.setAttribute("title", "teacher"); }
	 * if (user.getIdRole() == Constants.ROLE_STUDENT) {
	 * session.setAttribute("title", "student"); } user.setLogin(login);
	 * user.setPassword(password); //
	 * resp.sendRedirect("/WEB-INF/view/jsp/main.jsp");
	 * req.getRequestDispatcher("WEB-INF/view/jsp/main.jsp").forward( req,
	 * resp); } else { // ◊“Œ “”“???????  ¿  — ¿«¿“‹, ◊“Œ Õ≈“ “¿ Œ√Œ ﬁ«≈–¿
	 * Õ¿ —¿ÃŒÃ // ÀŒ√»Õ≈? } } catch (Exception e) {
	 * req.setAttribute("error", e);
	 * req.getRequestDispatcher("WEB-INF/view/jsp/login_error.jsp")
	 * .forward(req, resp); System.out.println(e.getMessage());
	 * System.out.println(e.getLocalizedMessage()); e.printStackTrace(); }
	 */
    }
}
