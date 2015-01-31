package ua.nure.khmelik.SummaryTask4.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.khmelik.SummaryTask4.entity.dbentities.User;

public class LoginFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(LoginFilter.class);

    @Override
    public void init(FilterConfig arg0) throws ServletException {
	LOGGER.debug("LoginFilter initialized.");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
	    FilterChain chain) throws IOException, ServletException {
	HttpServletRequest req = (HttpServletRequest) request;
	HttpServletResponse resp = (HttpServletResponse) response;
	HttpSession session = req.getSession();
	if (session.getAttribute("user") != null) {
	    LOGGER.debug("User logged"
		    + ((User) session.getAttribute("user")).getLogin());
	    chain.doFilter(request, response);
	} else if (req.getRequestURI().startsWith("/SummaryTask4/login")
		|| req.getRequestURI().matches(".*(css|jpg|png|gif|js)")) {
	    LOGGER.debug("Ends with login");
	    chain.doFilter(request, response);
	} else {
	    LOGGER.debug("Doesnt even end with login" + req.getRequestURI());
	    resp.sendRedirect("login");
	}
    }

    @Override
    public void destroy() {
	LOGGER.debug("LoginFilter instance destroyed.");
    }

}
