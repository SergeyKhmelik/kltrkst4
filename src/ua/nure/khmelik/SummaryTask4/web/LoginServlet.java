package ua.nure.khmelik.SummaryTask4.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.khmelik.SummaryTask4.constants.Constants;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Permission;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.User;
import ua.nure.khmelik.SummaryTask4.service.implementation.AuthorizationLogic;

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = -9056876894273126770L;

    // private LoginService loginService;

    // @Override
    // public void init() throws ServletException {
    // super.init();
    // loginService = getServletContext().getAttribute("loginService");
    // }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {
	resp.setContentType("text/html");

	String login = req.getParameter("login");
	String password = req.getParameter("password");

	try {
	    AuthorizationLogic logic = new AuthorizationLogic();
	    User user = logic.getUserByLoginPassword(login, password);
	    if (user != null) {
		ArrayList<Permission> permissions = logic
			.getPermissionsByRole(user.getRoleId());
		HttpSession session = req.getSession();
		session.setAttribute("user", user);
		session.setAttribute("permissions", permissions);
		if (user.getRoleId() == Constants.ROLE_ADMIN) {
		    session.setAttribute("title", "admin");
		}
		if (user.getRoleId() == Constants.ROLE_TEACHER) {
		    session.setAttribute("title", "teacher");
		}
		if (user.getRoleId() == Constants.ROLE_STUDENT) {
		    session.setAttribute("title", "student");
		}
		user.setLogin(login);
		user.setPassword(password);
		// resp.sendRedirect("/WEB-INF/view/jsp/main.jsp");
		req.getRequestDispatcher("WEB-INF/view/jsp/main.jsp").forward(
			req, resp);
	    } else {
		// ◊“Œ “”“???????  ¿  — ¿«¿“‹, ◊“Œ Õ≈“ “¿ Œ√Œ ﬁ«≈–¿ Õ¿ —¿ÃŒÃ
		// ÀŒ√»Õ≈?
	    }
	} catch (Exception e) {
	    req.setAttribute("error", e);
	    req.getRequestDispatcher("WEB-INF/view/jsp/login_error.jsp")
		    .forward(req, resp);
	    System.out.println(e.getMessage());
	    System.out.println(e.getLocalizedMessage());
	    e.printStackTrace();
	}

    }
}
